package com.llycloud.lms.model.dto;

import lombok.Data;

/**
 * @author Akide Liu
 */
@Data
public class UserLoginDTO {

    private String username;

    private String password;

    private Boolean rememberMe = false;
}
