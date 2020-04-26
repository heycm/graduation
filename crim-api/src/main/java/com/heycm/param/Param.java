package com.heycm.param;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-26 19:28
 */
@Data
public class Param<T> {
    private Integer page;
    private Integer rows;
    private List<Integer> ids;
    private T data;
}
