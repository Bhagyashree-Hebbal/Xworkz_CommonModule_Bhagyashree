package com.xworkz.commonmodule.repository;

import com.xworkz.commonmodule.entity.UserEntity;

public interface UserRepository {
    boolean save(UserEntity userEntity);

    String getNameByEmailAndPassword(String email,String password);

    Long getCountByName(String name);
    Long getCountByEmail(String email);
    Long getCountByPhone(long phone);
    Long getCountByAlterEmail(String alterEmail);
    Long getCountByAlterPhone(long alterPhone);
    //Long countByLocation(String location)

//    public UserEntity onSignin(String email);
//
//    public UserEntity getData(String email);
//
//    public boolean onUpdate(UserEntity entity);

    boolean update(UserEntity entity);
    UserEntity findByEmail(String email);

}

