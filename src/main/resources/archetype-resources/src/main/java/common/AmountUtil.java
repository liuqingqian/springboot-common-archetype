package ${package}.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 金额元分之间转换工具类
 */
public class AmountUtil {

    /**
     * 元转分，确保price保留两位有效数字
     *
     * @return
     */
    public static int changeY2F(double price) {
        DecimalFormat df = new DecimalFormat("#.00");
        price = Double.valueOf(df.format(price));
        int money = (int) (price * 100);
        return money;
    }

    /**
     * 分转元，转换为bigDecimal在toString
     *
     * @return
     */
    public static String changeF2Y(int price) {
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }

    public static void main(String[] args) {
        double yuan = 1.5;
        int fen = 10;
        System.out.println("changeY2F(yuan) = " + changeY2F(yuan));
        System.out.println("changeF2Y(fen) = " + changeF2Y(fen));
    }
}
