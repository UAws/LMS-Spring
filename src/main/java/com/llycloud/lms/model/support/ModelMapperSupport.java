package com.llycloud.lms.model.support;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Akide Liu
 * @date 2021-01-18 15:48
 */

@Component
public class ModelMapperSupport {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
