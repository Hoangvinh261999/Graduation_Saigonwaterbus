package com.sgwb.saigonwaterbus.Model.Enum;

public enum Fixed implements BaseEnum{
    FIXED(1, "Fixed"),
    UNSTABLE(0, "Unstable");

    private final int key;
    private final String value;

    Fixed(int key, String value) {
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

    public static Fixed fromKey(int key) {
        return AbstractBaseEnum.fromKey(Fixed.class, key);
    }
}
