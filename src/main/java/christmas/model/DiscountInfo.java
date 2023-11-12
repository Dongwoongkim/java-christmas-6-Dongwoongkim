package christmas.model;

public enum DiscountInfo {

    START_D_DAY_DISCOUNT_AMOUNT(900),
    D_DAY_DISCOUNT_AMOUNT(100),
    EVENT_ATTEND_MINIMUM_ORDER_AMOUNT(10000),
    SPECIAL_DISCOUNT_AMOUNT(1000);

    private final Integer amount;

    DiscountInfo(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
