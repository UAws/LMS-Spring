package com.llycloud.lms.service;

import com.llycloud.lms.model.entity.People;
import com.llycloud.lms.model.entity.Subject;
import com.llycloud.lms.model.enums.UserLevelEnum;
import com.llycloud.lms.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Akide Liu
 * @date 2021-01-17 16:28
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private PeopleService peopleService;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }


    public Optional<Subject> getSubjectById(int subjectId) {
        return subjectRepository.findById(subjectId);
    }

    public Subject createOrUpdateSubject(Subject ReceivedSubject) {

        Optional<Subject> result = Optional.empty();

        if (ReceivedSubject.getSubjectId() != null) {

            result = subjectRepository.findById(ReceivedSubject.getSubjectId());

        }

            Subject newSubject = result.get();

            newSubject.setName(ReceivedSubject.getName());


            // TODO need reduce DB operations

            List<People> tutors = peopleService.getPeopleByRoleId(UserLevelEnum.TUTOR.getValue());

            List<People> lecturers = peopleService.getPeopleByRoleId(UserLevelEnum.LECTURER.getValue());

            List<People> newLists = new ArrayList<>();

            newLists.addAll(tutors);
            newLists.addAll(lecturers);

            newSubject.setContainedPeople(new HashSet<>(newLists));


        return subjectRepository.saveAndFlush(newSubject);


    }

    public Boolean deleteSubject(Integer id) {
        if (subjectRepository.findById(id).isPresent()) {
            subjectRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
