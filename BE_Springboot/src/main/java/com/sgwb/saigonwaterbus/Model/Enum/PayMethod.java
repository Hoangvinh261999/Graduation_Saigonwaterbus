package com.sgwb.saigonwaterbus.Model.Enum;

public enum PayMethod implements BaseEnum{
    CASH(0, "Cash"),
    BANK_TRANSFER(1, "Bank_Transfer");

    private final int key;
    private final String value;

    PayMethod(int key, String value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static PayMethod fromKey(int key) {
        return AbstractBaseEnum.fromKey(PayMethod.class, key);
    }
}
