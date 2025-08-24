package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {


    // 根据手机号查找
    User findUserByPhone(String phone);

    User findUserByEmail(String email);
}
