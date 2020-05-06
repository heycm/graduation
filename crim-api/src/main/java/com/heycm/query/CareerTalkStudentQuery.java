package com.heycm.query;

import lombok.Data;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Data
public class CareerTalkStudentQuery {
    private Integer yearId;
    private Integer quarter;
    private String jobFairTitle;
    private String careerTalkTitle;
}
