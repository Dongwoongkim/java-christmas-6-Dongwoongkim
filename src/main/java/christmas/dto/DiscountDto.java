package christmas.dto;

import static christmas.model.DiscountInfo.D_DAY_DISCOUNT;
import static christmas.model.DiscountInfo.PRESENT_DISCOUNT;
import static christmas.model.DiscountInfo.SPECIAL_DISCOUNT;
import static christmas.model.DiscountInfo.WEEKDAY_DISCOUNT;
import static christmas.model.DiscountInfo.WEEKEND_DISCOUNT;

import christmas.model.Discount;
import java.util.Map;

public class DiscountDto {

    private final Map<String, Integer> discountInfo;

    private DiscountDto(Map<String, Integer> discountInfo) {
        this.discountInfo = discountInfo;
    }

    public static DiscountDto create(Discount discount) {
        return new DiscountDto(discount.getDiscountInfo());
    }

    public Integer getWeekdayDiscount() {
        return discountInfo.getOrDefault(WEEKDAY_DISCOUNT.getPolicy(), 0);
    }

    public Integer getWeekendDayDiscount() {
        return discountInfo.getOrDefault(WEEKEND_DISCOUNT.getPolicy(), 0);
    }

    public Integer getSpecialDayDiscount() {
        return discountInfo.getOrDefault(SPECIAL_DISCOUNT.getPolicy(), 0);
    }

    public Integer getDayDiscount() {
        return discountInfo.getOrDefault(D_DAY_DISCOUNT.getPolicy(), 0);
    }

    public Integer getGiftDiscount() {
        return discountInfo.getOrDefault(PRESENT_DISCOUNT, 0);
    }

    public Map<String, Integer> getDiscountInfo() {
        return discountInfo;
    }
}
