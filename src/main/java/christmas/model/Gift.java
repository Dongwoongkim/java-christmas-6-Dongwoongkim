package christmas.model;

import static christmas.model.EventInfo.GIFT_REQUIRE_AMOUNT;

public class Gift {

    private static final String ONE_CHAMPAGNE = "샴페인 1개";
    private static final String NONE_GIFT = "없음";
    private final String name;

    private Gift(final String name) {
        this.name = name;
    }

    public static Gift createGift(final Integer amount) {
        if (amount >= GIFT_REQUIRE_AMOUNT.getValue()) {
            return new Gift(ONE_CHAMPAGNE);
        }
        return new Gift(NONE_GIFT);
    }

    public boolean isReceived() {
        return !name.equals(NONE_GIFT);
    }

    public String getName() {
        return name;
    }
}
