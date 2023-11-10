package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<String, Integer> discountInfo;

    public Discount(Map<String, Integer> discountInfo) {
        this.discountInfo = discountInfo;
    }

    public static Discount createDiscount(Integer weekDayDiscount, Integer weekendDayDiscount) {
        Map<String, Integer> info = new HashMap<>();
        if (weekDayDiscount != 0) {
            info.put("평일 할인", weekDayDiscount);
        }

        if (weekendDayDiscount != 0) {
            info.put("주말 할인", weekendDayDiscount);
        }
        
        return new Discount(info);
    }

    public Map<String, Integer> getDiscountInfo() {
        return discountInfo;
    }
}