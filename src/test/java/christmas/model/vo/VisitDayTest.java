package christmas.model.vo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.exception.DayDoesNotExistInCalendarException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDayTest {

    @DisplayName("1-31사이의 숫자인 경우 예외가 발생하지 않으며 객체가 생성된다.")
    @Test
    void validDay_InMonth_test() {
        // when
        Integer validDay = 15;

        // then
        VisitDay visitDay = assertDoesNotThrow(() -> new VisitDay(validDay));
        assertEquals(validDay, visitDay.day());
    }

    @DisplayName("1-31사이의 숫자가 아닌 경우 예외가 발생하며 객체가 생성되지 않는다.")
    @Test
    void invalidDay_OutMonth_test_() {
        // then
        assertThrows(DayDoesNotExistInCalendarException.class, () -> new VisitDay(40));
    }

    @DisplayName("필드값이 주말에 속하는 경우 true를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void isWeekend_true_test(Integer day) {
        // when
        VisitDay visitDay = new VisitDay(day);

        // then
        assertTrue(visitDay.isWeekend());
    }

    @DisplayName("필드값이 평일에 속하는 경우 false를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14})
    void isWeekend_false_test(Integer day) {
        // when
        VisitDay visitDay = new VisitDay(day);

        // then
        assertFalse(visitDay.isWeekend());
    }

    @DisplayName("크리스마스 이전인 경우 true를 리턴한다.")
    @Test
    void isBeforeOrEqualsChristmas_test() {
        // when
        VisitDay visitDay = new VisitDay(20);

        // then
        assertTrue(visitDay.isBeforeOrEqualsChristmas());
    }

    @DisplayName("크리스마스 이후인 경우 false를 리턴한다.")
    @Test
    void isNotBeforeOrEqualsChristmas_test() {
        // when
        VisitDay visitDay = new VisitDay(26);

        // then
        assertFalse(visitDay.isBeforeOrEqualsChristmas());
    }

    @DisplayName("스페셜 데이인 경우 true를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void isSpecialDay_test(Integer day) {
        // when
        VisitDay visitDay = new VisitDay(day);

        // then
        assertTrue(visitDay.isSpecialDay());
    }

    @DisplayName("스페셜 데이가 아닌경우 false를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7})
    void isNotSpecialDay_test(Integer day) {
        // when
        VisitDay visitDay = new VisitDay(day);

        // then
        assertFalse(visitDay.isSpecialDay());
    }
}
