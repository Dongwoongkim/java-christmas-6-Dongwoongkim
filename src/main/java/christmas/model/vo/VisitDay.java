package christmas.model.vo;

import christmas.exception.InvalidValueOfDayException;
import java.util.List;

public record VisitDay(Integer day) {

    private static final Integer CHRISTMAS_DAY = 25;
    private static final Integer START_DAY_OF_MONTH = 1;
    private static final Integer END_DAY_OF_MONTH = 31;
    private static final Integer WEEK_LENGTH = 7;
    private static final Integer SUNDAY_MODULUS = 3;
    private static final List<Integer> WEEKEND_DAY = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);

    public VisitDay {
        validate(day);
    }

    private void validate(final Integer day) {
        if (!isInMonth(day)) {
            throw new InvalidValueOfDayException();
        }
    }

    private boolean isInMonth(final Integer day) {
        if (day >= START_DAY_OF_MONTH && day <= END_DAY_OF_MONTH) {
            return true;
        }
        return false;
    }

    public boolean isWeekend() {
        return WEEKEND_DAY.contains(day);
    }

    public boolean isChristmasD_Day() {
        return day <= CHRISTMAS_DAY;
    }

    public boolean isSpecialDay() {
        return isSunday() || isChristmasDay();
    }

    private boolean isChristmasDay() {
        return day.equals(CHRISTMAS_DAY);
    }

    private boolean isSunday() {
        return day % WEEK_LENGTH == SUNDAY_MODULUS;
    }
}
