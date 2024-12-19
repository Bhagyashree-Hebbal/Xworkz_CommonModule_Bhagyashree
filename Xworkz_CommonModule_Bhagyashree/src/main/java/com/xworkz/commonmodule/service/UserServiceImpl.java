package com.xworkz.commonmodule.service;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import com.xworkz.commonmodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean save(UserDTO userDTO) {

            System.out.println("rinning in service implementation");

        String password =generateRandomPassword();
        UserEntity entity=new UserEntity();
        entity.setName(userDTO.getName());
        entity.setEmail(userDTO.getEmail());
        entity.setPhone(userDTO.getPhone());
        entity.setAlterEmail(userDTO.getAlterEmail());
        entity.setAlterPhone(userDTO.getAlterPhone());
        entity.setLocation(userDTO.getLocation());
        entity.setPassword(password);
        System.out.println("values"+entity.toString());
        boolean saved=userRepository.save(entity);
        return true;
    }

    @Override
    public Long countByName(String name) {
        Long count = userRepository.countByName(name);
        return count;
    }

    @Override
    public Long countByEmail(String email) {
        return userRepository.countByEmail(email);
    }

    @Override
    public Long countByPhone(long phone) {
        return userRepository.countByPhone(phone);
    }

    @Override
    public Long countByAlterEmail(String alterEmail) {
        return userRepository.countByAlterEmail(alterEmail);
    }

    @Override
    public Long countByAlterPhone(long alterPhone) {
        return userRepository.countByAlterPhone(alterPhone);
    }


    public static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

}
