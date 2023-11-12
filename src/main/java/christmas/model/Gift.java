package christmas.model;

import static christmas.model.GiftInfo.NONE_GIFT;
import static christmas.model.GiftInfo.ONE_CHAMPAGNE;

public class Gift {

    private final String name;

    private Gift(final String name) {
        this.name = name;
    }

    public static Gift create(final Integer amount) {
        if (amount >= ONE_CHAMPAGNE.getRequireAmount()) {
            return new Gift(ONE_CHAMPAGNE.getGiftName());
        }
        return new Gift(NONE_GIFT.getGiftName());
    }

    public boolean isReceived() {
        return !name.equals(NONE_GIFT.getGiftName());
    }

    public String getName() {
        return name;
    }
}
