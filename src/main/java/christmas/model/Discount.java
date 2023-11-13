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
import static christmas.model.GiftInfo.ONE_CHAMPAGNE;

import christmas.model.vo.VisitDay;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private static final Integer NO_QUANTITY = 0;
    private static final Integer NO_DISCOUNT_AMOUNT = 0;
    private static final Integer PRESENT_YEAR = 2023;

    private final Map<DiscountPolicy, DiscountAmount> discountDetails;

    private Discount(final Map<DiscountPolicy, DiscountAmount> discountDetails) {
        this.discountDetails = discountDetails;
    }

    public static Discount create(final Integer orderAmount, final Integer mainQuantity, final Integer dessertQuantity,
                                  final VisitDay visitDay, final boolean isGiftReceived) {
        Map<DiscountPolicy, DiscountAmount> discountDetails = new HashMap<>();

        if (orderAmount <= EVENT_ATTEND_MINIMUM_ORDER_AMOUNT.getAmount()) {
            return new Discount(discountDetails);
        }

        putWeekDayDiscount(dessertQuantity, visitDay, discountDetails);
        putWeekendDayDiscount(mainQuantity, visitDay, discountDetails);
        putD_DayDiscount(visitDay, discountDetails);
        putSpecialDayDiscount(visitDay, discountDetails);
        putGiftDiscount(isGiftReceived, discountDetails);

        return new Discount(discountDetails);
    }

    private static void putGiftDiscount(final boolean isGiftReceived,
                                        final Map<DiscountPolicy, DiscountAmount> discountDetails) {
        if (isGiftReceived) {
            DiscountAmount discountAmount = DiscountAmount.create(ONE_CHAMPAGNE.getGiftAmount());
            discountDetails.put(GIFT_DISCOUNT, discountAmount);
        }
    }

    private static void putSpecialDayDiscount(final VisitDay visitDay,
                                              final Map<DiscountPolicy, DiscountAmount> discountDetails) {
        if (visitDay.isSpecialDay()) {
            DiscountAmount discountAmount = DiscountAmount.create(SPECIAL_DISCOUNT_AMOUNT.getAmount());
            discountDetails.put(SPECIAL_DISCOUNT, discountAmount);
        }
    }

    private static void putD_DayDiscount(final VisitDay visitDay,
                                         final Map<DiscountPolicy, DiscountAmount> discountDetails) {
        if (visitDay.isBeforeOrEqualsChristmas()) {
            DiscountAmount discountAmount = DiscountAmount.create(
                    START_D_DAY_DISCOUNT_AMOUNT.getAmount() + visitDay.day() * D_DAY_DISCOUNT_AMOUNT.getAmount());
            discountDetails.put(D_DAY_DISCOUNT, discountAmount);
        }
    }

    private static void putWeekendDayDiscount(final Integer mainQuantity, final VisitDay visitDay,
                                              final Map<DiscountPolicy, DiscountAmount> discountDetails) {
        if (visitDay.isWeekend() && mainQuantity != NO_QUANTITY) {
            DiscountAmount discountAmount = DiscountAmount.create(mainQuantity * PRESENT_YEAR);
            discountDetails.put(WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private static void putWeekDayDiscount(final Integer dessertQuantity, final VisitDay visitDay,
                                           final Map<DiscountPolicy, DiscountAmount> discountDetails) {
        if (!visitDay.isWeekend() && dessertQuantity != NO_QUANTITY) {
            DiscountAmount discountAmount = DiscountAmount.create(dessertQuantity * PRESENT_YEAR);
            discountDetails.put(WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    public Integer getGiftDiscount() {
        return discountDetails.getOrDefault(GIFT_DISCOUNT, DiscountAmount.create(NO_DISCOUNT_AMOUNT)).getAmount();
    }

    public Integer getSumOfDiscount() {
        return discountDetails.values()
                .stream()
                .mapToInt(DiscountAmount::getAmount)
                .sum();
    }

    public Map<DiscountPolicy, DiscountAmount> getDiscountDetails() {
        return Collections.unmodifiableMap(discountDetails);
    }
}
