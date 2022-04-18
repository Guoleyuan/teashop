package com.guet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeaStatus {

    WAIT("待处理订单"),

    HISTORY("历史订单");



    private final String type;

}
