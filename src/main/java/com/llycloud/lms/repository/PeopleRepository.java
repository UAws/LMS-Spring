package com.llycloud.lms.repository;

import com.llycloud.lms.model.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Akide Liu
 * @date 2021-01-16 16:06
 */

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> , JpaSpecificationExecutor<People> {

    List<People> findByUserLevel(Integer roleId);

}
