package com.heycm.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-29 23:48
 */
@Data
public class TreeDTO {
    private Integer id;
    private String label;
    private List<TreeDTO> children;
}
