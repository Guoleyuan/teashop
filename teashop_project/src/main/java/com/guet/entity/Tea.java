package com.guet.entity;

import lombok.Data;

@Data
public class Tea {
    private Integer teaId;
    private String teaName;
    private Integer teaAmount;
    private double teaPrice;
    private String teaCategory;
    private Double teaDiscount;

}
