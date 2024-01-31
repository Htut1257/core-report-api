/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author myoht
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ReturnObject<T> {

    private int status;
    private String message;
    private T data;
    private List<T> listData;
    private String errMessage;

//    public ReturnObject(Integer status, String message, List<T> listData) {
//        this.status = status;
//        this.message = message;
//        this.listData = listData;
//    }
}
