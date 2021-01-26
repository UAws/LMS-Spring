package com.llycloud.lms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Akide Liu
 * @date 2021-01-25 20:35
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectEmbedDTO {

    private Integer subjectId;
    private String name;

}
