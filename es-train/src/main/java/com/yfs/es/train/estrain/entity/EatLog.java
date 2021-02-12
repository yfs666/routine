package com.yfs.es.train.estrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class EatLog {

    private String id;
    /**
     * 量，如果是早饭，可以设置为0
     */
    private BigDecimal count;
    /**
     * 日期
     */
    private String date;
    /**
     * 吃药时间
     */
    private LocalTime drugTime;
    /**
     * 吃早餐时间
     */
    private LocalTime breakFastTime;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 最后操作时间
     */
    private LocalDateTime operateTime;

}
