package com.llycloud.lms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Akide Liu
 * @date 2021-01-25 19:15
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO implements Serializable {


    @Nullable
    private Integer subjectId;

    @NotNull
    private String name;

    private Set<PeopleEmbedDTO> containedPeople;


}
