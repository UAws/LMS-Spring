package com.llycloud.lms.model.enums;

import java.util.Arrays;

public enum PersistentLayerErrorEnum {

    NO_SUCH_FILED_ERROR(
            "NO_SUCH_RECORD_ERROR",
            30001,
            "Database operational and handled request properly, " +
                    "But there is no such Record(s) existed, " +
                    "Arguments :  ",
            "Following data are not found from such sql"),
    ;


    private final String key;
    private final Integer errorCode;
    private final String message;
    private final String description;


    public String getKey() {
        return key;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage(Object... RequestInfo) {
        return message + Arrays.toString(RequestInfo);
    }

    public String getDescription() {
        return description;
    }

    /***
     *
     * @param key
     * @param errorCode provide error code to front end
     * @param message   response msg by controller, excluding sql info and sensitive data
     * @param description logging to system, including all sql info and sensitive data
     */
    PersistentLayerErrorEnum(String key, Integer errorCode, String message, String description) {
        this.description = description;
        this.errorCode = errorCode;
        this.key = key;
        this.message = message;
    }


}
