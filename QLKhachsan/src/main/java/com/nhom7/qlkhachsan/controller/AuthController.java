package com.nhom7.qlkhachsan.controller;

import com.nhom7.qlkhachsan.entity.user.Role;
import com.nhom7.qlkhachsan.entity.user.User;
import com.nhom7.qlkhachsan.repository.RoleRepository;
import com.nhom7.qlkhachsan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.HashSet;

@Controller
@RequestMapping("/")
public class AuthController {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public String registerUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Role role = roleRepository.findByRoleName("ROLE_USER");
        HashSet roles = new HashSet();
        roles.add(role);
        user.setRoles(roles);
        userService.createUser(user);
        return "login";
    }

    @GetMapping("/signup")
    String register(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/login")
    public String login(){
        return "redirect:/";
    }
}
