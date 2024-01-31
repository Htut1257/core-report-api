/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import cv.report.api.entity.Register;
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
public class RegisterService {

    private final R2dbcEntityTemplate template;
    private final DatabaseClient dbClient;

    public Flux<Register> getRegisterUser() {
        return template.select(Register.class).all();
    }

    public Mono<Register> findByPhoneNo(String phNo) {
        return template.select(Register.class)
                .matching(Query.query(Criteria.where("phone_no").is(phNo))).one();
    }

    public Mono<Register> saveRegisterUser(Register model) {
        return findByPhoneNo(model.getPhoneNo())
                .flatMap(reg -> template.update(model))
                .switchIfEmpty(Mono.defer(() -> {
                    return template.insert(model);
                }));
    }

}
