/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import cv.report.api.common.CommonUtil;
import cv.report.api.entity.SubscriptionPlan;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

/**
 *
 * @author myoht
 */
@Service
@RequiredArgsConstructor
public class SubscriptionPlanService {

    private final R2dbcEntityTemplate template;
    private final DatabaseClient dbClient;

    @Autowired
    private SequenceService seqService;

    public Flux<SubscriptionPlan> getAllSubPlan() {
        return template.select(SubscriptionPlan.class).all();
    }

    public Mono<SubscriptionPlan> getSubPlan(String planName) {
        return template.select(SubscriptionPlan.class)
                .matching(Query.query(Criteria.where("sub_plan_name").is(planName))).one();
    }

    public Mono<SubscriptionPlan> saveSubPlan(SubscriptionPlan model) {
        String planCode = model.getSubPlanCode();
        String period = CommonUtil.toDateStr(CommonUtil.getTodayDate(), "MMyyyy");
        if (CommonUtil.isNullOrEmpty(planCode)) {
            return seqService.getSequence("SubPlan", period)
                    .flatMap(seq -> {
                        String seqNo = CommonUtil.generateSequence(5, seq.getSeqNo());
                        model.setSubPlanCode(seqNo);
                        return insertSubPlan(model);
                    });
        }
        return updateSubPlan(model);
    }

    public Mono<SubscriptionPlan> insertSubPlan(SubscriptionPlan model) {
        model.setCreatedDate(LocalDateTime.now());
        return template.insert(model);
    }

    public Mono<SubscriptionPlan> updateSubPlan(SubscriptionPlan model) {
        model.setUpdatedDate(LocalDateTime.now());
        return template.update(model);
    }

}
