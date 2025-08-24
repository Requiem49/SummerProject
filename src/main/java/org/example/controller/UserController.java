package org.example.controller;

import jdk.jshell.spi.ExecutionControl;
import org.example.service.IdentityParameters;
import org.example.service.UserException;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;



@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(path = "/user/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password,
    @RequestParam("phone") String phone, @RequestParam("email") String email) {
        String result = null;
        IdentityParameters identityParameters = new IdentityParameters();
        identityParameters.setName(username);
        identityParameters.setPasswd(password);
        identityParameters.setPhone(phone);
        identityParameters.setEmail(email);


        int signupCode = userService.signup(identityParameters);
        switch (signupCode) {
            case 0:
                result = "success";
                break;
            case -1:
                result = "param error";
                break;
            case -2:
                result = "user exists";
                break;
            case -3:
                result = "password not valid";
                break;
            case -4:
                result = "username not valid";
                break;
            case -5:
                result = "email not valid";
                break;
            case -6:
                result = "phone not valid";
                break;
        }
        return result;
    }

    @PostMapping(path = "/user/login")
    public Resp login(@RequestParam("username") String username,
                      @RequestParam("password") String password) {
        String result = null;
        try {
            userService.login(new IdentityParameters());
            result = "success";
        } catch (UserException userException) {
            userException.printStackTrace();
            result = "failure";
        }
        return Resp.newInstance(0, result);
    }
}

