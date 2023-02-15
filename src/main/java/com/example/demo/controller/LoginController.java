package com.example.demo.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Controller
@RequestMapping
@Slf4j
public class LoginController {

    @GetMapping("/registeration")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user")
    public String checkUser(@ModelAttribute("user") @Validated User user, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "user";
        } else {
            Subject subject = SecurityUtils.getSubject();

            if (!subject.isAuthenticated()) {
                log.info("the user is ", user.getName());
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                        user.getName(), "");
                usernamePasswordToken.setRememberMe(true);
                subject.login(usernamePasswordToken);
                return "redirect:/posts";
            }
            return "user";
        }

    }

}