package christmas.vo;

import christmas.exception.DayDoesNotExistInCalendarException;
import java.util.List;

public class VisitDay {

    private static final Integer CHRISTMAS_DAY = 25;
    private static final Integer START_EVENT_DAY = 1;
    private static final Integer END_EVENT_DAY = 31;
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
        if (!isInEventDay(day)) {
            throw new DayDoesNotExistInCalendarException();
        }
    }

    private boolean isInEventDay(final Integer day) {
        if (day >= START_EVENT_DAY && day <= END_EVENT_DAY) {
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
        return isSunday() || isChristmasDay() || isEndDayOfEvent();
    }

    private boolean isEndDayOfEvent() {
        return day == END_EVENT_DAY;
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
