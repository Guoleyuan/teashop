package com.guet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeaCategory {

    MIKETEA("奶茶"),

    JUICE("果汁"),

    COFFEE("咖啡");



    private final String type;

}
