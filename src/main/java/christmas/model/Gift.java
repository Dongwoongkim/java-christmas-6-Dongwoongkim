package christmas.model;

import static christmas.model.EventInfo.GIFT_REQUIREMENT_AMOUNT;

public class Gift {

    private final String name;

    public Gift(String name) {
        this.name = name;
    }

    public static Gift createGift(Integer amount) {
        if (amount >= GIFT_REQUIREMENT_AMOUNT.getValue()) {
            return new Gift("샴페인 1개");
        }
        return new Gift("없음");
    }

    public String getName() {
        return name;
    }
}
