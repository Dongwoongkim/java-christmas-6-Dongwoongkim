package christmas.model;

public enum EventInfo {

    SPECIAL_DISCOUNT_AMOUNT(1000),
    ONE_DAY_DISCOUNT(100),
    START_DAY_DISCOUNT(900),
    CHRISTMAS_DAY(25),
    START_EVENT_DAY(1),
    END_EVENT_DAY(31),
    PRESENT_YEAR(2023),
    MAX_ORDER_QUANTITY(20),
    GIFT_REQUIREMENT_AMOUNT(120000),
    SANTA_BADGE_REQUIREMENT_AMOUNT(20000),
    TREE_BADGE_REQUIREMENT_AMOUNT(10000),
    STAR_BADGE_REQUIREMENT_AMOUNT(5000);

    private final Integer value;

    EventInfo(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
