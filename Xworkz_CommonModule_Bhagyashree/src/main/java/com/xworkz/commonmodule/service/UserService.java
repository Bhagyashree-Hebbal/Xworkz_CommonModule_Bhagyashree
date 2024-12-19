package com.xworkz.commonmodule.service;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;

public interface UserService {
    boolean save(UserDTO userDTO);

    //String getNameByEmailAndPassword(String email,String password);

    Long countByName(String name);
    Long countByEmail(String email);
    Long countByPhone(long phone);
    Long countByAlterEmail(String alterEmail);
    Long countByAlterPhone(long alterPhone);
    //Long countByLocation(String location);
}
