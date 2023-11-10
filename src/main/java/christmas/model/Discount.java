package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<String, Integer> discountInfo;

    public Discount(Map<String, Integer> discountInfo) {
        this.discountInfo = discountInfo;
    }

    public static Discount createDiscount(Integer weekDayDiscount, Integer weekendDayDiscount, Integer day,
                                          boolean isGetGift) {
        Map<String, Integer> info = new HashMap<>();

        if (weekDayDiscount != 0) {
            info.put("평일 할인", weekDayDiscount);
        }

        if (weekendDayDiscount != 0) {
            info.put("주말 할인", weekendDayDiscount);
        }

        if (day <= 25) {
            info.put("크리스마스 디데이 할인", 900 + day * 100);
        }

        if (isGetGift) {
            info.put("증정 이벤트", Menu.getChampagnePrice());
        }

        return new Discount(info);
    }

    public Map<String, Integer> getDiscountInfo() {
        return discountInfo;
    }

    public Integer getWeekdayDiscount() {
        return discountInfo.getOrDefault("평일 할인", 0);
    }

    public Integer getWeekendDayDiscount() {
        return discountInfo.getOrDefault("주말 할인", 0);
    }

    public Integer getDayDiscount() {
        return discountInfo.getOrDefault("크리스마스 디데이 할인", 0);
    }

    public Integer getGiftDiscount() {
        return discountInfo.getOrDefault("증정 이벤트", 0);
    }

    public Integer sumOfDiscount() {
        return getWeekdayDiscount() + getWeekendDayDiscount() + getGiftDiscount() + getDayDiscount();
    }
}
