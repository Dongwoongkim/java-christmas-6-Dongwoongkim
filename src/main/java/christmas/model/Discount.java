package christmas.model;

import static christmas.model.DiscountInfo.D_DAY_DISCOUNT;
import static christmas.model.DiscountInfo.PRESENT_DISCOUNT;
import static christmas.model.DiscountInfo.SPECIAL_DISCOUNT;
import static christmas.model.DiscountInfo.WEEKDAY_DISCOUNT;
import static christmas.model.DiscountInfo.WEEKEND_DISCOUNT;

import christmas.vo.Date;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<String, Integer> discountInfo;

    public Discount(Map<String, Integer> discountInfo) {
        this.discountInfo = discountInfo;
    }

    public static Discount createDiscount(Integer weekDayDiscount, Integer weekendDayDiscount, Date date,
                                          boolean isGetGift) {
        Map<String, Integer> info = new HashMap<>();

        if (weekDayDiscount != 0) {
            info.put(WEEKDAY_DISCOUNT.getPolicy(), weekDayDiscount);
        }

        if (weekendDayDiscount != 0) {
            info.put(WEEKEND_DISCOUNT.getPolicy(), weekendDayDiscount);
        }

        if (date.getDay() <= 25) {
            info.put(D_DAY_DISCOUNT.getPolicy(), 900 + date.getDay() * 100);
        }

        if (isGetGift) {
            info.put(PRESENT_DISCOUNT.getPolicy(), Menu.getChampagnePrice());
        }

        if (date.isSpecialDay()) {
            info.put(SPECIAL_DISCOUNT.getPolicy(), 1000);
        }

        return new Discount(info);
    }

    public Integer getWeekdayDiscount() {
        return discountInfo.getOrDefault("평일 할인", 0);
    }

    public Integer getWeekendDayDiscount() {
        return discountInfo.getOrDefault("주말 할인", 0);
    }

    public Integer getSpecialDayDiscount() {
        return discountInfo.getOrDefault("특별 할인", 0);
    }

    public Integer getDayDiscount() {
        return discountInfo.getOrDefault("크리스마스 디데이 할인", 0);
    }

    public Integer getGiftDiscount() {
        return discountInfo.getOrDefault("증정 이벤트", 0);
    }

    public Integer getSumOfDiscount() {
        return getWeekdayDiscount() + getWeekendDayDiscount() + getGiftDiscount() + getDayDiscount()
                + getSpecialDayDiscount();
    }

    public String getBadge() {
        Integer sumOfDiscount = getSumOfDiscount();
        if (sumOfDiscount >= 20000) {
            return "산타";
        }
        if (sumOfDiscount >= 10000) {
            return "트리";
        }
        if (sumOfDiscount >= 5000) {
            return "별";
        }
        return "없음";
    }

    public Map<String, Integer> getDiscountInfo() {
        return discountInfo;
    }
}
