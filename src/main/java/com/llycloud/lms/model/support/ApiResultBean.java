package com.llycloud.lms.model.support;

import java.io.Serializable;

/**
 * @author Akide Liu
 * @date 2021-01-16 16:42
 */
public class ApiResultBean implements Serializable {

    private static final long serialVersionUID = -8276264968757808344L;

    public static final int SUCCESS = 20000;

    public static final int FAIL = -1;

    public static final int AUTHORIZATION_REQUIRED = -2;

    public static final int Forbidden = -3;

    private String message = "Request SUCCESS";

    private int code = SUCCESS;

    private Object data;

    private ApiResultBean() {
        super();
    }

    private ApiResultBean(String message, Object data, int code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public static ApiResultBean success() {
        return success("Request SUCCESS");
    }

    public static ApiResultBean success(String msg) {
        return success(msg, null);
    }

    public static ApiResultBean successData(Object data) {
        return success("Request SUCCESS", data);
    }

    public static ApiResultBean successPage(Object data, Long total) {
        return success("Request SUCCESS", data);
    }

    public static ApiResultBean success(Object data) {
        return success("Request SUCCESS", data);
    }

    public static ApiResultBean success(String msg, Object data) {
        return new ApiResultBean(msg, data, SUCCESS);
    }

    public static ApiResultBean error(String msg) {
        ApiResultBean ApiResultBean = new ApiResultBean();
        ApiResultBean.setCode(FAIL);
        ApiResultBean.setMessage(msg);
        return ApiResultBean;
    }

    public static ApiResultBean error(String msg, Integer code) {
        ApiResultBean ApiResultBean = new ApiResultBean();
        ApiResultBean.setCode(code);
        ApiResultBean.setMessage(msg);
        return ApiResultBean;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
