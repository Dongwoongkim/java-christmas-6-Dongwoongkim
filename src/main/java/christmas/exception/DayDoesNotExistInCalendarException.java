package christmas.exception;

import static christmas.exception.ErrorMessage.INVALID_INPUT_MENU_ERROR_MESSAGE;

public class DayDoesNotExistInCalendarException extends IllegalArgumentException {

    public DayDoesNotExistInCalendarException() {
        super(INVALID_INPUT_MENU_ERROR_MESSAGE.getMessage());
    }
}
