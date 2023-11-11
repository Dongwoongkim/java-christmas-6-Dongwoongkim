package christmas.vo;

import static christmas.model.EventInfo.END_EVENT_DAY;
import static christmas.model.EventInfo.START_EVENT_DAY;

import christmas.exception.DayDoesNotExistInCalendarException;

public class VisitDay {

    private static final Integer START_OF_DAY = 1;
    private static final Integer END_OF_DAY = 31;

    private final Integer day;

    public VisitDay(Integer day) {
        validate(day);
        this.day = day;
    }

    private void validate(Integer day) {
        if (!isValidDay(day)) {
            throw new DayDoesNotExistInCalendarException();
        }
    }

    private boolean isValidDay(Integer day) {
        if (day >= START_EVENT_DAY.getValue() && day <= END_EVENT_DAY.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isSpecialDay() {
        return isSunday() || isChristmasDay() || isEndDayOfEvent();
    }

    private boolean isEndDayOfEvent() {
        return day == END_EVENT_DAY.getValue();
    }

    private boolean isChristmasDay() {
        return day == 25;
    }

    private boolean isSunday() {
        return day % 7 == 3;
    }

    public Integer getDay() {
        return day;
    }
}
