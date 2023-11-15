package christmas.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.DayNonNumericException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @DisplayName("입력값이 숫자인 경우 검증에 통과한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "1000"})
    void valid_Numeric_Day_test(String day) {
        // then
        assertDoesNotThrow(() -> InputValidator.validateDay(day));
    }

    @DisplayName("입력값이 숫자가 아닌 경우 검증에 통과하지 못한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "-", "!"})
    void invalid_Numeric_Day_exception_test(String day) {
        // then
        assertThrows(DayNonNumericException.class, () -> InputValidator.validateDay(day));
    }
}
