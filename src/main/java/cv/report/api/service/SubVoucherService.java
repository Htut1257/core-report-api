/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import cv.report.api.common.CommonUtil;
import cv.report.api.entity.SubVou;
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
public class SubVoucherService {

    private final R2dbcEntityTemplate template;
    private final DatabaseClient dbClient;

    @Autowired
    private SequenceService seqService;

    Flux<SubVou> searchSubVoucher() {
        return Flux.just(null);
    }

    Mono<SubVou> saveSubVou(SubVou model) {
        String subCode = model.getSubPlanCode();
        String period = CommonUtil.toDateStr(CommonUtil.getTodayDate(), "MMyyyy");
        if (CommonUtil.isNullOrEmpty(subCode)) {
            return seqService.getSequence("SubVou", period)
                    .flatMap(seq -> {
                        String seqNo = CommonUtil.generateSequence(5, seq.getSeqNo());
                        return insertSubVou(model);
                    });
        }
        return updateSubVou(model);
    }

    Mono<SubVou> insertSubVou(SubVou model) {
        model.setCreatedDate(LocalDateTime.now());
        return template.insert(model);
    }

    Mono<SubVou> updateSubVou(SubVou model) {
        model.setUpdatedDate(LocalDateTime.now());
        return template.update(model);
    }

}
