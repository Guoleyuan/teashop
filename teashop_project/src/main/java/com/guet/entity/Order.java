package com.guet.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer orderId;
    private String orderNumber;
    private Float orderPrice;
    private String orderName;
    private String orderCreatTime;
    private Integer orderStatus;
}
