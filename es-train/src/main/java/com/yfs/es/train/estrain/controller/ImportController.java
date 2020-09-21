package com.yfs.es.train.estrain.controller;

import com.alibaba.excel.EasyExcel;
import com.yfs.es.train.estrain.excel.ExcelListener;
import com.yfs.es.train.estrain.service.StockInfoService;
import com.yfs.es.train.estrain.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/import")
public class ImportController {

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockPriceService stockPriceService;

    @RequestMapping(value = "/importDayLine", method = RequestMethod.POST)
    public Map<String, Object> importDayLine(MultipartFile file) throws IOException {
        ExcelListener excelListener = new ExcelListener("2020-09-21");
        EasyExcel.read(file.getInputStream(), excelListener).sheet().headRowNumber(1).doRead();
        stockInfoService.compareAndSave(excelListener.getStockInfoList());
        stockPriceService.compareAndAdd(excelListener.getThsPriceList());
        return Collections.emptyMap();
    }

}
