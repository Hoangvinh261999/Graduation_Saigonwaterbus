package com.sgwb.saigonwaterbus.Model.Enum;

public abstract class AbstractBaseEnum<E extends Enum<E> & BaseEnum> implements BaseEnum {
    private final int key;
    private final String value;

    protected AbstractBaseEnum(int key, String value) {
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

    public static <E extends Enum<E> & BaseEnum> E fromKey(Class<E> enumClass, int key) {
        for (E enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getKey() == key) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("Invalid key: " + key);
    }
}

