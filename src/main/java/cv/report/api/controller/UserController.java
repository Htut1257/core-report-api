/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.controller;

import cv.report.api.entity.Register;
import cv.report.api.entity.User;
import cv.report.api.service.RegisterService;
import cv.report.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author myoht
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService regService;

    @GetMapping("/get-all-user")
    public Flux<User> getAllUser() {
        return userService.getUser();
    }

    @PostMapping("/save-user")
    public Mono<User> saveUser(@RequestBody User model) {
        return userService.saveUser(model);
    }

    @GetMapping("/get-register")
    public Flux<Register> getAllRegisterUser() {
        return regService.getRegisterUser();
    }

    @PostMapping("save-register")
    public Mono<Register> saveRegister(@RequestBody Register model) {
        return regService.saveRegisterUser(model);
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public String test() throws Exception {
        return "Testing Successful.";
    }

    @GetMapping(value = "/excludedAuthPages")
    @ResponseBody
    public String excludedAuthPages() throws Exception {
        return "Testing excludedAuthPages.";
    }

    @GetMapping(value = "/admin")
    @ResponseBody
    public String admin() throws Exception {
        return "Testing admin role.";
    }

}
