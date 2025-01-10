package com.xworkz.commonmodule.service;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import com.xworkz.commonmodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public Set<ConstraintViolation<UserDTO>> save(UserDTO userDTO) {

        System.out.println("running in service implementation");
        String password = null;
        UserEntity entity = new UserEntity();
        entity.setName(userDTO.getName());
        entity.setEmail(userDTO.getEmail());
        entity.setPhone(userDTO.getPhone());
        entity.setAlterEmail(userDTO.getAlterEmail());
        entity.setAlterPhone(userDTO.getAlterPhone());
        entity.setLocation(userDTO.getLocation());

        entity.setCreatedBy(userDTO.getName());
        entity.setUpdatedBy(userDTO.getName());
        entity.getCreatedDate();
        entity.getUpdatedDate();
        int count = -1;
        if (entity.getEmail() != null) {
            password = generateRandomPassword();
            entity.setPassword(password);
            entity.setCount(count);
        }
        // System.out.println("values" + entity.toString());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<UserDTO>> set = validator.validate(userDTO);
        if (set.isEmpty()) {
            boolean saved = userRepository.save(entity);
            if (saved) {
                saveEmail(userDTO.getEmail(), password);

            }
        }
        return set;
    }

    @Override
    public UserEntity getNameByEmailAndPassword(String email, String password) {
        return userRepository.getNameByEmailAndPassword(email, password);
    }

    @Override
    public List<UserEntity> getAll(String email, String password) {
        List<UserEntity> list = userRepository.getAll(email, password);
        if (list != null) {
            return list;
        }
        return null;
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

    @Override
    public String updatePasswordByName(String name, String oldPassword, String newPassword, String confirmPassword) {

        if (newPassword.equals(confirmPassword)) {
            String msg = userRepository.updatePasswordByName(newPassword, name);

        }
        return "password updated successfully";
    }

    @Override
    public UserEntity getEmail(String email, String password) {
        UserEntity entity = userRepository.getEmail(email);
        if (entity != null) {
            System.out.println(entity.toString());
            if (password.equals(entity.getPassword()) && entity.getCount() == -1) {
                System.out.println("matches");
                return entity;
            } else if (!(password.equals(entity.getPassword())) && (entity.getCount() >= 0 && entity.getCount() < 3)) {

                repository.updateCount(email, entity.getCount());
                System.out.println("password entered is wrong");
                return null;

            } else if (!(password.equals(entity.getPassword())) && entity.getCount() == 3) {
                System.out.println("locked");
                if(entity.getAccountLockedTime()==null)
                    repository.updateLockedAccountTimeByEmail(email);
                return null;
            } else if (password.equals(entity.getPassword()) && (entity.getCount() < 3 && entity.getCount() > -1)) {
                boolean reset = repository.resetCount(email, entity.getCount());
                if (reset)
                    return entity;
                else
                    return null;
            }
        }
        return null;
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

    @Override
    public boolean saveEmail(String email, String password) {

        System.out.println(email + password);
        final String username = "hebbalbhagya304@gmail.com";
        final String userPassword = "ukxf fmhi hjte qaes";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        System.out.println("=================================================");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, userPassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Your password");
            message.setText("your password : " + password);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return true;
    }
    @Override
    public Set<ConstraintViolation<UserDTO>> updateDetails(String name,UserDTO userDTO,String filePath) {

        if(userDTO!=null)
        {
            userDTO.setName(name);
            System.out.println(userDTO.toString());

            ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
            Validator validator = vf.getValidator();
            Set<ConstraintViolation<UserDTO>> set = validator.validate(userDTO);
            if(set.isEmpty())
            {
                boolean updated=repository.updateDetails( name,userDTO,filePath);

            }
            return set;
        }

        return null;
    }

    @Override
    public String resetPasswordByEmail(String email, String newPassword, String confirmPassword) {
        System.out.println("reset password in service");
        if(newPassword.equals(confirmPassword)){
            userRepository.getEmail(email);
            return userRepository.resetPasswordByEmail(email, newPassword);
        }
        return "password updated successfully";
    }

}

















