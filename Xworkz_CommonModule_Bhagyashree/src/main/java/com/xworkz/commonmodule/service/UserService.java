package com.xworkz.commonmodule.service;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;

import java.util.List;

public interface UserService {
    boolean save(UserDTO userDTO);

    UserEntity getNameByEmailAndPassword(String email,String password);
    List<UserEntity> getAll(String email,String password);
    Long getCountByName(String name);
    Long getCountByEmail(String email);
    Long getCountByPhone(long phone);
    Long getCountByAlterEmail(String alterEmail);
    Long getCountByAlterPhone(long alterPhone);

    String updatePasswordByName(String name, String oldPassword, String newPassword,String confirmPassword);
    UserEntity getEmail(String email,String password);

    boolean saveEmail(String email,String password);

}
