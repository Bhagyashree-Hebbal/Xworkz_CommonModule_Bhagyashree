package com.xworkz.commonmodule.restcontroller;

import com.xworkz.commonmodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onName(@PathVariable String name){
        System.out.println("name=" + name);
        Long count = this.userService.getCountByName(name);
        if (count == 0) {
            System.out.println("does not exist");
            return "not exist";
        } else {
            System.out.println("exists");
            return "exists";
        }
        //return null;
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onEmail(@PathVariable String email) {
        System.out.println("email=" + email);
        Long count = this.userService.getCountByEmail(email);
        if (count == 0) {
            System.out.println("does not exist");
            return "not exist";
        } else {
            System.out.println("exists");
            return "exists";
        }
        //return null;
    }

    @GetMapping(value = "/phone/{phone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onPhone(@PathVariable long phone) {
        System.out.println("phone=" + phone);
        Long count = this.userService.getCountByPhone(phone);
        if (count == 0) {
            System.out.println("does not exist");
            return "not exist";
        } else {
            System.out.println("exists");
            return "exists";
        }
        //return null;
    }

    @GetMapping(value = "/alterEmail/{alterEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onAlterEmail(@PathVariable String alterEmail) {
        System.out.println("alterEmail=" + alterEmail);
        Long count = this.userService.getCountByAlterEmail(alterEmail);
        if (count == 0) {
            System.out.println("does not exist");
            return "not exist";
        } else {
            System.out.println("exists");
            return "exists";
        }
        //return null;
    }

    @GetMapping(value = "/alterPhone/{alterPhone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onAlterPhone(@PathVariable long alterPhone) {
        System.out.println("alterPhone=" + alterPhone);
        Long count = this.userService.getCountByAlterPhone(alterPhone);
        if (count == 0) {
            System.out.println("does not exist");
            return "not exist";
        } else {
            System.out.println("exists");
            return "exists";
        }
        //return null;
    }

}
