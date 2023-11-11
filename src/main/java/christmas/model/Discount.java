package christmas.model;

import static christmas.model.DiscountPolicy.D_DAY_DISCOUNT;
import static christmas.model.DiscountPolicy.PRESENT_DISCOUNT;
import static christmas.model.DiscountPolicy.SPECIAL_DISCOUNT;
import static christmas.model.DiscountPolicy.WEEKDAY_DISCOUNT;
import static christmas.model.DiscountPolicy.WEEKEND_DISCOUNT;
import static christmas.model.EventInfo.CHRISTMAS_DAY;
import static christmas.model.EventInfo.ONE_DAY_DISCOUNT;
import static christmas.model.EventInfo.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.model.EventInfo.START_DAY_DISCOUNT;

import christmas.vo.DiscountAmount;
import christmas.vo.VisitDay;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private static final Integer NO_DISCOUNT_AMOUNT = 0;
    private final Map<DiscountPolicy, DiscountAmount> discountInformation;

    private Discount(Map<DiscountPolicy, DiscountAmount> discountInformation) {
        this.discountInformation = discountInformation;
    }

    public static Discount createDiscount(Integer weekDayDiscount, Integer weekendDayDiscount,
                                          VisitDay visitDay, boolean isGiftGiven) {
        Map<DiscountPolicy, DiscountAmount> discountInformation = new HashMap<>();

        putWeekDayDiscount(weekDayDiscount, discountInformation);
        putWeekendDayDiscount(weekendDayDiscount, discountInformation);
        putD_DayDiscount(visitDay, discountInformation);
        putSpecialDayDiscount(visitDay, discountInformation);
        putGiftDiscount(isGiftGiven, discountInformation);

        return new Discount(discountInformation);
    }

    private static void putGiftDiscount(boolean isGiftGiven, Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (isGiftGiven) {
            DiscountAmount discountAmount = new DiscountAmount(Menu.getGiftPrice());
            discountInformation.put(PRESENT_DISCOUNT, discountAmount);
        }
    }

    private static void putSpecialDayDiscount(VisitDay visitDay,
                                              Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (visitDay.isSpecialDay()) {
            DiscountAmount discountAmount = new DiscountAmount(SPECIAL_DISCOUNT_AMOUNT.getValue());
            discountInformation.put(SPECIAL_DISCOUNT, discountAmount);
        }
    }

    private static void putD_DayDiscount(VisitDay visitDay, Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (visitDay.getDay() <= CHRISTMAS_DAY.getValue()) {
            DiscountAmount discountAmount = new DiscountAmount(
                    START_DAY_DISCOUNT.getValue() + visitDay.getDay() * ONE_DAY_DISCOUNT.getValue());
            discountInformation.put(D_DAY_DISCOUNT, discountAmount);
        }
    }

    private static void putWeekendDayDiscount(Integer weekendDayDiscount,
                                              Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (weekendDayDiscount != NO_DISCOUNT_AMOUNT) {
            DiscountAmount discountAmount = new DiscountAmount(weekendDayDiscount);
            discountInformation.put(WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private static void putWeekDayDiscount(Integer weekDayDiscount,
                                           Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (weekDayDiscount != NO_DISCOUNT_AMOUNT) {
            DiscountAmount discountAmount = new DiscountAmount(weekDayDiscount);
            discountInformation.put(WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    public Integer getGiftDiscount() {
        return discountInformation.getOrDefault(PRESENT_DISCOUNT, new DiscountAmount(NO_DISCOUNT_AMOUNT)).getAmount();
    }

    public Integer getSumOfDiscount() {
        return discountInformation.values()
                .stream()
                .mapToInt(DiscountAmount::getAmount)
                .sum();
    }

    public Map<DiscountPolicy, DiscountAmount> getDiscountInformation() {
        return discountInformation;
    }
}
