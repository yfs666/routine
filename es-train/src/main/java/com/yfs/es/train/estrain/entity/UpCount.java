package com.yfs.es.train.estrain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpCount {
    private String code;
    private Integer upDays;
    private BigDecimal upPercent;
    private Integer days;
}
