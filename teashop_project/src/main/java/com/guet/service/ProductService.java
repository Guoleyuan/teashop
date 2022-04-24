package com.guet.service;

import com.guet.entity.Tea;

import java.util.List;

public interface ProductService {

    List<Tea> selectAllProduct();

    // List<Tea> list = service.selectAllProduct();
    // Object[][] a=new Object[list.size()][5];
    //     for (int i = 0; i < list.size(); i++) {
    //     Tea tea=list.get(i);
    //     a[i][0]=tea.getTeaId();
    //     a[i][1]=tea.getTeaName();
    //     a[i][2]=tea.getTeaAmount();
    //     a[i][3]=tea.getTeaPrice();
    //     a[i][4]=tea.getTeaCategory();

    List<Tea> selectSomeProducts(String str);

    int insertProduct(Tea tea);

    int updateProductAmount(String name);
}
