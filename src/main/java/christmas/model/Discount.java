package christmas.model;

import static christmas.model.DiscountInfo.D_DAY_DISCOUNT;
import static christmas.model.DiscountInfo.PRESENT_DISCOUNT;
import static christmas.model.DiscountInfo.SPECIAL_DISCOUNT;
import static christmas.model.DiscountInfo.WEEKDAY_DISCOUNT;
import static christmas.model.DiscountInfo.WEEKEND_DISCOUNT;
import static christmas.model.EventInfo.CHRISTMAS_DAY;
import static christmas.model.EventInfo.ONE_DAY_DISCOUNT;
import static christmas.model.EventInfo.SANTA_BADGE_REQUIREMENT_AMOUNT;
import static christmas.model.EventInfo.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.model.EventInfo.START_DAY_DISCOUNT;
import static christmas.model.EventInfo.STAR_BADGE_REQUIREMENT_AMOUNT;
import static christmas.model.EventInfo.TREE_BADGE_REQUIREMENT_AMOUNT;

import christmas.vo.Badge;
import christmas.vo.VisitDay;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private static final String SANTA_BADGE = "산타";
    private static final String TREE_BADGE = "트리";
    private static final String STAR_BADGE = "별";
    private static final String NONE_BADGE = "없음";

    private final Map<String, Integer> discountInfo;

    public Discount(Map<String, Integer> discountInfo) {
        this.discountInfo = discountInfo;
    }

    public static Discount createDiscount(Integer weekDayDiscount, Integer weekendDayDiscount, VisitDay visitDay,
                                          boolean isGetGift) {
        Map<String, Integer> info = new HashMap<>();

        if (weekDayDiscount != 0) {
            info.put(WEEKDAY_DISCOUNT.getPolicy(), weekDayDiscount);
        }

        if (weekendDayDiscount != 0) {
            info.put(WEEKEND_DISCOUNT.getPolicy(), weekendDayDiscount);
        }

        if (visitDay.getDay() <= CHRISTMAS_DAY.getValue()) {
            info.put(D_DAY_DISCOUNT.getPolicy(),
                    START_DAY_DISCOUNT.getValue() + visitDay.getDay() * ONE_DAY_DISCOUNT.getValue());
        }

        if (isGetGift) {
            info.put(PRESENT_DISCOUNT.getPolicy(), Menu.getChampagnePrice());
        }

        if (visitDay.isSpecialDay()) {
            info.put(SPECIAL_DISCOUNT.getPolicy(), SPECIAL_DISCOUNT_AMOUNT.getValue());
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

    public Badge getBadge() {
        Integer sumOfDiscount = getSumOfDiscount();
        if (sumOfDiscount >= SANTA_BADGE_REQUIREMENT_AMOUNT.getValue()) {
            return new Badge(SANTA_BADGE);
        }
        if (sumOfDiscount >= TREE_BADGE_REQUIREMENT_AMOUNT.getValue()) {
            return new Badge(TREE_BADGE);
        }
        if (sumOfDiscount >= STAR_BADGE_REQUIREMENT_AMOUNT.getValue()) {
            return new Badge(STAR_BADGE);
        }
        return new Badge(NONE_BADGE);
    }

    public Map<String, Integer> getDiscountInfo() {
        return discountInfo;
    }
}
