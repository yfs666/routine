package com.yfs.es.train.estrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CountShowVO {

    private Integer count;

    private BigDecimal closeAvg;
    private BigDecimal highAvg;
    private BigDecimal lowAvg;

    private List<CountShowDetailVO> closeAvgSum;
    private List<CountShowDetailVO> closeAvg60x;
    private List<CountShowDetailVO> closeAvg688;
    private List<CountShowDetailVO> closeAvg000;
    private List<CountShowDetailVO> closeAvg002;
    private List<CountShowDetailVO> closeAvg300;

    private List<CountShowDetailVO> highAvgSum;
    private List<CountShowDetailVO> highAvg60x;
    private List<CountShowDetailVO> highAvg688;
    private List<CountShowDetailVO> highAvg000;
    private List<CountShowDetailVO> highAvg002;
    private List<CountShowDetailVO> highAvg300;

    private List<CountShowDetailVO> lowAvgSum;
    private List<CountShowDetailVO> lowAvg60x;
    private List<CountShowDetailVO> lowAvg688;
    private List<CountShowDetailVO> lowAvg000;
    private List<CountShowDetailVO> lowAvg002;
    private List<CountShowDetailVO> lowAvg300;

}
