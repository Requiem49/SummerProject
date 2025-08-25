package org.example.service;

import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.lang3.StringUtils;
import org.example.entity.User;
import org.example.repository.UserRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepo userRepo;


    /**
     * 1. validate input, 1.1 if valid password
     * 2. check user exists
     * 3. persistence
     *
     * @param identityParameters  用户注册输入参数 (用户名, 密码, 邮箱, 电话)
     * @return 注册结果
     */
    @Override
    public int signup(IdentityParameters identityParameters) {
        String username = identityParameters.getName();
        String password = identityParameters.getPasswd();
        String email    = identityParameters.getEmail();
        String phone    = identityParameters.getPhone();

        // 校验输入是否合法
        boolean isPhoneValid     = StringUtils.isNumeric(phone);  // 电话是否全为数字
        boolean isEmailValid     = StringUtils.containsAny(email, "@"); // 邮箱是否包含@
        boolean isPasswdValid    = StringUtils.length(password) > 5; // 密码长度 > 5
        boolean isUserNameValid  = !StringUtils.isEmpty(username); // 用户名非空

        // 是否有空字段


        if(!isPhoneValid){
            return -6;
        }

        if(!isEmailValid){
            return -5;
        }

        if(!isPasswdValid){
            return -3;
        }

        if(!isUserNameValid){
            return -4;
        }

        boolean isSameEmailFound = userRepo.findUserByEmail(email) != null;
        boolean isSamePhoneFound = userRepo.findUserByPhone(phone) != null;

        if(isSameEmailFound || isSamePhoneFound){
            return -2;
        }

        User newUser = new User();
        newUser.setName(username);
        newUser.setName(password);
        newUser.setPhone(phone);
        newUser.setEmail(email);

        userRepo.save(newUser);

        return 0;  // 暂时固定返回 0，后面可替换为实际逻辑
    }

    @Override
    public void login(IdentityParameters identityParameters) throws UserException {
        String password = identityParameters.getPasswd();
        String email = identityParameters.getEmail();
        String phone = identityParameters.getPhone();

        boolean invalidPasswd = StringUtils.isBlank( password);
        boolean invalidId = StringUtils.isAllBlank(email, password);

        if (invalidPasswd || invalidId) {
            throw new UserException.InvalidParameterException("parameters invalid");
        }

        User userByEmail = userRepo.findUserByEmail(email);
        User userByPhone = userRepo.findUserByEmail(phone);
        if (null == userByEmail && null == userByPhone) {
            throw new UserException.UserNotFoundException("User " + email +" " + phone+ " does not exist");
        }



        if (userByEmail != null &&!password.equals(userByEmail.getPasswd())) {
            throw new UserException.InvalidPasswordException("Passwords do not match");
        }
        if (userByPhone != null &&!password.equals(userByPhone.getPasswd())) {
            throw new UserException.InvalidPasswordException("Passwords do not match");
        }
    }


}



