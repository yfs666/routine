
package com.yfs.es.train.estrain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockProfit {
    private String id;
    private String symbol;
    private Long reportDate;
    private String reportName;
    private BigDecimal profit;
    private BigDecimal profitPercent;
    private BigDecimal revenue;
    private BigDecimal revenuePercent;
}