package christmas.exception;

import static christmas.exception.message.ErrorMessage.INVALID_INPUT_MENU_ERROR_MESSAGE;

public class OrderNotInMenuException extends IllegalArgumentException {

    public OrderNotInMenuException() {
        super(INVALID_INPUT_MENU_ERROR_MESSAGE.getMessage());
    }
}
