package christmas.util;

import christmas.exception.DayNonNumericException;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateDay(final String day) {
        if (!isNumeric(day)) {
            throw new DayNonNumericException();
        }
    }

    private static boolean isNumeric(final String day) {
        try {
            Integer.valueOf(day);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
