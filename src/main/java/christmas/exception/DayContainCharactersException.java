package christmas.exception;

import static christmas.exception.ErrorMessage.INVALID_INPUT_DAY_ERROR_MESSAGE;

public class DayContainCharactersException extends IllegalArgumentException {

    public DayContainCharactersException() {
        super(INVALID_INPUT_DAY_ERROR_MESSAGE.getMessage());
    }
}
