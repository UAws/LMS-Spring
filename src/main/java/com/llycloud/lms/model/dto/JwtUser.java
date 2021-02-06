package com.llycloud.lms.model.dto;

import com.llycloud.lms.model.entity.People;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Akide Liu
 */

@Data
@AllArgsConstructor
public class JwtUser {

    private PeopleDTO user;

    private String token;

}
