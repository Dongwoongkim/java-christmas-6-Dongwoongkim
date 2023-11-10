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
        if (!isValidDay(day)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isValidDay(Integer day) {
        if (day >= START_OF_DAY && day <= END_OF_DAY) {
            return true;
        }
        return false;
    }

    public boolean isSpecialDay() {
        if (day == 3 || day == 10 || day == 17 || day == 24 || day == 25 || day == 31) {
            return true;
        }
        return false;
    }

    public Integer getDay() {
        return day;
    }
}
