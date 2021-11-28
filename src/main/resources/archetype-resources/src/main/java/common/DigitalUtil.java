package ${package}.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 有关数字转换 例如小数->整数 | 整数->小数 | 保留几位小数
 */
public class DigitalUtil {

    public static String retainTwoDecimal(Float value) {
        return new BigDecimal(value).setScale(2, RoundingMode.DOWN).toString();
    }

    public static String retainTwoDecimal(Double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.DOWN).toString();
    }

}
