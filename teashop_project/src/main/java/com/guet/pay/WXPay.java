package com.guet.pay;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.guet.entity.Order;
import com.guet.entity.Tea;
import com.guet.sdk.WXPayUtil;
import com.guet.service.Impl.OrderServiceImpl;
import com.guet.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class WXPay {

    private static Log log = LogFactory.getLog(WXPay.class);

    private static final String PAY_SUCCESS = "SUCCESS";
    private static final String PAY_USERPAYING = "USERPAYING";

    public static void main(String[] args) throws Exception {

        // 生成二维码，完成支付
        // unifiedOrder();
        // 商家扫用户手机的条形码

    }

    /**
     * 微信付款吗支付
     *
     * @throws Exception
     */
    public static String scanCodeToPay(String auth_code,Order order,List<Tea> shopCartList) throws Exception {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();

        MyConfig config = new MyConfig();
        com.guet.sdk.WXPay wxpay = new com.guet.sdk.WXPay(config);

        String out_trade_no = order.getOrderNumber();
        Map<String, String> map = new HashMap<>(16);

        String orderName = order.getOrderName();
        String s = orderName + ";";

        map.put("attach",s );
        map.put("auth_code", auth_code);
        map.put("body", "蜜雪冰城");
        map.put("device_info", "桂电10号店");
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        map.put("out_trade_no", out_trade_no);
        map.put("spbill_create_ip", spbill_create_ip);
        int total_fee = (int) (order.getOrderPrice()*100);//100分：1块钱
        map.put("total_fee", String.valueOf(total_fee));
        //生成签名
        String sign = WXPayUtil.generateSignature(map, config.getKey());
        map.put("sign", sign);
        String mapToXml = null;
        try {
            //调用微信的扫码支付接口
            Map<String, String> resp = wxpay.microPay(map);
            System.out.println("扫码支付结果：" + resp);
            mapToXml = WXPayUtil.mapToXml(resp);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信支付失败" + e);
        }


        //判断支付是否成功
        String return_code = null;
        String result_code = null;
        String err_code_des = null;
        String err_code = null;
        String transaction_id=null;
        String time_end=null;
        Timestamp timestamp=null;
        //获取Document对象（主要是获取支付接口的返回信息）
        Document doc = DocumentHelper.parseText(mapToXml);
        //获取对象的根节点<xml>
        Element rootElement = doc.getRootElement();
        //获取对象的子节点
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            if (element.getName().equals("return_code")) {
                return_code = element.getTextTrim();
            } else if (element.getName().equals("result_code")) {
                result_code = element.getTextTrim();
            } else if (element.getName().equals("err_code_des")) {
                err_code_des = element.getTextTrim();
            } else if (element.getName().equals("err_code")) {
                err_code = element.getTextTrim();
            }
        }
        for (Element element : elements) {
            if (element.getName().equals("transaction_id")) {
                transaction_id = element.getTextTrim();
            }
            if (element.getName().equals("time_end")) {
                time_end = element.getTextTrim();
            }
        }
        if (transaction_id!=null&&time_end!=null){
            order.setTransactionId(transaction_id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            timestamp = new Timestamp(sdf.parse(time_end).getTime());
            order.setOrderTime(timestamp);
        }
        if (PAY_SUCCESS.equals(return_code) && PAY_SUCCESS.equals(result_code)) {
            log.info("微信免密支付成功！");
            new OrderServiceImpl().shopCardPay(order,shopCartList);
            return PAY_SUCCESS;
        } else if (PAY_USERPAYING.equals(err_code)) {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(3000);
                Map<String, String> data = new HashMap<>(16);
                data.put("out_trade_no", out_trade_no);
                //调用微信的查询接口
                Map<String, String> orderQuery = wxpay.orderQuery(data);
                String orderResp = WXPayUtil.mapToXml(orderQuery);
                String trade_state = null;
                //获取Document对象
                Document orderDoc = DocumentHelper.parseText(orderResp);
                //获取对象的根节点<xml>
                Element rootElement1 = orderDoc.getRootElement();
                //获取对象的子节点
                List<Element> elements1 = rootElement1.elements();
                for (Element element : elements1) {
                    if (element.getName().equals("trade_state")) {
                        trade_state = element.getTextTrim();
                    }
                    if (element.getName().equals("transaction_id")) {
                        transaction_id = element.getTextTrim();
                    }
                    if (element.getName().equals("time_end")) {
                        time_end = element.getTextTrim();
                    }
                }
                if (transaction_id!=null&&time_end!=null){
                    order.setTransactionId(transaction_id);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    timestamp = new Timestamp(sdf.parse(time_end).getTime());
                    order.setOrderTime(timestamp);
                }

                if (PAY_SUCCESS.equals(trade_state)) {
                    log.info("微信加密支付成功！");
                    new OrderServiceImpl().shopCardPay(order,shopCartList);
                    return PAY_SUCCESS;
                }
                log.info("正在支付" + orderResp);
            }
        }
        log.error("微信支付失败！");
        return "支付失败";
    }

    /*
    下单：生成二维码
     */
    public static void unifiedOrder(Order order) {
        Map<String, String> resultMap = new HashMap();
        // String openid = "ouR0E1oP5UGTEBce8jZ_sChfH26g";
        MyConfig config = null;
        com.guet.sdk.WXPay wxpay=null;
        try {
            config = new MyConfig();
            wxpay = new com.guet.sdk.WXPay(config);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成的随机字符串
        String nonce_str = order.getOrderNumber();
        //获取客户端的ip地址
        //获取本机的ip地址
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();
        //支付金额，需要转成字符串类型，否则后面的签名会失败
        int total_fee = (int) (order.getOrderPrice()*100);//100分：1块钱
        //商品描述
        String orderName = order.getOrderName();


        String body = "蜜雪冰城";
        //商户订单号
        String out_trade_no = order.getOrderNumber();
        //统一下单接口参数
        SortedMap<String, String> data = new TreeMap<String, String>();
        data.put("appid", "wxd9a46e74fc279fcc");
        data.put("body",body);
        data.put("mch_id", "1623889015");
        // 回调接口，必须是一个域名，不能使用IP
        // 腾讯会自动调用你（程序自己提供的接口）的接口，给你发送支付结果的数据，数据格式：xml格式
        data.put("notify_url", "http://4p77a46269.qicp.vip/result");
        data.put("out_trade_no", out_trade_no);//交易号
        data.put("spbill_create_ip", spbill_create_ip);//下单的电脑IP地址
        data.put("trade_type", "NATIVE");//支付类型
        data.put("total_fee", String.valueOf(total_fee));
        // data.put("openid", openid);
        String s = orderName + ";";
        System.out.println(s);
        data.put("attach",s);

        try {
            Map<String, String> rMap = wxpay.unifiedOrder(data);
            System.out.println("统一下单接口返回: " + rMap);
            createQRCode(rMap);//生成二维码
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createQRCode(Map<String, String> map) throws Exception {

        File outputFile = new File(System.getProperty("user.dir")+"/teashop_project/src/main/resources" + File.separator + "new.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        String url = map.get("code_url");
        System.out.println("生成二维码的url：" + url);
        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300, hints);

            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", fileOutputStream);
        } catch (Exception e) {
            throw new Exception("生成二维码失败！");
        } finally {
            fileOutputStream.close();
        }
    }
}