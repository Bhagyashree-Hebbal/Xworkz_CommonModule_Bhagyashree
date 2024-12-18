package com.xworkz.commonmodule.repository;

import com.xworkz.commonmodule.entity.UserEntity;

public interface UserRepository {
    boolean save(UserEntity userEntity);

   // String getNameByEmailAndPassword(String email,String password);
}
