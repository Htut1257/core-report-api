/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

/**
 *
 * @author myoht
 */
@Data
@Builder
@Slf4j
public class Sequence {

    // private String macId;
    @Id
    private String seqOption;
    private String period;
    private Integer seqNo;

}
