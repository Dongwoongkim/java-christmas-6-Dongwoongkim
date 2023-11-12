package christmas.model;

import static christmas.model.DiscountPolicy.D_DAY_DISCOUNT;
import static christmas.model.DiscountPolicy.GIFT_DISCOUNT;
import static christmas.model.DiscountPolicy.SPECIAL_DISCOUNT;
import static christmas.model.DiscountPolicy.WEEKDAY_DISCOUNT;
import static christmas.model.DiscountPolicy.WEEKEND_DISCOUNT;
import static christmas.model.EventInfo.D_DAY_DISCOUNT_AMOUNT;
import static christmas.model.EventInfo.PRESENT_YEAR;
import static christmas.model.EventInfo.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.model.EventInfo.START_D_DAY_DISCOUNT_AMOUNT;

import christmas.vo.DiscountAmount;
import christmas.vo.VisitDay;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private static final Integer NO_DISCOUNT_AMOUNT = 0;

    private final Map<DiscountPolicy, DiscountAmount> discountInformation;

    private Discount(final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        this.discountInformation = discountInformation;
    }

    public static Discount createDiscount(final Integer mainQuantity, final Integer dessertQuantity,
                                          final VisitDay visitDay, final boolean isGiftReceived) {
        Map<DiscountPolicy, DiscountAmount> discountInformation = new HashMap<>();

        putWeekDayDiscount(dessertQuantity, visitDay, discountInformation);
        putWeekendDayDiscount(mainQuantity, visitDay, discountInformation);
        putD_DayDiscount(visitDay, discountInformation);
        putSpecialDayDiscount(visitDay, discountInformation);
        putGiftDiscount(isGiftReceived, discountInformation);

        return new Discount(discountInformation);
    }

    private static void putGiftDiscount(final boolean isGiftReceived,
                                        final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (isGiftReceived) {
            DiscountAmount discountAmount = new DiscountAmount(Menu.getGiftPrice());
            discountInformation.put(GIFT_DISCOUNT, discountAmount);
        }
    }

    private static void putSpecialDayDiscount(final VisitDay visitDay,
                                              final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (visitDay.isSpecialDay()) {
            DiscountAmount discountAmount = new DiscountAmount(SPECIAL_DISCOUNT_AMOUNT.getValue());
            discountInformation.put(SPECIAL_DISCOUNT, discountAmount);
        }
    }

    private static void putD_DayDiscount(final VisitDay visitDay,
                                         final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (visitDay.isBeforeOrEqualsChristmas()) {
            DiscountAmount discountAmount = new DiscountAmount(
                    START_D_DAY_DISCOUNT_AMOUNT.getValue() + visitDay.getDay() * D_DAY_DISCOUNT_AMOUNT.getValue());
            discountInformation.put(D_DAY_DISCOUNT, discountAmount);
        }
    }

    private static void putWeekendDayDiscount(final Integer mainQuantity, final VisitDay visitDay,
                                              final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (!visitDay.isWeekDay() && mainQuantity != 0) {
            DiscountAmount discountAmount = new DiscountAmount(mainQuantity * PRESENT_YEAR.getValue());
            discountInformation.put(WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private static void putWeekDayDiscount(final Integer dessertQuantity, final VisitDay visitDay,
                                           final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (visitDay.isWeekDay() && dessertQuantity != 0) {
            DiscountAmount discountAmount = new DiscountAmount(dessertQuantity * PRESENT_YEAR.getValue());
            discountInformation.put(WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    public Integer getGiftDiscount() {
        return discountInformation.getOrDefault(GIFT_DISCOUNT, new DiscountAmount(NO_DISCOUNT_AMOUNT)).getAmount();
    }

    public Integer getSumOfDiscount() {
        return discountInformation.values()
                .stream()
                .mapToInt(DiscountAmount::getAmount)
                .sum();
    }

    public Map<DiscountPolicy, DiscountAmount> getDiscountInformation() {
        return Collections.unmodifiableMap(discountInformation);
    }
}
