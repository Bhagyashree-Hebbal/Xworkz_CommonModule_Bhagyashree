package com.xworkz.commonmodule.controller;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
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
        model.addAttribute("msg","SignUp Success");
        return "SignUp";
    }

//    @PostMapping("/signin")
//   public String onSearch(@RequestParam String email,@RequestParam String password,Model model){
//        String valid = userService.getNameByEmailAndPassword(email, password);
//        model.addAttribute("msg",valid);
//        return "SignIn.jsp";
//    }
}
