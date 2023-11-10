package christmas.exception;

import static christmas.exception.ErrorMessage.INVALID_INPUT_MENU_ERROR_MESSAGE;

public class OrderMenuCountNonNumericException extends IllegalArgumentException {

    public OrderMenuCountNonNumericException() {
        super(INVALID_INPUT_MENU_ERROR_MESSAGE.getMessage());
    }
}
