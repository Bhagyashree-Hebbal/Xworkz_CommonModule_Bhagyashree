package com.xworkz.commonmodule.service;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import com.xworkz.commonmodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        entity.setCount(-1);
        System.out.println("values"+entity.toString());
        boolean saved=userRepository.save(entity);
        return true;
    }

    @Override
    public String getNameByEmailAndPassword(String email, String password) {
        return userRepository.getNameByEmailAndPassword(email, password);
    }

    @Override
    public Long getCountByName(String name) {
        Long count = userRepository.getCountByName(name);
        return count;
    }

    @Override
    public Long getCountByEmail(String email) {
        return userRepository.getCountByEmail(email);
    }

    @Override
    public Long getCountByPhone(long phone) {
        return userRepository.getCountByPhone(phone);
    }

    @Override
    public Long getCountByAlterEmail(String alterEmail) {
        return userRepository.getCountByAlterEmail(alterEmail);
    }

    @Override
    public Long getCountByAlterPhone(long alterPhone) {
        return userRepository.getCountByAlterPhone(alterPhone);
    }

//    @Override
//    public String onSignin(String email, String password) {
//        System.out.println("running onSignin in userServiceImpl");
//        UserEntity entity = userRepository.;
//        if(entity ! =nul){
//            if(encoderPassword.matches(password,entity.getPassword())){
//                return entity.getName();
//            }
//            return null;
//        }
//        return null;
//    }

    @Override
    public boolean onUpdate(String email,String oldPassword, String newPassword) {
        System.out.println("SERVICE :on update method : " + email);
        UserEntity entity = userRepository.findByEmail(email);
            if (entity != null) {
                if(entity.getPassword().equals(oldPassword)){
                    String encryptedPassword = passwordEncoder.encode(newPassword);

                    entity.setPassword(encryptedPassword);
                    entity.setCount(0);

                    return userRepository.update(entity);
            }
            return false;
        }
        return false;
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
