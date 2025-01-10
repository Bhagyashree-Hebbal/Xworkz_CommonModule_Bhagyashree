package com.xworkz.commonmodule.controller;

import com.xworkz.commonmodule.constants.LocationEnum;
import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import com.xworkz.commonmodule.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("/signIn")
    public String onSearch(@RequestParam String email, @RequestParam String password, Model model) {

        System.out.println(email + " " +password);
        UserEntity userEntity = userService.getEmail(email, password);

        int logInCount;

        if (userEntity != null) {
            logInCount = userEntity.getCount();
            System.out.println("Login Count: " + logInCount);

            if (logInCount == -1) {
                System.out.println("Redirecting to UpdatePassword page.");
                String name = userEntity.getName();
                model.addAttribute("userName", name);
                return "UpdatePassword";
            } else {
                System.out.println("Redirecting to Success page.");
                String name = userEntity.getName();
                model.addAttribute("userName", name);
                model.addAttribute("filePath",userEntity.getFilePath());
                return "Success";
            }
        }
        System.out.println("User entity is null. Redirecting to SignIn.");
        return "SignIn";

    }


    @PostMapping("/updateProfile")
    public String onUpdating(@RequestParam String name, UserDTO userDTO, @RequestParam("picture") MultipartFile multipartFile, Model model) throws IOException {
        System.out.println(name);

        if (multipartFile.isEmpty()) {
            // Add the name to the model to pass it to the Success page
            Set<ConstraintViolation<UserDTO>> set = userService.updateDetails(name, userDTO,null);
           // model.addAttribute("userName", name);
            if(set.isEmpty()){
                return "Success";
            }
        }else{
            System.out.println("multipartFile="+multipartFile);
            System.out.println("multipartFile OriginalFileName=="+multipartFile.getOriginalFilename());
            System.out.println("multipartFile=="+multipartFile.getContentType());

            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get("C:\\fileUpload\\" + name + System.currentTimeMillis() + ".jpg");
            Files.write(path, bytes);
            String filePath = path.getFileName().toString();
            System.err.println("filePath=====" + filePath);

            Set<ConstraintViolation<UserDTO>> set = userService.updateDetails(name, userDTO,filePath);
            if(set.isEmpty()){
                return "Success";
            }
        }
        return "UpdateProfile";
    }

    @GetMapping("/download")
    public void display(HttpServletResponse response,@RequestParam String filePath) throws  Exception{
        System.out.println("this is image"+filePath);
        response.setContentType("Image/jpg");
        File file = new File("C:\\fileUpload\\" + filePath);
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);
        response.flushBuffer();

    }

    @PostMapping("/resetPassword")
    public String forgotPassword(@RequestParam String email,@RequestParam String newPassword,@RequestParam String confirmPassword){
        System.out.println("Received request for resetting password:");
        System.out.println("Email:"+email);
        System.out.println("New Password:" + newPassword);
        System.out.println("ConfirmPassword:" + confirmPassword);

        String response = userService.resetPasswordByEmail(email,newPassword,confirmPassword);

        if("password updated successfully".equals(response)) {
            return "Success";
        }else{
            return "ForgotPassword";
        }
    }
}
