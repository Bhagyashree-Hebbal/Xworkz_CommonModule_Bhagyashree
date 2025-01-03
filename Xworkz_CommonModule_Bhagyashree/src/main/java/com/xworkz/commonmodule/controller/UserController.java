package com.xworkz.commonmodule.controller;

import com.xworkz.commonmodule.constants.LocationEnum;
import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import com.xworkz.commonmodule.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    UserController(){
        System.out.println("No-arg const in UserController");
    }

    @GetMapping("/sup")
    public String onSignUp(Model model)
    {
        List<LocationEnum> listoflocation = new ArrayList<>(Arrays.asList(LocationEnum.values()));
        listoflocation.forEach(n-> System.out.println(n));
        model.addAttribute("listoflocation",listoflocation);
        return "SignUp";
    }

    @GetMapping("/update")
    public String onUpdateProfile(@RequestParam(required = false) String name, Model model)
    {
        System.out.println(name);
        List<LocationEnum> listoflocation = new ArrayList<>(Arrays.asList(LocationEnum.values()));
       listoflocation.forEach(n-> System.out.println(n));
        System.out.println(name);
       model.addAttribute("userName",name);
       model.addAttribute("listoflocation",listoflocation);

        return "UpdateProfile";
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
    public String onSearch(@RequestParam String email, @RequestParam String password, Model model) {
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
                String name=userEntity.getName();
                model.addAttribute("userName",name);
                return "Success";
            }
        }
        return "SignIn";

    }


    @PostMapping("/updateProfile")
    public String onUpdating(@RequestParam String name, UserDTO userDTO, Model model) {
        System.out.println(name);
        Set<ConstraintViolation<UserDTO>> set = userService.updateDetails(name, userDTO);
        if (set.isEmpty()) {
            // Add the name to the model to pass it to the Success page
            model.addAttribute("userName", name);
            return "Success";
        }
        return "UpdateProfile";
    }


}
