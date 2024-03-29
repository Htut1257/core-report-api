/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author myoht
 */
@Data
@Builder
@Table("subscription")
public class Subscription {

    @Id
    private String subCode;
    private String subName;
    private Double price;
    private Integer subType;
    private Boolean active;
    private Integer status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
