/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author myoht
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

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
    
    //user login and generate token
    @GetMapping(value = "/login")
    @ResponseBody
    public String loginUser() throws Exception {
        return "Testing admin role.";
    }
    
}
