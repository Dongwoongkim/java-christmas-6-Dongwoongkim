package christmas.validation;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateDay(String day) {
        if (!isNumeric(day)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isNumeric(String day) {
        try {
            Integer.valueOf(day);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
