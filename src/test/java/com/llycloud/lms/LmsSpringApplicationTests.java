package com.llycloud.lms;

import com.llycloud.lms.model.entity.People;
import com.llycloud.lms.model.entity.Subject;
import com.llycloud.lms.model.enums.PersistentLayerErrorEnum;
import com.llycloud.lms.repository.PeopleRepository;
import com.llycloud.lms.repository.SubjectRepository;
import com.llycloud.lms.service.PeopleService;
import com.llycloud.lms.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
class LmsSpringApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void DBConnectionTest(){
        Iterable<People> all = peopleRepository.findAll();
        for (People people : all) {
            System.out.println(people);
        }
    }

    @Test
    public void getAllPeopleTest() {
        List<People> byUserLevel = peopleRepository.findByUserLevel(3);
        for (People people : byUserLevel) {
            System.out.println(people);
        }
    }

    @Test
    public void testEnum() {
        int id = 1;
        System.out.println(PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getMessage(id));
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void manyToManyTest() {

        Subject subject = subjectRepository.getOne(1);

        Set<People> peoples = subject.getContainedPeople();

        for (People people : peoples) {
            System.out.println(people);
        }

    }

    @Autowired
    PeopleService peopleService;

    @Autowired
    SubjectService subjectService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void createOrUpdatePeople() {

        List<Subject> allSubjects = subjectService.getAllSubjects();
        Set<Subject> set = new HashSet<>();

        for (Subject subject : allSubjects) {
            set.add(subject);
        }

        People t = People.builder()
                .userId(0)
                .title("T")
                .password("")
                .name("test" + UUID.randomUUID())
                .isActive(true)
                .userLevel(3)
                // .belongedSubjects(null)
                .build();

        // People t = new People(0,"T","123","123",(byte)1,3,null);
        People newPeople = peopleService.createOrUpdatePeople(t);
        // People save = peopleRepository.save(t);
        System.out.println(newPeople);

        peopleRepository.deleteById(newPeople.getUserId());
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void dbLayerCreatedDataDestroyer() {

        List<People> all = peopleRepository.findAll();

        try {
            for (People people : all) {
                if (people.getUserId() >= 100) {
                    peopleRepository.deleteById(people.getUserId());
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void createOrUpdateSubject() {

        List<Subject> allSubjects = subjectService.getAllSubjects();
        Set<Subject> set = new HashSet<>();

        Subject subject = Subject.builder()
                .subjectId(4)
                .name("test")
                .build();


        Subject t = subjectService.createOrUpdateSubject(subject);

        subjectService.deleteSubject(t.getSubjectId());
    }

}
