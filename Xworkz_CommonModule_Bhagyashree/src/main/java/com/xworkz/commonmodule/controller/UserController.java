package com.xworkz.commonmodule.controller;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import com.xworkz.commonmodule.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;

    UserController(){
        System.out.println("No-arg const in UserController");
    }

    @PostMapping("/signup")
    public String onSave(UserDTO userDTO, Model model){
        System.out.println("running in service ");
        System.out.println(userDTO);
        boolean saved=userService.save(userDTO);
       // model.addAttribute("msg","SignUp Success");
        //return "SignUp";
        if(saved){
            return "Success";
        }else{
            return "SignUp";
        }
    }

    @PostMapping("/signin")
    public String onSearch(@RequestParam String email,@RequestParam String password,Model model){
        log.info(email + " " +password);
        List<UserEntity> list = userService.getAll(email, password);
        int count=0;
        String userName = null;
        for (UserEntity data : list){
            count = data.getCount();
            log.info("data.getCount()="+data.getCount());
            userName = data.getName();
        }

        log.info("valid=="+count);
        if(count == -1){
            model.addAttribute("userName",userName);
            model.addAttribute("msg","Not Matched");
            return "UpdatePassword";
        }else{

            model.addAttribute("msg","Matched");
            return "Success";
        }

    }

}
