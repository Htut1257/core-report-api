/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import cv.report.api.entity.CompInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author myoht
 */
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final R2dbcEntityTemplate template;
    private final DatabaseClient databaseClient;

    public Flux<CompInfo> findAllComp() {
        return template.select(CompInfo.class).all();
    }

    public Flux<CompInfo> findAll() {
        return databaseClient.sql("select * from comp_info")
                .map(r -> CompInfo.builder().build()).all();
    }

    public Mono<CompInfo> findCompById(String compCode) {
        return template.select(CompInfo.class).matching(Query.query(Criteria.where("comp_code").is(compCode))).one();
    }

}
