package com.llycloud.lms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Akide Liu
 * @date 2021-01-25 20:37
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleEmbedDTO {

    private Integer userId;
    private String name;
    private String password;
    private String title;
    private Boolean isActive;
    private Integer userLevel;


}
