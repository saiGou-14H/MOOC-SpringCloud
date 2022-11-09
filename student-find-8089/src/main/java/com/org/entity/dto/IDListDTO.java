package com.org.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Jie
 * @Date 2022-11-08
 */
@Data
public class IDListDTO implements Serializable {
    private List<Long> ids;
}
