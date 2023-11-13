package christmas.model.vo;

import christmas.exception.ZeroQuantityOrderException;

public record Quantity(Integer amount) {

    private static final Integer ZERO_AMOUNT = 0;

    public Quantity {
        validate(amount);
    }

    private void validate(final Integer amount) {
        if (isAmountZero(amount)) {
            throw new ZeroQuantityOrderException();
        }
    }

    private boolean isAmountZero(final Integer amount) {
        return amount == ZERO_AMOUNT;
    }
}
