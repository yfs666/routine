package com.yfs.es.train.estrain.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.entity.ThsPrice;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<Map<Integer, String>> {
    /**
     * 日期字符串 yyyy-MM-dd
     */
    private String date;

    public ExcelListener(String date) {
        this.date = date;
        try {
            this.dayTime = new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.stockInfoList = Lists.newArrayList();
        this.thsPriceList = Lists.newArrayList();
    }

    public ExcelListener() {
    }

    /**
     * 日期对应的凌晨时刻，用于排序
     */
    private long dayTime;

    private List<ThsPrice> thsPriceList;

    private List<StockInfo> stockInfoList;


    /**
     * When analysis one row trigger invoke function.
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        String symbol = data.get(0);
        String code = symbol.substring(2, 8);
        String name = data.get(1);
        StockInfo stockInfo = new StockInfo();
        stockInfo.setSymbol(symbol);
        stockInfo.setCode(code);
        stockInfo.setName(name);
        stockInfoList.add(stockInfo);
        String closePercent = data.get(3);
        if (StringUtils.isEmpty(closePercent) || "--".equals(closePercent)) {
            return;
        }
        ThsPrice thsPrice = new ThsPrice();
        thsPrice.setSymbol(symbol);
        thsPrice.setCode(code);
        thsPrice.setDate(date);
        thsPrice.setDayTime(dayTime);
        thsPrice.setOpen(new BigDecimal(data.get(9)));
        thsPrice.setClose(new BigDecimal(data.get(4)));
        thsPrice.setHigh(new BigDecimal(data.get(10)));
        thsPrice.setLow(new BigDecimal(data.get(11)));
        String yesterdayClose = data.get(8);
        thsPrice.setYesterdayClose(StringUtils.isEmpty(yesterdayClose) || "--".equals(yesterdayClose) ? thsPrice.getOpen() : new BigDecimal(yesterdayClose));
        thsPrice.setOpenPercent(thsPrice.getOpen().divide(thsPrice.getYesterdayClose(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)));
        thsPrice.setHighPercent(thsPrice.getHigh().divide(thsPrice.getYesterdayClose(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)));
        thsPrice.setLowPercent(thsPrice.getLow().divide(thsPrice.getYesterdayClose(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)));
        thsPrice.setClosePercent(new BigDecimal(data.get(3)));
        thsPrice.setIndustry(data.get(16));
        thsPrice.setVol(new BigDecimal(data.get(23)));
        String volRaise = data.get(15);
        thsPrice.setVolRise(StringUtils.isEmpty(volRaise) || "--".equals(volRaise) ? BigDecimal.ONE : new BigDecimal(volRaise));
        thsPriceList.add(thsPrice);
//        thsPrice.setHighBefore();
//        thsPrice.setMa5();
//        thsPrice.setMa10();
//        thsPrice.setMa20();
//        thsPrice.setMa30();
//        thsPrice.setMa60();
//        thsPrice.setUpTag();
    }

    /**
     * if have something to do after all analysis
     *
     * @param context c
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<ThsPrice> getThsPriceList() {
        return thsPriceList;
    }

    public List<StockInfo> getStockInfoList() {
        return stockInfoList;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDayTime(long dayTime) {
        this.dayTime = dayTime;
    }
}
