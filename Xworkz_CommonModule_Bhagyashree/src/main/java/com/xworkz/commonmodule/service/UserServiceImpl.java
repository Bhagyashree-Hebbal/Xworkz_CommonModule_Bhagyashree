package com.xworkz.commonmodule.service;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import com.xworkz.commonmodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean save(UserDTO userDTO) {

        System.out.println("running in service implementation");

        String password = null;
        UserEntity entity = new UserEntity();
        entity.setName(userDTO.getName());
        entity.setEmail(userDTO.getEmail());
        entity.setPhone(userDTO.getPhone());
        entity.setAlterEmail(userDTO.getAlterEmail());
        entity.setAlterPhone(userDTO.getAlterPhone());
        entity.setLocation(userDTO.getLocation());
        int count = -1;
        if (entity.getEmail() != null) {
            password = generateRandomPassword();
            entity.setPassword(password);
            entity.setCount(count);
        }
        // System.out.println("values" + entity.toString());
        boolean saved = userRepository.save(entity);
        if (saved) {
            return true;
        } else {
            return false;
        }
    }

        @Override
        public UserEntity getNameByEmailAndPassword (String email, String password){
            return userRepository.getNameByEmailAndPassword(email, password);
        }

        @Override
        public List<UserEntity> getAll (String email, String password){
            List<UserEntity> list = userRepository.getAll(email, password);
            if (list != null) {
                return list;
            }
            return null;
        }

        @Override
        public Long getCountByName (String name){
            Long count = userRepository.getCountByName(name);
            return count;
        }

        @Override
        public Long getCountByEmail (String email){
            return userRepository.getCountByEmail(email);
        }

        @Override
        public Long getCountByPhone ( long phone){
            return userRepository.getCountByPhone(phone);
        }

        @Override
        public Long getCountByAlterEmail (String alterEmail){
            return userRepository.getCountByAlterEmail(alterEmail);
        }

        @Override
        public Long getCountByAlterPhone ( long alterPhone){
            return userRepository.getCountByAlterPhone(alterPhone);
        }

        @Override
        public String updatePasswordByName (String newPassword, String confirmPassword, String name){

            if (newPassword.equals(confirmPassword)) {
                //String encodedNewPassword = passwordEncoder.encode(newPassword);
                String msg = userRepository.updatePasswordByName(newPassword, name);
            }
            return "password updated successfully";
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
