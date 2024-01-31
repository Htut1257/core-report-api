/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.controller;

import cv.report.api.common.ReturnObject;
import cv.report.api.entity.CompanyInfo;
import cv.report.api.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService compService;

    @GetMapping("/get-all-company")
    public Mono<ReturnObject> getallCompany() {
        return compService.findAllComp().collectList()
                .map(item -> {
                    return ReturnObject.<CompanyInfo>builder()
                            .status(200)
                            .message("Success geting Company List")
                            .listData(item)
                            .build();
                });
    }

    @GetMapping("/get-company-compcode")
    public Mono<CompanyInfo> findCompanyByCompCode(@RequestParam String compCode) {
        return compService.findCompById(compCode);
    }

    @PostMapping("/save-company")
    public Mono<CompanyInfo> saveCompany(@RequestBody CompanyInfo comp) {
        return compService.saveCompany(comp);
    }

    @GetMapping("/get-return")
    public Mono<ReturnObject> getreturn() {
        ReturnObject rObj = ReturnObject.builder().status(200).message("success").build();
        return Mono.just(rObj);
    }

}
