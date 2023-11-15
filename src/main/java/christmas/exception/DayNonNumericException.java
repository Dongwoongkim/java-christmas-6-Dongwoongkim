package christmas.exception;

import static christmas.exception.message.ErrorMessage.INVALID_INPUT_DAY_ERROR_MESSAGE;

public class DayNonNumericException extends IllegalArgumentException {

    public DayNonNumericException() {
        super(INVALID_INPUT_DAY_ERROR_MESSAGE.getMessage());
    }
}
