package com.xworkz.commonmodule.controller;

import com.xworkz.commonmodule.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/setPasswordController")
@Slf4j
public class UpdatePasswordController {
    @Autowired
    public UserService userService;

    @PostMapping("/updatePassword")
    public String setNewPassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword, @RequestParam String name) {

        log.info("userName==" + name);
        log.info("oldPassword==" + newPassword);
        log.info("confirmPassword==" + confirmPassword);

        String msg = userService.updatePasswordByName(newPassword, confirmPassword,name);

        if ("password updated successfully".equals(msg)) {
            return "Success";
        }
        else {
            return "index";
        }

    }

}


