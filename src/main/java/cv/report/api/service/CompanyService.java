/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import cv.report.api.common.CommonUtil;
import cv.report.api.entity.CompanyInfo;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author myoht
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final R2dbcEntityTemplate template;
    private final DatabaseClient databaseClient;

    @Autowired
    private SequenceService seqService;

    public Flux<CompanyInfo> findAllComp() {
        return template.select(CompanyInfo.class).all();
    }

    public Flux<CompanyInfo> findAll() {
        return databaseClient.sql("select * from comp_info")
                .map(r -> CompanyInfo.builder().build()).all();
    }

    public Mono<CompanyInfo> findCompById(String compCode) {
        return template.select(CompanyInfo.class)
                .matching(Query.query(Criteria.where("comp_code").is(compCode)))
                .one();
    }

    public Mono<CompanyInfo> saveCompany(CompanyInfo model) {
        String compCode = model.getCompCode();
        String period = CommonUtil.toDateStr(CommonUtil.getTodayDate(), "MMyyyy");
        model.setUpdateDate(LocalDateTime.now());
        if (CommonUtil.isNullOrEmpty(compCode)) {
            return seqService.getSequence("Company", "-")
                    .flatMap(seq -> {
                        String seqNo = CommonUtil.generateSequence(5,seq.getSeqNo());
                        model.setCompCode(seqNo);
                        return insert(model);
                    });
        }
        return update(model);
    }

    public Mono<CompanyInfo> insert(CompanyInfo model) {
        return template.insert(model);
    }

    public Mono<CompanyInfo> update(CompanyInfo model) {
        return template.update(model);
    }

}
