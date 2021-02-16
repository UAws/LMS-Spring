package com.llycloud.lms.controller;

import com.llycloud.lms.model.dto.PeopleDTO;
import com.llycloud.lms.model.entity.People;
import com.llycloud.lms.model.enums.PersistentLayerErrorEnum;
import com.llycloud.lms.model.support.ApiResultBean;
import com.llycloud.lms.service.PeopleService;
import com.llycloud.lms.service.mapper.PeopleMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Akide Liu
 * Date 2021-01-16
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Resource
    private PeopleService peopleService;

    @Resource
    private PeopleMapper peopleMapper;


    @GetMapping("/")
    public ApiResultBean getAllPeople(
            @RequestParam(name = "isShowSubject", required = false)
                    Optional<Boolean> isShowSubject
    ) {
        List<People> allPeople = peopleService.getAllPeople();

        return ApiResultBean.success(
                listCheckShowSubjectResultConstructor(allPeople, isShowSubject));

    }

    @GetMapping("/role/{roles}")
    public ApiResultBean getAllPeopleByRoleID(
            @PathVariable(name = "roles") int roleId,
            @RequestParam(name = "isShowSubject", required = false)
                    Optional<Boolean> isShowSubject
    ) {
        List<People> allPeople = peopleService.getPeopleByRoleId(roleId);

        if (allPeople.isEmpty()) {
            return ApiResultBean.error(
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getMessage("roleId", roleId),
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getErrorCode());
        }

        return ApiResultBean.success(
                listCheckShowSubjectResultConstructor(allPeople, isShowSubject));

    }

    @GetMapping("/id/{id}/")
    public ApiResultBean getAllPeopleByID(
            @PathVariable(name = "id") int id,
            @RequestParam(name = "isShowSubject", required = false)
                    Optional<Boolean> isShowSubject
    ) {

        Optional<People> allPeople = peopleService.getAllPeople(id);

        if (allPeople.isEmpty()) {

            return ApiResultBean.error(
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getMessage("UserID", id),
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getErrorCode());

        }

        return checkShowSubjectResultConstructor(isShowSubject, allPeople);

    }


    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ApiResultBean createOrUpdatePeople(@Valid @RequestBody PeopleDTO peopleDTO){

        People people = peopleMapper.convertToEntity(peopleDTO);

        People newPeople = peopleService.createOrUpdatePeople(people);

        return ApiResultBean.success(newPeople);
    }

    @DeleteMapping(path = "/id/{id}")
    public ApiResultBean deletePerson(@PathVariable(name = "id") int id) {

        if (peopleService.deletePeople(id)) {
            return ApiResultBean.success("User Deleted");
        } else {
            return ApiResultBean.error(
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getMessage("UserID", id),
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getErrorCode());
        }
    }


    private List<?> listCheckShowSubjectResultConstructor(List<People> innerPeopleList
            , Optional<Boolean> innerIsShowSubject) {
        if (innerIsShowSubject.isPresent() && innerIsShowSubject.get()) {
            return innerPeopleList.stream()
                    .map(peopleMapper::convertToDto)
                    .collect(Collectors.toList());
        } else {
            return innerPeopleList.stream()
                    .map(peopleMapper::convertToDto)
                    .peek(p -> p.setBelongedSubjects(new HashSet<>()))
                    .collect(Collectors.toList());
        }
    }

    @NotNull
    private ApiResultBean checkShowSubjectResultConstructor(Optional<Boolean> isShowSubject, Optional<People> allPeople) {
        if (isShowSubject.isPresent() && isShowSubject.get()) {
            return ApiResultBean.successData(peopleMapper.convertToDto(allPeople.get()));
        } else {
            allPeople.get().setBelongedSubjects(new HashSet<>());
            return ApiResultBean.success(peopleMapper.convertToDto(allPeople.get()));
        }
    }


}
