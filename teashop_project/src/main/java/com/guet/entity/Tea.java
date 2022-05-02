package com.guet.entity;

import lombok.Data;

@Data
public class Tea {
    private Integer teaId;
    private String teaName;
    private Integer teaAmount;
    private Float teaPrice;
    private String teaCategory;
    private Float teaDiscount;

}
