package com.llycloud.lms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Akide Liu
 * @date 2021-01-17 16:35
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDTO implements Serializable {

    @Nullable
    private Integer userId;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String title;
    @NotNull
    private Boolean isActive;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer userLevel;

    private Set<SubjectEmbedDTO> belongedSubjects;


}
