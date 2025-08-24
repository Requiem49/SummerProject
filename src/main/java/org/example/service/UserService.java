package org.example.service;

import jdk.jshell.spi.ExecutionControl;
import org.example.entity.User;

/**
 * User service interface
 *
 * Defines user-related business operations
 */
public interface UserService {

    /**
     * option1: sign up a new user
     *
     * @param username username of new user
     * @param password password of new user
     * @return id of the created user
     */
    int signup(IdentityParameters identityParameters);

    /**
     * option2: login
     * It’s better that the frontend ensures parameters are not empty
     *
     * @param username username to login
     * @param password password to check
     * @throws UserException if user does not exist or password invalid
     */
    void login(IdentityParameters identityParameters) throws UserException;



}