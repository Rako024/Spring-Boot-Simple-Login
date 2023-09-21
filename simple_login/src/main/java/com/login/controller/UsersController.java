package com.login.controller;

import com.login.entity.User;
import com.login.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    public final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login_page")
    public String getLogin(){
        return "login_page";
    }


    @GetMapping("/login")
    public String loginUser(@RequestParam String nickName,@RequestParam String password,Model model){
        User user = userService.loginUser(nickName,password);
        if(user == null){
            model.addAttribute("fail","Nickname or password is incorrect!!!");
            model.addAttribute("isVisible",true);
            return "login_page";
        }else{
            model.addAttribute("success","Login success");
            model.addAttribute("name",user.getName());
            model.addAttribute("nickname",user.getNickName());
            model.addAttribute("isVisible",false);
            model.addAttribute("user",userService.user);
            return "success";
        }

    }

    @GetMapping("/user_edit")
    public String editUser(Model model){
        model.addAttribute("user",userService.user);
        return "user_edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam String newNickName,
                         @RequestParam String newName,
                         @RequestParam String newSurName,
                         @RequestParam short newAge,
                         Model model){
        User user = userService.update(newNickName,newName,newSurName,newAge);
        if(user == null){
            model.addAttribute("isFalseNickname",true);
            return "login_pagekh";
        }else {
            model.addAttribute("isFalseNickname",false);
            return "login_page";
        }
    }



    @GetMapping("updatepass_page")
    public String setPass(){
        return "updatepass_page";
    }

    @PostMapping("/set-password")
    public String updatePassword(@RequestParam String password,
                                 @RequestParam String newPassword,
                                 @RequestParam String againNewPassword,
                                 Model model){

        User user = userService.updatePassword(password,newPassword,againNewPassword);
        if(user == null){
            model.addAttribute("isFalsePassword",true);
            model.addAttribute("passwrodFailMessage","Password Update Failed");
            return "updatepass_page";
        }else{
            model.addAttribute("isSPassword",true);
            model.addAttribute("passwordSuccessMessage","Password Update Success");
            return "login_page";
        }
    }

    @GetMapping("register_page")
    public String getRegistration(){
        return "register_page";
    }

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("firstMessage","HelloSpring");
        return "helloWorld";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam String name,
                           @RequestParam String surName,
                           @RequestParam String nickName,
                           @RequestParam short age,
                           @RequestParam String password,
                           Model model){
        User user = userService.saveUser(name,surName,nickName,age,password);
        if(user == null){
            model.addAttribute("isNickName",true);
            model.addAttribute("registerMessage","Nickname must be unique, there is a user with such a nickname!!!");
            return "register_page";
        }else
             return "index";
    }
}
