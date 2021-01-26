package com.llycloud.lms.controller;

import com.llycloud.lms.model.dto.SubjectDTO;
import com.llycloud.lms.model.entity.Subject;
import com.llycloud.lms.model.enums.PersistentLayerErrorEnum;
import com.llycloud.lms.model.support.ApiResultBean;
import com.llycloud.lms.service.SubjectService;
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
 * @date 2021-01-17 19:45
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public ApiResultBean getAllSubject() {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        return ApiResultBean.successData(
                allSubjects
                        .stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ApiResultBean getAllSubjectByID(@PathVariable(name = "id") int id) {

        Optional<Subject> allSubjects = subjectService.getSubjectById(id);

        if (allSubjects.isEmpty()) {

            return ApiResultBean.error(
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getMessage("SubjectID", id),
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getErrorCode());

        }

        return ApiResultBean.successData(allSubjects
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList())
        );
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ApiResultBean createOrUpdateSubject(@Valid @RequestBody SubjectDTO subject) {
        Subject newSubject = subjectService.createOrUpdateSubject(convertToEntity(subject));
        return ApiResultBean.success(convertToDto(newSubject));
    }

    @DeleteMapping(path = "/id/{id}")
    public ApiResultBean deleteSubject(@PathVariable(name = "id") int id) {
        if (subjectService.deleteSubject(id)) {
            return ApiResultBean.success("Subject Deleted");
        } else {
            return ApiResultBean.error(
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getMessage("SubjectID", id),
                    PersistentLayerErrorEnum.NO_SUCH_FILED_ERROR.getErrorCode());
        }
    }


    private SubjectDTO convertToDto(Subject subject) {
        return modelMapper.map(subject, SubjectDTO.class);
    }

    private Subject convertToEntity(SubjectDTO subjectDTO) { return modelMapper.map(subjectDTO, Subject.class); }

}
