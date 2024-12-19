package com.xworkz.commonmodule.repository;

import com.xworkz.commonmodule.entity.UserEntity;

public interface UserRepository {
    boolean save(UserEntity userEntity);

   // String getNameByEmailAndPassword(String email,String password);

    Long countByName(String name);
    Long countByEmail(String email);
    Long countByPhone(long phone);
    Long countByAlterEmail(String alterEmail);
    Long countByAlterPhone(long alterPhone);
    //Long countByLocation(String location)
}
