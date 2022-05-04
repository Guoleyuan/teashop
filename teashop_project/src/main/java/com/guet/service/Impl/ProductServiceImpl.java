package com.guet.service.Impl;

import com.guet.dao.Impl.ProductDaoImpl;
import com.guet.dao.ProductDao;
import com.guet.entity.Tea;
import com.guet.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao=new ProductDaoImpl();

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<Tea> selectAllProduct() {
        List<Tea> list = productDao.selectAllProducts();
        return list;
    }

    /**
     * 模糊查询所有带关键字的产品
     * @param str
     * @return
     */
    @Override
    public List<Tea> selectSomeProducts(String str) {
        List<Tea> list = productDao.selectSomeProducts(str);
        return  list;
    }

    /**
     * 插入奶茶数据
     * 如果插入成功，返回1  如果插入失败 返回0
     * @param tea
     * @return
     */
    @Override
    public int insertProduct(Tea tea) {
        int i = productDao.insertProduct(tea);
        if (i==1){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 完成订单之后，对相应的数据库的奶茶数量减去1
     * @param name
     * @return
     */
    @Override
    public int updateProductAmount(String name) throws SQLException {
        int i = productDao.updateProductAmount(name);
        if (i==1){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 删除奶茶商品  通过id
     * @param id
     * @return
     */
    @Override
    public boolean deleteProductById(int id) {
        boolean b = productDao.deleteProductById(id);
        if (b){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int searchAmount(String name) {
        int i = productDao.searchAmountByName(name);
        return i;
    }
}
