package com.guet.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {
    private Integer orderId;
    private String orderNumber;
    private Float orderPrice;
    private String orderName;
    private Integer orderStatus;
    private Integer mchId;
    private Timestamp orderTime;
    private String transactionId;
}
