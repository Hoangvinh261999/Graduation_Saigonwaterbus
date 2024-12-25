package com.sgwb.saigonwaterbus.Model.Enum;

public enum Status implements BaseEnum {
    INACTIVE(0, "Inactive"),
    ACTIVE(1, "Active"),
    COMPLETED(2, "Completed"),

    CANCELLED(3, "Cancelled"),
    INPROGRESS(4, "In_Progress"),
    MAINTENANCE(5, "Maintenance"),
    BOOKED(6, "Booked"),
    NOTYET(7, "NotYet");

    private final int key;
    private final String value;

    Status(int key, String value) {
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

    public static Status fromKey(int key) {
        return AbstractBaseEnum.fromKey(Status.class, key);
    }
}
