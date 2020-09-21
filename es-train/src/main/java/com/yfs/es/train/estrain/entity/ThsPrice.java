package com.yfs.es.train.estrain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ThsPrice {

    private String id;
    private String symbol;
    private String code;
    private String date;
    private Long dayTime;
    private Integer highBefore;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal yesterdayClose;
    private BigDecimal openPercent;
    private BigDecimal closePercent;
    private BigDecimal highPercent;
    private BigDecimal lowPercent;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 量
     */
    private BigDecimal vol;
    /**
     * 量比
     */
    private BigDecimal volRise;
    private BigDecimal ma5;
    private BigDecimal ma10;
    private BigDecimal ma20;
    private BigDecimal ma30;
    private BigDecimal ma60;
    private Integer upTag;

}
