package com.login.service;

import com.login.entity.User;
import com.login.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    public User user;
    public final UserRepository  userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String name, String surName, String nickName, short age,String password){
        if(!isNickName(nickName)) {
            User user = new User();
            user.setName(name);
            user.setSurName(surName);
            user.setNickName(nickName);
            user.setAge(age);
            user.setPassword(password);
            userRepository.save(user);
            return user;
        }else
            return null;
    }

    public User updatePassword(String password, String newPassword, String againNewPassword){
        User updateUser = this.user;
        if(updateUser.getPassword().equals(password) && newPassword.equals(againNewPassword)){
            updateUser.setPassword(againNewPassword);
            userRepository.save(updateUser);
            return updateUser;
        }else {
            return null;
        }
    }

    public User update( String newNickName, String newName, String newSurName, short newAge){
        User updateUser = this.user;
        if(!isNickName(newNickName) || newNickName.equals(updateUser.getNickName())){
            updateUser.setNickName(newNickName);
            updateUser.setName(newName);
            updateUser.setSurName(newSurName);
            updateUser.setAge(newAge);
            userRepository.save(updateUser);
            System.out.println("isledi");
            return updateUser;
        }else
            return null;
    }

    public boolean isNickName(String nickName){
        List<User> users = userRepository.findAll();
        boolean is = false;
        for (User user:users) {
            if(user.getNickName().equals(nickName)){
                is=true;
            }
        }
        return is;
    }
    public User loginUser(String nickName, String password){
        if(isNickName(nickName)) {
            List<User> users = userRepository.findByNickName(nickName);
            User user = users.get(users.size() - 1);
            if (user.getPassword().equals(password)) {
                this.user = user;
                return user;
            } else
                return null;
        }else
            return null;
    }
}
