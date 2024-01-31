/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author myoht
 */
@Builder
@Data
public class User {

    private String userCode;
    private String userName;
    private String userShortName;
    private String phoneNo;
    private String email;
    private String password;
    private String address;
    private Boolean active;
    private Integer status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
