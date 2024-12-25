package com.sgwb.saigonwaterbus.Exception;

import com.sgwb.saigonwaterbus.Model.Enum.BaseEnum;

public enum ErrorCode implements BaseEnum {
    NO_STATIC_RESOURCES(404, "No static resources found"),
    ACCOUNT_EXISTED(500,"Account existed");

    private final int key;
    private final String value;

    ErrorCode(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}