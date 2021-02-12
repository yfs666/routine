package com.yfs.es.train.estrain;

import com.yfs.es.train.estrain.entity.EatLog;
import com.yfs.es.train.estrain.service.EatLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
public class EatLogServiceTest {

    @Autowired
    private EatLogService eatLogService;

    @Test
    public void deleteIndexTest() throws IOException {
        eatLogService.deleteIndex();
    }

    @Test
    public void addIndexTest() throws IOException {
        eatLogService.addIndex();
    }

    @Test
    public void addEatLogTest() throws IOException {
        eatLogService.addEatLog(BigDecimal.valueOf(1.5), LocalTime.of(9, 5, 0), LocalTime.of(9, 35, 0), "yangfengshuai");
    }

    @Test
    public void queryTest() {
        EatLog eatLog = eatLogService.queryByDate(LocalDate.now());
        System.out.println(eatLog);
    }
}
