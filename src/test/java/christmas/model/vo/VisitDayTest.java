package christmas.model.vo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
