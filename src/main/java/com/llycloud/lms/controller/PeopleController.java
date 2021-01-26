package com.llycloud.lms.controller;

import com.llycloud.lms.model.dto.PeopleDTO;
import com.llycloud.lms.model.entity.People;
import com.llycloud.lms.model.enums.PersistentLayerErrorEnum;
import com.llycloud.lms.model.support.ApiResultBean;
import com.llycloud.lms.service.PeopleService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Akide Liu
 * @date 2021-01-16 16:51
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Resource
    private PeopleService peopleService;

    @Autowired
    private ModelMapper modelMapper;


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

        People people = convertToEntity(peopleDTO);

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


    private PeopleDTO convertToDto(People people) {
        return modelMapper.map(people, PeopleDTO.class);
    }

    private People convertToEntity(PeopleDTO peopleDTO) { return modelMapper.map(peopleDTO, People.class); }

    private List<?> listCheckShowSubjectResultConstructor(List<People> innerPeopleList
            , Optional<Boolean> innerIsShowSubject) {
        if (innerIsShowSubject.isPresent() && innerIsShowSubject.get()) {
            return innerPeopleList;
        } else {
            return innerPeopleList.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
    }

    @NotNull
    private ApiResultBean checkShowSubjectResultConstructor(Optional<Boolean> isShowSubject, Optional<People> allPeople) {
        if (isShowSubject.isPresent() && isShowSubject.get()) {
            return ApiResultBean.successData(allPeople);
        } else {
            return ApiResultBean.success(convertToDto(allPeople.get()));
        }
    }


}
