package christmas.model;

public enum DiscountPolicy {

    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    D_DAY_DISCOUNT("크리스마스 디데이 할인"),
    GIFT_DISCOUNT("증정 이벤트"),
    SPECIAL_DISCOUNT("특별 할인");

    private final String policy;

    DiscountPolicy(final String policy) {
        this.policy = policy;
    }

    public String getPolicy() {
        return policy;
    }
}
