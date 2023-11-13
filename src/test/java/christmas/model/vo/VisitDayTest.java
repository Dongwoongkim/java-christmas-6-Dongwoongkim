package christmas.model.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VisitDayTest {

    @DisplayName("1-31사이의 숫자인 경우 예외가 발생하지 않으며 객체가 생성된다.")
    @Test
    void validDay_InMonth_test() {
        // given
        Integer validDay = 15;

        // when
        VisitDay visitDay = VisitDay.create(validDay);

        // then
        assertEquals(validDay, visitDay.day());
    }
}
