package christmas.vo;

public class Date {

    private static final Integer START_OF_DAY = 1;
    private static final Integer END_OF_DAY = 31;

    private final Integer day;

    public Date(Integer day) {
        validate(day);
        this.day = day;
    }

    private void validate(Integer day) {
        if (!isEventDay(day)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isEventDay(Integer day) {
        if (day >= START_OF_DAY && day <= END_OF_DAY) {
            return true;
        }
        return false;
    }

    public Integer getDay() {
        return day;
    }
}
