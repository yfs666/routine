package com.yfs.es.train.estrain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class KLineVO implements Comparable<KLineVO>{

    public KLineVO(String code, BigDecimal closeY, BigDecimal open, BigDecimal closeT, BigDecimal high, BigDecimal low) {
        this.code = code;
        this.closeY = closeY;
        this.open = open;
        this.closeT = closeT;
        this.high = high;
        this.low = low;
    }

    private String code;
    private BigDecimal closeY;
    private BigDecimal open;
    private BigDecimal closeT;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal openPercent;
    private BigDecimal closePercent;
    private BigDecimal highPercent;
    private BigDecimal lowPercent;

    public void wrapPercent() {
        this.closePercent = this.closeT.divide(this.closeY, 6, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
        this.openPercent = this.open.divide(this.closeY, 6, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
        this.highPercent = this.high.divide(this.closeY, 6, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
        this.lowPercent = this.low.divide(this.closeY, 6, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
    }

    @Override
    public int compareTo(KLineVO kLineVO) {
        return this.closePercent.compareTo(kLineVO.closePercent);
    }
}
