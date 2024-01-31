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
@Table("comp_info")
public class CompInfo {
    @Id
    private String compCode;
    private String compName;
    private String compShort;
    private String compPhoneNumbers;
    private String compEmails;
    private String compAddress;
    private Boolean active;
    private LocalDateTime compStartDate;
    private LocalDateTime compEndDate;
    private Integer compBranchCount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;
    private String compOwnerName;
    private String compContactPerson;
    private String hostName;
    private String token;
}
