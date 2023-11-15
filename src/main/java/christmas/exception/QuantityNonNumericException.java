package christmas.exception;

import static christmas.exception.message.ErrorMessage.INVALID_INPUT_MENU_ERROR_MESSAGE;

public class QuantityNonNumericException extends IllegalArgumentException {

    public QuantityNonNumericException() {
        super(INVALID_INPUT_MENU_ERROR_MESSAGE.getMessage());
    }
}
