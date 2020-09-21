package com.yfs.es.train.estrain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {

    private String id;

    private String symbol;

    private String code;

    private String name;

    /**
     * 是否退市 true表示已退市
     */
    private Boolean quitMarket;

}
