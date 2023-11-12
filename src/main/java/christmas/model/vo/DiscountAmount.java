package christmas.model.vo;

public class DiscountAmount {

    private final Integer amount;

    private DiscountAmount(final Integer amount) {
        this.amount = amount;
    }

    public static DiscountAmount create(final Integer amount) {
        return new DiscountAmount(amount);
    }

    public Integer getAmount() {
        return amount;
    }
}
