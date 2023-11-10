package christmas.exception;

import static christmas.exception.ErrorMessage.INVALID_INPUT_MENU_ERROR_MESSAGE;

public class ZeroQuantityOrderException extends IllegalArgumentException {

    public ZeroQuantityOrderException() {
        super(INVALID_INPUT_MENU_ERROR_MESSAGE.getMessage());
    }
}
