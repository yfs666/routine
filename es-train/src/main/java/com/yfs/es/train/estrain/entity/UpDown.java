package com.yfs.es.train.estrain.entity;

import com.yfs.es.train.estrain.LimitEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpDown {

    private String code;

    private BigDecimal percent;

    public LimitEnum getLimitEnum() {
        if (LimitEnum.getByPercent(percent.doubleValue()) == null) {
            System.out.println(123);
        }
        return LimitEnum.getByPercent(percent.doubleValue());
    }

    public String getGroupKey() {
        if (percent == null) {
            return "0";
        }
        BigDecimal multiply = percent.multiply(BigDecimal.valueOf(100));
        if (multiply.doubleValue() >= 10) {
            return "10~11";
        } else if (multiply.doubleValue() < 10 && multiply.doubleValue() >= 9) {
            return "9~10";
        } else if (multiply.doubleValue() < 9 && multiply.doubleValue() >= 8) {
            return "8~9";
        } else if (multiply.doubleValue() < 8 && multiply.doubleValue() >= 7) {
            return "7~8";
        } else if (multiply.doubleValue() < 7 && multiply.doubleValue() >= 6) {
            return "6~7";
        } else if (multiply.doubleValue() < 6 && multiply.doubleValue() >= 5) {
            return "5~6";
        } else if (multiply.doubleValue() < 5 && multiply.doubleValue() >= 4) {
            return "4~5";
        } else if (multiply.doubleValue() < 4 && multiply.doubleValue() >= 3) {
            return "3~4";
        } else if (multiply.doubleValue() < 3 && multiply.doubleValue() >= 2) {
            return "2~3";
        } else if (multiply.doubleValue() < 2 && multiply.doubleValue() >= 1) {
            return "1~2";
        } else if (multiply.doubleValue() < 1 && multiply.doubleValue() >= 0.5) {
            return "0.5~1";
        } else if (multiply.doubleValue() < 0.5 && multiply.doubleValue() > 0) {
            return "0.01~0.5";
        } else if (multiply.doubleValue() == 0D) {
            return "0";
        } else if (multiply.doubleValue() < 0 && multiply.doubleValue() > -0.5) {
            return "-0.01~-0.5";
        } else if (multiply.doubleValue() < -0.5 && multiply.doubleValue() >= -1) {
            return "-0.5~-1";
        } else if (multiply.doubleValue() < -1 && multiply.doubleValue() >= -2) {
            return "-1~-2";
        } else if (multiply.doubleValue() < -2 && multiply.doubleValue() >= -3) {
            return "-2~-3";
        } else if (multiply.doubleValue() < -3 && multiply.doubleValue() >= -4) {
            return "-3~-4";
        } else if (multiply.doubleValue() < -4 && multiply.doubleValue() >= -5) {
            return "-4~-5";
        } else if (multiply.doubleValue() < -5 && multiply.doubleValue() >= -6) {
            return "-5~-6";
        } else if (multiply.doubleValue() < -6 && multiply.doubleValue() >= -7) {
            return "-6~-7";
        } else if (multiply.doubleValue() < -7 && multiply.doubleValue() >= -8) {
            return "-7~-8";
        } else if (multiply.doubleValue() < -8 && multiply.doubleValue() >= -9) {
            return "-8~-9";
        } else if (multiply.doubleValue() < -9 && multiply.doubleValue() >= -10) {
            return "-9~-10";
        } else {
            return "-10~-11";
        }

    }

}
