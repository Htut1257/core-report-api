/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import cv.report.api.common.CommonUtil;
import cv.report.api.entity.User;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.time.LocalDateTime;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

import java.util.function.BiFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * @author myoht
 */
@Service
@RequiredArgsConstructor
public class UserService {

    //private final R2dbcEntityTemplate template;
    private final DatabaseClient dbClient;

    @Autowired
    private SequenceService seqService;

    //mapping database data for estimated object
    private static final BiFunction<Row, RowMetadata, User> Mapping
            = (row, metaData) -> User.builder()
                    .userCode(row.get("user_code", String.class))
                    .userName(row.get("user_name", String.class))
                    .userShortName(row.get("user_short_name", String.class))
                    .phoneNo(row.get("phone", String.class))
                    .email(row.get("email", String.class))
                    .address(row.get("address", String.class))
                    .active(row.get("active", Boolean.class))
                    .status(row.get("status", Integer.class))
                    .build();

    public Flux<User> getUser() {
        String query = "SELECT * FROM app_user";
        return dbClient.sql(query)
                .map(Mapping)
                .all();
    }

    public Mono<User> getUserByPhEmail(String phno, String email) {
        String query = "SELECT * FROM app_user WHERE phone=:ph AND (email = :email OR :email = '-')";
        return dbClient.sql(query)
                .bind("ph", phno)
                .bind("email", email)
                .map(Mapping)
                .one();
    }

    public Mono<User> saveUser(User model) {
        String userCode = model.getUserCode();
        String period = CommonUtil.toDateStr(CommonUtil.getTodayDate(), "MMyyyy");
        model.setCreatedDate(LocalDateTime.now());
        model.setUpdatedDate(LocalDateTime.now());
        if (CommonUtil.isNullOrEmpty(userCode)) {
            return seqService.getSequence("User", period)
                    .flatMap(seq -> {
                        String seqNo = CommonUtil.generateSequence(5, seq.getSeqNo());
                        model.setUserCode(seqNo);
                        return insert(model);
                    });
        }
        return update(model);
    }

    public Mono<User> insert(User model) {
        String qurey = "INSERT INTO app_user (user_code,user_name,user_short_name,phone,email,password,active,status,createdDate)"
                + "VALUES (:code,:name,:shortName,:phone,:email,:password,:active,:status,:create) ";
        return dbClient.sql(qurey)
                .bind("code", model.getUserCode())
                .bind("name", model.getUserName())
                .bind("shortName", model.getUserShortName())
                .bind("phone", model.getPhoneNo())
                .bind("email", model.getEmail())
                .bind("password", model.getPassword())
                .bind("active", model.getActive())
                .bind("status", model.getStatus())
                .bind("create", model.getCreatedDate())
                .fetch()
                .rowsUpdated()
                .map(r -> model);
    }

    public Mono<User> update(User model) {
        String qurey = " UPDATE app_user "
                + " SET user_name=:name,user_short_name=:shortName,phone=:phone,email=:email,password=:password,updateedDate:update"
                + "active=:active,status=:status "
                + " WHERE user_code=:code ";
        return dbClient.sql(qurey)
                .bind("code", model.getUserCode())
                .bind("name", model.getUserName())
                .bind("shortName", model.getUserShortName())
                .bind("phone", model.getPhoneNo())
                .bind("email", model.getEmail())
                .bind("password", model.getPassword())
                .bind("active", model.getActive())
                .bind("status", model.getStatus())
                .bind("update", model.getUpdatedDate())
                .fetch()
                .rowsUpdated()
                .map(r -> model);
    }

}
