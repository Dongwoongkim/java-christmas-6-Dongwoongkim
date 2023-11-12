package christmas.model;

public enum GiftInfo {

    ONE_CHAMPAGNE("샴페인 1개", 120000),
    NONE_GIFT("없음", 0);

    private final String giftName;
    private final Integer requireAmount;

    GiftInfo(String giftName, Integer requireAmount) {
        this.giftName = giftName;
        this.requireAmount = requireAmount;
    }

    public String getGiftName() {
        return giftName;
    }

    public Integer getRequireAmount() {
        return requireAmount;
    }
}
