package christmas.model;

public enum EventInfo {

    MINIMUM_ORDER_AMOUNT_TO_ATTEND_EVENT(10000),
    SPECIAL_DISCOUNT_AMOUNT(1000),
    D_DAY_DISCOUNT_AMOUNT(100),
    START_D_DAY_DISCOUNT_AMOUNT(900),
    CHRISTMAS_DAY(25),
    START_EVENT_DAY(1),
    END_EVENT_DAY(31),
    PRESENT_YEAR(2023),
    MAX_ORDER_QUANTITY(20),
    GIFT_REQUIRE_AMOUNT(120000),
    SANTA_BADGE_REQUIRE_AMOUNT(20000),
    TREE_BADGE_REQUIRE_AMOUNT(10000),
    STAR_BADGE_REQUIRE_AMOUNT(5000);

    private final Integer value;

    EventInfo(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
