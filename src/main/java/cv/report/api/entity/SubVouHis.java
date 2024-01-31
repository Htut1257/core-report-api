/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.entity;

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
@Table("sub_vou_his")
public class SubVouHis {

    @Id
    private String subHisId;
    private String subVou;
    private String compCode;
    private String subId;
    private Double price;
    private Integer month;
    private Double disAmount;
    private Double disPercent;
    private Double amount;

}
