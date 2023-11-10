package christmas.validation;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateDay(String day) {
        if (!isNumeric(day)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
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
