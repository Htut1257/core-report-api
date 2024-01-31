/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author myoht
 */
@Data
@Builder
@Table("subscribe_plan")
public class SubscriptionPlan {

    private String subPlanCode;
    private String subPlanName;
    private String subPlanPrice;
    private String subPlanDuration;
    private String subPlanType;
    private String subType;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
