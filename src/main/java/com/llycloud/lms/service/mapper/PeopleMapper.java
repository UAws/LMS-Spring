package com.llycloud.lms.service.mapper;

import com.llycloud.lms.model.dto.PeopleDTO;
import com.llycloud.lms.model.entity.People;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Akide Liu
 */
@Component
public class PeopleMapper {

    @Autowired
    private ModelMapper modelMapper;


    public  PeopleDTO convertToDto(People people) {
        return modelMapper.map(people, PeopleDTO.class);
    }

    public People convertToEntity(PeopleDTO peopleDTO) { return modelMapper.map(peopleDTO, People.class); }




}
