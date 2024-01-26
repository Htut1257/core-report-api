/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author myoht
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Back.";
    }

    @GetMapping("/get-user")
    public String getAllUser() {
        return "Hello Back.";
    }
    
    @GetMapping("/save-user")
    public String saveUser() {
        return "Hello Back.";
    }

}
