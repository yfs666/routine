package com.yfs.es.train.estrain.entity;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class MarketKLineShowVO {

    public MarketKLineShowVO(List<KLineVO> kSum, List<KLineVO> k60x, List<KLineVO> k000, List<KLineVO> k002, List<KLineVO> k300) {
        this.kSum = kSum;
        this.k60x = k60x;
        this.k000 = k000;
        this.k002 = k002;
        this.k300 = k300;
        this.kSum.forEach(KLineVO::wrapPercent);
        this.k60x.forEach(KLineVO::wrapPercent);
        this.k000.forEach(KLineVO::wrapPercent);
        this.k002.forEach(KLineVO::wrapPercent);
        this.k300.forEach(KLineVO::wrapPercent);
        Collections.sort(this.kSum);
        Collections.sort(this.k60x);
        Collections.sort(this.k000);
        Collections.sort(this.k002);
        Collections.sort(this.k300);
        this.k002_1 = this.k002.subList(0, this.k002.size() / 2);
        this.k002_2 = this.k002.subList(this.k002.size() / 2, this.k002.size());
        this.k60x_1 = this.k60x.subList(0, this.k60x.size() / 2);
        this.k60x_2 = this.k60x.subList(this.k60x.size() / 2, this.k60x.size());
    }

    private List<KLineVO> kSum;
    private List<KLineVO> k60x;
    private List<KLineVO> k60x_1;
    private List<KLineVO> k60x_2;
    private List<KLineVO> k000;
    private List<KLineVO> k002;
    private List<KLineVO> k002_1;
    private List<KLineVO> k002_2;
    private List<KLineVO> k300;
}
