package com.sgwb.saigonwaterbus.Model.Enum;

public enum Role implements BaseEnum{
    USER(0, "User"),
    STAFF(1, "Staff"),
    ADMIN(2, "Admin");

    private final int key;
    private final String value;

    Role(int key, String value) {
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

    public static Role fromKey(int key) {
        return AbstractBaseEnum.fromKey(Role.class, key);
    }
}
