package com.yfs.es.train.estrain;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LimitEnum {


    limit10_11("10~11", 10, 11, true),
    limit9_10("9~10", 9, 10, true),
    limit8_9("8~9", 8, 9, true),
    limit7_8("7~8", 7, 8, true),
    limit6_7("6~7", 6, 7, true),
    limit5_6("5~6", 5, 6, true),
    limit4_5("4~5", 4, 5, true),
    limit3_4("3~4", 3, 4, true),
    limit2_3("2~3", 2, 3, true),
    limit1_2("1~2", 1, 2, true),
    limit0_5_1("0.5~1", 0.5, 1, true),
    limit0_0_5("0~0.5", 0, 0.5, true),
    limit0("0", 0, 0, true),
    limit0d("0", 100, 100, false),
    limit10_11d("10~11", 10, 11, false),
    limit9_10d("9~10", 9, 10, false),
    limit8_9d("8~9", 8, 9, false),
    limit7_8d("7~8", 7, 8, false),
    limit6_7d("6~7", 6, 7, false),
    limit5_6d("5~6", 5, 6, false),
    limit4_5d("4~5", 4, 5, false),
    limit3_4d("3~4", 3, 4, false),
    limit2_3d("2~3", 2, 3, false),
    limit1_2d("1~2", 1, 2, false),
    limit0_5_1d("0.5~1", 0.5, 1, false),
    limit0_0_5d("0~0.5", 0, 0.5, false),
    ;

    public String limitName;

    public double floor;

    public double ceiling;

    public Boolean isUp;

    LimitEnum(String limitName, double floor, double ceiling, Boolean isUp) {
        this.limitName = limitName;
        this.floor = floor;
        this.ceiling = ceiling;
        this.isUp = isUp;
    }

    public static List<Tuple2<LimitEnum, LimitEnum>> getEnumGroup() {
        return Arrays.stream(LimitEnum.values()).filter(it -> it.isUp).map(it -> Tuples.of(it, LimitEnum.getByLimitNameAndIsUp(it.limitName, false))).collect(Collectors.toList());
    }

    public static LimitEnum getByLimitNameAndIsUp(String limitName, boolean isUp) {
        return Arrays.stream(LimitEnum.values()).filter(it -> it.limitName.equals(limitName) && it.isUp == isUp).findFirst().orElse(null);
    }

    public static LimitEnum getByPercent(double percent) {
        if (percent == 0D) {
            return limit0;
        }
        return Arrays.stream(LimitEnum.values()).filter(it -> isInLimit(percent, it)).findFirst().orElse(null);
    }

    private static boolean isInLimit(double percent, LimitEnum limitEnum) {
        if (percent == 0D && limitEnum == limit0) {
            return true;
        }
        if (percent > 0 && limitEnum.isUp) {
            // 0.5的范围不包括上下线，因为有0已经单独统计了
            if (limitEnum == limit0_0_5) {
                return percent > limitEnum.floor && percent < limitEnum.ceiling;
            }
            if (limitEnum == limit10_11) {
                return percent >= limitEnum.floor;
            }
            return percent >= limitEnum.floor && percent < limitEnum.ceiling;
        }
        if (percent < 0 && !limitEnum.isUp) {
            if (limitEnum == limit0_0_5d) {
                return percent > -limitEnum.ceiling && percent < -limitEnum.floor;
            }
            if (limitEnum == limit10_11d) {
                return percent <= -limitEnum.floor;
            }
            return percent > -limitEnum.ceiling && percent <= -limitEnum.floor;
        }
        return false;
    }
}
