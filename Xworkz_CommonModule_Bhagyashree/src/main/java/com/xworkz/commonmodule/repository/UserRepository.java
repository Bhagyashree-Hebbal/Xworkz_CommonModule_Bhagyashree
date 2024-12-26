package com.xworkz.commonmodule.repository;

import com.xworkz.commonmodule.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    boolean save(UserEntity userEntity);

    UserEntity getNameByEmailAndPassword(String email,String password);
    List<UserEntity> getAll(String email, String password);
    Long getCountByName(String name);
    Long getCountByEmail(String email);
    Long getCountByPhone(long phone);
    Long getCountByAlterEmail(String alterEmail);
    Long getCountByAlterPhone(long alterPhone);

    String updatePasswordByName(String newPassword,String name);
    UserEntity findByName(String name);
    UserEntity getEmail(String email);

    boolean updatePasswordAndCount(String name,String confirmPassword,int count);
    void updateCount(String email,int count);
    boolean resetCount(String email,int count);

    UserEntity getByNamePassword(String name,String password);

}

