package christmas.model;

public enum GiftInfo {

    ONE_CHAMPAGNE("샴페인 1개", 120000, 25000),
    NONE_GIFT("없음", 0, 0);

    private final String giftName;
    private final Integer requiredAmount;
    private final Integer giftAmount;

    GiftInfo(final String giftName, final Integer requiredAmount, final Integer giftAmount) {
        this.giftName = giftName;
        this.requiredAmount = requiredAmount;
        this.giftAmount = giftAmount;
    }

    public String getGiftName() {
        return giftName;
    }

    public Integer getRequiredAmount() {
        return requiredAmount;
    }

    public Integer getGiftAmount() {
        return giftAmount;
    }
}
