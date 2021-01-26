package com.llycloud.lms.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserLevelEnum {

    STUDENT(1),
    TUTOR(2),
    LECTURER(3),
    ADMIN(4),
    ;

    public Integer getValue() {
        return value;
    }

    public static Map<Integer, UserLevelEnum> enumMap = new HashMap<>();


    static {

        for (UserLevelEnum value : UserLevelEnum.values()) {
            enumMap.put(value.value, value);
        }
    }

    private final Integer value;

    UserLevelEnum(Integer value) {
        this.value = value;
    }
}
