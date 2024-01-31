/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import cv.report.api.entity.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import static org.springframework.data.relational.core.query.Criteria.where;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * @author myoht
 */
@Service
@RequiredArgsConstructor
public class SequenceService {

    private final R2dbcEntityTemplate template;
    private final DatabaseClient dbClient;

    public Mono<Sequence> getSequence(String option, String period) {
        return template.select(Sequence.class)
                .matching(Query.query(where("seqOption").is(option)))
                .first()
                .flatMap(seq -> {
                    int seqNo = seq.getSeqNo() + 1;
                    seq.setSeqNo(seqNo);
                    return updateSequence(seq);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    return createSequence(option);
                }));
    }

    public Mono<Sequence> createSequence(String option) {
        Sequence seq = Sequence.builder()
                .seqOption(option)
                .period("-")
                .seqNo(1).build();
        return template.insert(seq);
    }

    public Mono<Sequence> updateSequence(Sequence model) {
        return template.update(model);
    }
    
}
