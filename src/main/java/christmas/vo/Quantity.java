package christmas.vo;

import christmas.exception.ZeroQuantityOrderException;

public class Quantity {

    private static final Integer ZERO_AMOUNT = 0;
    private final Integer amount;

    public Quantity(final Integer amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Quantity create(final Integer amount) {
        return new Quantity(amount);
    }

    private void validate(final Integer amount) {
        if (isAmountZero(amount)) {
            throw new ZeroQuantityOrderException();
        }
    }

    private boolean isAmountZero(final Integer amount) {
        return amount == ZERO_AMOUNT;
    }

    public Integer getQuantity() {
        return amount;
    }
}
