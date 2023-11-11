package christmas.vo;

import static christmas.model.EventInfo.CHRISTMAS_DAY;
import static christmas.model.EventInfo.END_EVENT_DAY;
import static christmas.model.EventInfo.START_EVENT_DAY;

import christmas.exception.DayDoesNotExistInCalendarException;
import java.util.List;

public class VisitDay {

    private static final Integer WEEK_LENGTH = 7;
    private static final Integer SUNDAY_MODULUS = 3;

    private static final List<Integer> WEEK_DAY = List.of(3, 4, 5, 6, 7, 8,
            10, 11, 12, 13, 14, 15,
            17, 18, 19, 20, 21, 22,
            24, 25, 26, 27, 28, 29,
            31);

    private final Integer day;

    public VisitDay(Integer day) {
        validate(day);
        this.day = day;
    }

    private void validate(Integer day) {
        if (!isInEventDay(day)) {
            throw new DayDoesNotExistInCalendarException();
        }
    }

    private boolean isInEventDay(Integer day) {
        if (day >= START_EVENT_DAY.getValue() && day <= END_EVENT_DAY.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isWeekDay() {
        return WEEK_DAY.contains(day);
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
