/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.controller;

import cv.report.api.entity.CompInfo;
import cv.report.api.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * @author myoht
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService compService;

    @GetMapping("/get-all-company")
    public Flux<CompInfo> getallCompany() {
        return compService.findAllComp();
    }

}
