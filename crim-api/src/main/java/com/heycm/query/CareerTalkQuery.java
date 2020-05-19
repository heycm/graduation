package com.heycm.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */

@Data
public class CareerTalkQuery {

    private Integer yearId;
    private Integer quarter;
    private String jobFairTitle;
    private String careerTalkTitle;

    private  String companyName;
    private  Boolean isStar;

}
