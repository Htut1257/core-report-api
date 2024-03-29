/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import cv.report.api.common.CommonUtil;
import cv.report.api.entity.Subscription;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
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
public class SubscriptionService {

    private final R2dbcEntityTemplate template;
    private final DatabaseClient dbClient;

    @Autowired
    private SequenceService seqService;

    public Flux<Subscription> getAllSubscribePlan() {
        return template.select(Subscription.class).all();
    }

    public Mono<Subscription> saveSubscribe(Subscription model) {
        String subCode = model.getSubCode();
        String period = CommonUtil.toDateStr(CommonUtil.getTodayDate(), "yyyy");
        if (CommonUtil.isNullOrEmpty(subCode)) {
            return seqService.getSequence("SubPlan", period)
                    .flatMap(seq -> {
                        String seqNo = CommonUtil.generateSequence(5, seq.getSeqNo()) + period;
                        model.setSubCode(seqNo);
                        return insert(model);
                    });
        }
        return update(model);
    }

    public Mono<Subscription> insert(Subscription model) {
        model.setCreatedDate(LocalDateTime.now());
        return template.insert(model);
    }

    public Mono<Subscription> update(Subscription model) {
        model.setUpdatedDate(LocalDateTime.now());
        return template.update(model);
    }

}
