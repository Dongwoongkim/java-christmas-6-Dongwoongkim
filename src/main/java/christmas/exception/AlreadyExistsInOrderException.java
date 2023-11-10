package christmas.exception;

import static christmas.exception.message.ErrorMessage.INVALID_INPUT_MENU_ERROR_MESSAGE;

public class AlreadyExistsInOrderException extends IllegalArgumentException {

    public AlreadyExistsInOrderException() {
        super(INVALID_INPUT_MENU_ERROR_MESSAGE.getMessage());
    }
}
