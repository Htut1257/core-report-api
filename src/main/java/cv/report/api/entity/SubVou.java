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
@Table("sub_vou")
public class SubVou {

    @Id
    private String subVou;
    private String userCode;
    private String subPlanCode;
    private Double amount;
    private Double disAmount;
    private Double disPercent;
    private Double balance;
    private Integer vouStatus;
    private LocalDateTime vouDate;
    private Boolean deleted;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
