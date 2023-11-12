package christmas.model;

public enum GiftInfo {

    ONE_CHAMPAGNE("샴페인 1개", 120000, 25000),
    NONE_GIFT("없음", 0, 0);

    private final String giftName;
    private final Integer requireAmount;
    private final Integer giftAmount;

    GiftInfo(String giftName, Integer requireAmount, Integer giftAmount) {
        this.giftName = giftName;
        this.requireAmount = requireAmount;
        this.giftAmount = giftAmount;
    }

    public String getGiftName() {
        return giftName;
    }

    public Integer getRequireAmount() {
        return requireAmount;
    }

    public Integer getGiftAmount() {
        return giftAmount;
    }
}
