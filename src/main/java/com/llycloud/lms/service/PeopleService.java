package com.llycloud.lms.service;

import com.llycloud.lms.model.entity.People;
import com.llycloud.lms.model.enums.UserLevelEnum;
import com.llycloud.lms.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


/**
 * @author Akide Liu
 * @date 2021-01-16 16:50
 */
@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<People> getAllPeople(){

        return peopleRepository.findAll();

    }

    public List<People> getPeopleByRoleId(int roleId){

        return peopleRepository.findByUserLevel(roleId);

    }

    public Optional<People> getAllPeople(int id){

        return peopleRepository.findById(id);

    }

    public People createOrUpdatePeople(People ReceivedPeople) {

        Optional<People> result = Optional.empty();

        if (ReceivedPeople.getUserId() != null) {
             result = peopleRepository.findById(ReceivedPeople.getUserId());
        }

        //update
        if (result.isPresent()) {

            return updatePeople(ReceivedPeople, result);

        //insert
        } else {

            return createPeople(ReceivedPeople);

        }

    }

    @NotNull
    private People createPeople(People ReceivedPeople) {
    /*
        TODO need more consideration of tutor

        TUTOR and Lecturer automatically enrolled in all subject
        This auto enroll operation just implement during user creation progress

    */

        Integer userLevel = ReceivedPeople.getUserLevel();

        try {

            switch (UserLevelEnum.enumMap.get(userLevel)) {

                case STUDENT:
                case ADMIN:
                    ReceivedPeople.setBelongedSubjects(new HashSet<>());

                    break;

                case TUTOR:
                case LECTURER:

                    ReceivedPeople.setBelongedSubjects(
                            new HashSet<>(subjectService.getAllSubjects())
                    );

                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + UserLevelEnum.enumMap.get(userLevel));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ReceivedPeople.setPassword(bCryptPasswordEncoder.encode(ReceivedPeople.getPassword()));


        return peopleRepository.saveAndFlush(ReceivedPeople);
    }

    @NotNull
    private People updatePeople(People ReceivedPeople, Optional<People> result) {
        People newPeople = result.get();

        /**
         * handle user level changes in order to modify belonged subject
         * Student -> Tutor / Lecturer = Add All subject
         * Tutor / Lecturer -> Students = Remove All Subject
         * TODO need mode consideration for student self requested joined subject
         */
        if (
                newPeople.getUserLevel().equals(UserLevelEnum.STUDENT.getValue())
                && !ReceivedPeople.getUserLevel().equals(UserLevelEnum.STUDENT.getValue())

        ) {
            newPeople.setBelongedSubjects(new HashSet<>(subjectService.getAllSubjects()));
        }
        else if (

                !newPeople.getUserLevel().equals(UserLevelEnum.STUDENT.getValue())
                && ReceivedPeople.getUserLevel().equals(UserLevelEnum.STUDENT.getValue())

        ){
            newPeople.setBelongedSubjects(new HashSet<>());
        }


        newPeople.setName(ReceivedPeople.getName());
        newPeople.setPassword(bCryptPasswordEncoder.encode(ReceivedPeople.getPassword()));
        newPeople.setIsActive(ReceivedPeople.getIsActive());
        newPeople.setTitle(ReceivedPeople.getTitle());
        newPeople.setUserLevel(ReceivedPeople.getUserLevel());


        return peopleRepository.saveAndFlush(newPeople);
    }

    public Boolean deletePeople(Integer id) {
        if (peopleRepository.findById(id).isPresent()) {
            peopleRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }


    public Optional<People> getUserByName(String username) {

        return peopleRepository.findByName(username);

    }
}
