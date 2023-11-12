package christmas.model;

import static christmas.model.DiscountInfo.D_DAY_DISCOUNT_AMOUNT;
import static christmas.model.DiscountInfo.EVENT_ATTEND_MINIMUM_ORDER_AMOUNT;
import static christmas.model.DiscountInfo.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.model.DiscountInfo.START_D_DAY_DISCOUNT_AMOUNT;
import static christmas.model.DiscountPolicy.D_DAY_DISCOUNT;
import static christmas.model.DiscountPolicy.GIFT_DISCOUNT;
import static christmas.model.DiscountPolicy.SPECIAL_DISCOUNT;
import static christmas.model.DiscountPolicy.WEEKDAY_DISCOUNT;
import static christmas.model.DiscountPolicy.WEEKEND_DISCOUNT;

import christmas.model.vo.DiscountAmount;
import christmas.model.vo.VisitDay;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private static final Integer PRESENT_YEAR = 2023;

    private final Map<DiscountPolicy, DiscountAmount> discountInformation;

    private Discount(final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        this.discountInformation = discountInformation;
    }

    public static Discount create(final Integer orderAmount, final Integer mainQuantity, final Integer dessertQuantity,
                                  final VisitDay visitDay, final boolean isGiftReceived) {
        Map<DiscountPolicy, DiscountAmount> discountInformation = new HashMap<>();

        if (orderAmount <= EVENT_ATTEND_MINIMUM_ORDER_AMOUNT.getAmount()) {
            return new Discount(discountInformation);
        }

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
            DiscountAmount discountAmount = DiscountAmount.create(Menu.getGiftPrice());
            discountInformation.put(GIFT_DISCOUNT, discountAmount);
        }
    }

    private static void putSpecialDayDiscount(final VisitDay visitDay,
                                              final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (visitDay.isSpecialDay()) {
            DiscountAmount discountAmount = DiscountAmount.create(SPECIAL_DISCOUNT_AMOUNT.getAmount());
            discountInformation.put(SPECIAL_DISCOUNT, discountAmount);
        }
    }

    private static void putD_DayDiscount(final VisitDay visitDay,
                                         final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (visitDay.isBeforeOrEqualsChristmas()) {
            DiscountAmount discountAmount = DiscountAmount.create(
                    START_D_DAY_DISCOUNT_AMOUNT.getAmount() + visitDay.getDay() * D_DAY_DISCOUNT_AMOUNT.getAmount());
            discountInformation.put(D_DAY_DISCOUNT, discountAmount);
        }
    }

    private static void putWeekendDayDiscount(final Integer mainQuantity, final VisitDay visitDay,
                                              final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (visitDay.isWeekend() && mainQuantity != 0) {
            DiscountAmount discountAmount = DiscountAmount.create(mainQuantity * PRESENT_YEAR);
            discountInformation.put(WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private static void putWeekDayDiscount(final Integer dessertQuantity, final VisitDay visitDay,
                                           final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        if (!visitDay.isWeekend() && dessertQuantity != 0) {
            DiscountAmount discountAmount = DiscountAmount.create(dessertQuantity * PRESENT_YEAR);
            discountInformation.put(WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    public Integer getGiftDiscount() {
        return discountInformation.getOrDefault(GIFT_DISCOUNT, DiscountAmount.create(0)).getAmount();
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
