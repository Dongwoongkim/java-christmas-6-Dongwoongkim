package christmas.vo;

import christmas.exception.ZeroQuantityOrderException;

public class Quantity {

    private static final Integer ZERO_QUANTITY = 0;
    private final Integer quantity;

    public Quantity(final Integer quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    public static Quantity create(Integer quantity) {
        return new Quantity(quantity);
    }

    private void validate(final Integer quantity) {
        if (isQuantityZero(quantity)) {
            throw new ZeroQuantityOrderException();
        }
    }

    private boolean isQuantityZero(final Integer quantity) {
        return quantity == ZERO_QUANTITY;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
