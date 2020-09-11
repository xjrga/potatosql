package org.xjrga.potatosql.other;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GoldenRatio {
    private static final BigDecimal goldenRatio = new BigDecimal(1.61803398875);
    private final Boolean widthLarger = true;

    private GoldenRatio() {
    }

    public static Integer getLongSide(Integer length) {
        BigDecimal v = new BigDecimal(length).multiply(goldenRatio);
        v.setScale(2, RoundingMode.HALF_UP);
        return v.intValue();
    }

    public static Integer getShortSide(Integer length) {
        BigDecimal v = new BigDecimal(length).divide(goldenRatio, 2, RoundingMode.HALF_UP);
        return v.intValue();
    }
}
