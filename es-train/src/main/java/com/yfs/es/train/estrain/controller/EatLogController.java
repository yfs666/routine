package com.yfs.es.train.estrain.controller;

import com.google.common.collect.Maps;
import com.yfs.es.train.estrain.entity.EatLog;
import com.yfs.es.train.estrain.service.EatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@RestController
@RequestMapping(value = "/eatLog")
public class EatLogController {

    @Autowired
    private EatLogService eatLogService;

    @RequestMapping(value = "addEatLog", method = RequestMethod.GET)
    public Map<String, Object> addEatLog(BigDecimal count, Long operateTimePre, String operator, Integer eatType) {
        Map<String, Object> result = Maps.newHashMap();
        if (eatType != 1 && eatType != 2) {
            result.put("code", 1);
            return result;
        }
        try {
            LocalTime operateTime = LocalTime.now();
            if (operateTimePre != null && operateTimePre > 0) {
                operateTime = operateTime.minusMinutes(operateTimePre);
            }
            EatLog eatLog = eatLogService.queryByDate(LocalDate.now());
            if (eatType == 1) {
                if (eatLog != null) {
                    result.put("code", 1);
                } else {
                    if (count == null) {
                        count = BigDecimal.valueOf(1.5);
                    }
                    eatLogService.addEatLog(count, operateTime, null, operator);
                    result.put("code", 0);
                }
                return result;
            }


            if (eatLog == null) {
                result.put("code", 1);
            } else {
                eatLog.setBreakFastTime(operateTime);
                eatLogService.update(eatLog);
                result.put("code", 0);
            }
            return result;
        } catch (Exception e) {
            result.put("code", 1);
            return result;
        }

    }


}
