package christmas.vo;

import christmas.exception.ZeroQuantityOrderException;

public class Quantity {

    private static final Integer ZERO_QUANTITY = 0;
    private final Integer quantity;

    public Quantity(Integer quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private void validateQuantity(Integer quantity) {
        if (quantityIsZero(quantity)) {
            throw new ZeroQuantityOrderException();
        }
    }

    private boolean quantityIsZero(Integer quantity) {
        return quantity == ZERO_QUANTITY;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
