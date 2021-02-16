package com.llycloud.lms.controller;

import com.llycloud.lms.constants.SecurityConstants;
import com.llycloud.lms.model.dto.JwtUser;
import com.llycloud.lms.model.dto.PeopleDTO;
import com.llycloud.lms.model.dto.UserLoginDTO;
import com.llycloud.lms.model.dto.UserRegisterDTO;
import com.llycloud.lms.model.enums.PersistentLayerErrorEnum;
import com.llycloud.lms.model.support.ApiResultBean;
import com.llycloud.lms.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


/**
 * @author Akide Liu
 * Date : 6/2/21
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<ApiResultBean> login(@Validated @RequestBody UserLoginDTO userLogin) {

        JwtUser jwtUser = authService.authLogin(userLogin);


        if (jwtUser.getUser().getIsActive()) {

            HashMap<String, Object> authInfo = new HashMap<>(2) {{
                put("token", SecurityConstants.TOKEN_PREFIX + jwtUser.getToken());
                put("user", jwtUser.getUser());
            }};


            return new ResponseEntity<>(ApiResultBean.success(authInfo), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(
                    ApiResultBean.error(
                            PersistentLayerErrorEnum.USER_NOT_ACTIVE_ERROR.
                                    getMessage("User Name ", jwtUser.getUser().getName())
                            , PersistentLayerErrorEnum.USER_NOT_ACTIVE_ERROR.getErrorCode())
                    , HttpStatus.OK
            );
        }


    }


    @PostMapping("/logout")
    @ApiOperation(value = "logout")
    public ResponseEntity<ApiResultBean> logout() {
        authService.authLogout();
        return new ResponseEntity<>(ApiResultBean.success(), HttpStatus.OK);
    }

    @PostMapping("/register")
    @ApiOperation(value = "User Register")
    public ResponseEntity<Void> register(@RequestBody UserRegisterDTO userRegister) {
        authService.authRegister(userRegister);
        return ResponseEntity.ok().build();
    }

}
