package com.xworkz.commonmodule.controller;

import com.xworkz.commonmodule.constants.LocationEnum;
import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import com.xworkz.commonmodule.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;

    private List<LocationEnum> listoflocation = new ArrayList<>(Arrays.asList(LocationEnum.values()));

    UserController(){
        System.out.println("No-arg const in UserController");
    }

    @GetMapping("/sup")
    public String onSignUp(Model model)
    {
        listoflocation.forEach(n-> System.out.println(n));
        model.addAttribute("listoflocation",listoflocation);
        return "SignUp";
    }


    @PostMapping("/signup")
    public String onSave(UserDTO userDTO, Model model){
        System.out.println("running in service ");
       // System.out.println(userDTO.getLocation());
        Set<ConstraintViolation<UserDTO>> constraintViolations=userService.save(userDTO);
        if(constraintViolations.isEmpty()){
            model.addAttribute("msg","SignUp Success");
            return "Success";
        }else{
            model.addAttribute("error",constraintViolations);
            return "SignUp";
        }
    }

    @PostMapping("/signin")
    public String onSearch(@RequestParam String email,@RequestParam String password,Model model){
        System.out.println(email + " " +password);
        UserEntity userEntity = userService.getEmail(email, password);

        if(userEntity != null) {
            int count=userEntity.getCount();
            System.out.println(count);

            if(count==-1)
            {
                String name=userEntity.getName();

                model.addAttribute("userName",name);
                return "UpdatePassword";
            }
            else
            {
                return "Success";
            }
        }
        return "SignIn";

    }

}
