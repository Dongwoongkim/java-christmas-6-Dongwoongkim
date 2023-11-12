package christmas.exception;

import static christmas.exception.message.ErrorMessage.INVALID_INPUT_DAY_ERROR_MESSAGE;

public class DayDoesNotExistInCalendarException extends IllegalArgumentException {

    public DayDoesNotExistInCalendarException() {
        super(INVALID_INPUT_DAY_ERROR_MESSAGE.getMessage());
    }
}
