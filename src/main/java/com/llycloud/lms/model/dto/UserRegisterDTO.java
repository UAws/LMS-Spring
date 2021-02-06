package com.llycloud.lms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Akide Liu
 * Date : 5/2/21
 */

@Data
@AllArgsConstructor
public class UserRegisterDTO {

    @NotNull
    @Size(min = 4, max = 30)
    private String username;

    @NotNull
    @Size(min = 4, max = 30)
    private String password;

}
