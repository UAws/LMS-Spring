package com.llycloud.lms.service;

import com.llycloud.lms.exception.UserAlreadyExistsException;
import com.llycloud.lms.model.dto.JwtUser;
import com.llycloud.lms.model.dto.UserLoginDTO;
import com.llycloud.lms.model.dto.UserRegisterDTO;
import com.llycloud.lms.model.entity.People;
import com.llycloud.lms.model.enums.UserLevelEnum;
import com.llycloud.lms.service.mapper.PeopleMapper;
import com.llycloud.lms.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Akide Liu
 * @date 2021-02-05 23:22
 */

@Service
public class AuthService {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PeopleMapper peopleMapper;

    public JwtUser authLogin(UserLoginDTO userLogin) {

        String username = userLogin.getUsername();
        String password = userLogin.getPassword();

        // validate the username, it required to be exist otherwise throw exception
        Optional<People> peopleOptional = peopleService.getUserByName(username);

        if (peopleOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found by username : " + username);
        }

        People people = peopleOptional.get();

        // validate the password and compare with database to be same
        if (this.bCryptPasswordEncoder.matches(password, people.getPassword())) {

            List<String> role = Collections.singletonList(people.getTitle());

            // TODO ROLE need more consideration
            String token = JwtUtils.generateToken(username, role, userLogin.getRememberMe());

            Authentication authentication = JwtUtils.getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new JwtUser(peopleMapper.convertToDto(people),token);

        }

        throw new BadCredentialsException("user name or password not correct ");

    }

    public void authLogout() {
        SecurityContextHolder.clearContext();
    }

    @Transactional(rollbackFor = Exception.class)
    public void authRegister(UserRegisterDTO userRegister)  {

        Optional<People> userOptional = peopleService.getUserByName(userRegister.getUsername());

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("Register failed, the login identifier already exist");
        }

        String encodePassword = bCryptPasswordEncoder.encode(userRegister.getPassword());

        // TODO need a guest role to new registered user
        People people = People.builder()
                .name(userRegister.getUsername())
                .password(encodePassword)
                .userLevel(UserLevelEnum.STUDENT.getValue())
                .title("Student")
                .isActive(true)
                .build();

        peopleService.createOrUpdatePeople(people);


    }


}
