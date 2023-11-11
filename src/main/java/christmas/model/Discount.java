package christmas.model;

import static christmas.model.DiscountInfo.D_DAY_DISCOUNT;
import static christmas.model.DiscountInfo.PRESENT_DISCOUNT;
import static christmas.model.DiscountInfo.SPECIAL_DISCOUNT;
import static christmas.model.DiscountInfo.WEEKDAY_DISCOUNT;
import static christmas.model.DiscountInfo.WEEKEND_DISCOUNT;
import static christmas.model.EventInfo.CHRISTMAS_DAY;
import static christmas.model.EventInfo.ONE_DAY_DISCOUNT;
import static christmas.model.EventInfo.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.model.EventInfo.START_DAY_DISCOUNT;

import christmas.vo.VisitDay;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<String, Integer> discountInfo;

    public Discount(Map<String, Integer> discountInfo) {
        this.discountInfo = discountInfo;
    }

    public static Discount createDiscount(Integer weekDayDiscount, Integer weekendDayDiscount,
                                          VisitDay visitDay, Gift gift) {
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

        if (visitDay.isSpecialDay()) {
            info.put(SPECIAL_DISCOUNT.getPolicy(), SPECIAL_DISCOUNT_AMOUNT.getValue());
        }

        if (gift.isExist()) {
            info.put(PRESENT_DISCOUNT.getPolicy(), Menu.getGiftPrice());
        }

        return new Discount(info);
    }

    public Integer getGiftDiscount() {
        return discountInfo.getOrDefault(PRESENT_DISCOUNT, 0);
    }

    public Integer getSumOfDiscount() {
        return discountInfo.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<String, Integer> getDiscountInfo() {
        return discountInfo;
    }
}
