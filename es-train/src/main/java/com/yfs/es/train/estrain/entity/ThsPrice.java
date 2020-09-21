package com.yfs.es.train.estrain.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Comparator;

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



    @Override
    public String toString() {
        return "StockPrice{" +
                "id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", dayTime=" + dayTime +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", ma5=" + ma5 +
                ", ma10=" + ma10 +
                ", ma20=" + ma20 +
                ", ma30=" + ma30 +
                ", ma60=" + ma60 +
                '}';
    }

    public boolean allUp() {
        return ma5.doubleValue() >= ma10.doubleValue()
                && ma10.doubleValue() >= ma20.doubleValue()
                && ma20.doubleValue() >= ma30.doubleValue()
                && ma30.doubleValue() >= ma60.doubleValue()
                ;
    }


    public boolean allDown() {
        return ma5.doubleValue() <= ma10.doubleValue()
                && ma10.doubleValue() <= ma20.doubleValue()
                && ma20.doubleValue() <= ma30.doubleValue()
                ;
    }

    public boolean largerThanAllLine() {
        return Lists.newArrayList(ma5, ma10, ma20, ma30, ma60).stream().max(Comparator.comparing(BigDecimal::doubleValue)).map(it -> it.doubleValue() <= open.doubleValue()).orElse(false);
    }

    public boolean lessThanAllLine() {
        return Lists.newArrayList(ma5, ma10, ma20, ma30, ma60).stream().min(Comparator.comparing(BigDecimal::doubleValue)).map(it -> it.doubleValue() >= open.doubleValue()).orElse(false);
    }


}
