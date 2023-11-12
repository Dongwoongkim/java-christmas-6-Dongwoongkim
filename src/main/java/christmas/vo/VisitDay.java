package christmas.vo;

import static christmas.model.EventInfo.CHRISTMAS_DAY;
import static christmas.model.EventInfo.END_EVENT_DAY;
import static christmas.model.EventInfo.START_EVENT_DAY;

import christmas.exception.DayDoesNotExistInCalendarException;
import java.util.List;

public class VisitDay {

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
        if (day >= START_EVENT_DAY.getValue() && day <= END_EVENT_DAY.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isWeekend() {
        return WEEKEND_DAY.contains(day);
    }

    public boolean isBeforeOrEqualsChristmas() {
        return day <= CHRISTMAS_DAY.getValue();
    }

    public boolean isSpecialDay() {
        return isSunday() || isChristmasDay() || isEndDayOfEvent();
    }

    private boolean isEndDayOfEvent() {
        return day == END_EVENT_DAY.getValue();
    }

    private boolean isChristmasDay() {
        return day == CHRISTMAS_DAY.getValue();
    }

    private boolean isSunday() {
        return day % WEEK_LENGTH == SUNDAY_MODULUS;
    }

    public Integer getDay() {
        return day;
    }
}
