package christmas.exception;

import static christmas.exception.message.ErrorMessage.INVALID_INPUT_MENU_ERROR_MESSAGE;

public class OnlyDrinkOrderException extends IllegalArgumentException {

    public OnlyDrinkOrderException() {
        super(INVALID_INPUT_MENU_ERROR_MESSAGE.getMessage());
    }
}
