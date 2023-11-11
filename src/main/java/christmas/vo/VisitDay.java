package christmas.vo;

import static christmas.model.EventInfo.CHRISTMAS_DAY;
import static christmas.model.EventInfo.END_EVENT_DAY;

import christmas.exception.DayDoesNotExistInCalendarException;
import java.util.List;

public class VisitDay {

    private static final List<Integer> WEEK_DAY = List.of(3, 4, 5, 6, 7, 8,
            10, 11, 12, 13, 14, 15,
            17, 18, 19, 20, 21, 22,
            24, 25, 26, 27, 28, 29,
            31);
    private static final Integer START_OF_DAY = 1;
    private static final Integer END_OF_DAY = 31;

    private final Integer day;

    public VisitDay(Integer day) {
        validateDay(day);
        this.day = day;
    }

    private void validateDay(Integer day) {
        if (!isInDecember(day)) {
            throw new DayDoesNotExistInCalendarException();
        }
    }

    private boolean isInDecember(Integer day) {
        if (day >= START_OF_DAY && day <= END_OF_DAY) {
            return true;
        }
        return false;
    }

    public boolean isWeekDay() {
        return WEEK_DAY.contains(day);
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
        return day % 7 == 3;
    }

    public Integer getDay() {
        return day;
    }
}
