package christmas.model.vo;

import christmas.exception.DayDoesNotExistInCalendarException;
import java.util.List;

public class VisitDay {

    private static final Integer CHRISTMAS_DAY = 25;
    private static final Integer START_DAY_OF_MONTH = 1;
    private static final Integer END_DAY_OF_MONTH = 31;
    private static final Integer WEEK_LENGTH = 7;
    private static final Integer SUNDAY_MODULUS = 3;
    private static final List<Integer> WEEKEND_DAY = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);

    private final Integer day;

    private VisitDay(final Integer day) {
        validate(day);
        this.day = day;
    }

    public static VisitDay create(final Integer day) {
        return new VisitDay(day);
    }

    private void validate(final Integer day) {
        if (!isInMonth(day)) {
            throw new DayDoesNotExistInCalendarException();
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

    public boolean isBeforeOrEqualsChristmas() {
        return day <= CHRISTMAS_DAY;
    }

    public boolean isSpecialDay() {
        return isSunday() || isChristmasDay() || isEndDayOfMonth();
    }

    private boolean isEndDayOfMonth() {
        return day == END_DAY_OF_MONTH;
    }

    private boolean isChristmasDay() {
        return day == CHRISTMAS_DAY;
    }

    private boolean isSunday() {
        return day % WEEK_LENGTH == SUNDAY_MODULUS;
    }

    public Integer getDay() {
        return day;
    }
}
