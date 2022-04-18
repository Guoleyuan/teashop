package com.guet.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String orderNumber;
    private Double orderPrice;
    private String orderName;
    private Date orderCreatTime;
    private Integer orderStatus;
}
