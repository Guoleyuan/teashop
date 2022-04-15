package com.guet.util;

public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return true:空字符串  false：非空字符串
     */
    public static boolean isStringNull(String str){
        if (str==null||"".equals(str.trim())){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        String name="";
        boolean stringNull = isStringNull(name);
        System.out.println(stringNull);
    }
}
