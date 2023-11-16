package christmas.exception;

import static christmas.exception.message.ErrorMessage.INVALID_INPUT_MENU_ERROR_MESSAGE;

public class InvalidOrderFormatException extends IllegalArgumentException {

    public InvalidOrderFormatException() {
        super(INVALID_INPUT_MENU_ERROR_MESSAGE.getMessage());
    }
}
