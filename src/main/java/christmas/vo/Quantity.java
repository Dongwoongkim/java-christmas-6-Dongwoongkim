package christmas.vo;

import christmas.exception.ZeroQuantityOrderException;

public class Quantity {

    private static final Integer ZERO_QUANTITY = 0;
    private final Integer quantity;

    public Quantity(Integer quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(Integer quantity) {
        if (isQuantityZero(quantity)) {
            throw new ZeroQuantityOrderException();
        }
    }

    private boolean isQuantityZero(Integer quantity) {
        return quantity == ZERO_QUANTITY;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
