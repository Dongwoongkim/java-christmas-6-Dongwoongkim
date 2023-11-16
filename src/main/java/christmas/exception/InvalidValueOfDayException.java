package christmas.exception;

import static christmas.exception.message.ErrorMessage.INVALID_INPUT_DAY_ERROR_MESSAGE;

public class InvalidValueOfDayException extends IllegalArgumentException {

    public InvalidValueOfDayException() {
        super(INVALID_INPUT_DAY_ERROR_MESSAGE.getMessage());
    }
}
