package christmas.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.AlreadyExistsInOrderException;
import christmas.exception.InvalidOrderFormatException;
import christmas.exception.OrderMenuCountNonNumericException;
import christmas.model.vo.Food;
import christmas.model.vo.Quantity;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputConverterTest {


    @DisplayName("유효한 주문의 경우 Food와 Quantity 불변 객체로 변환 후 맵에 삽입하여 리턴한다.")
    @Test
    void valid_stringToMapTest() {
        // given
        String validMenuString = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        // when
        Map<Food, Quantity> convertedMap = InputConverter.stringToMap(validMenuString);

        // then
        assertEquals(4, convertedMap.size());
        assertEquals(1, convertedMap.get(Food.create("티본스테이크")).getQuantity());
        assertEquals(1, convertedMap.get(Food.create("바비큐립")).getQuantity());
        assertEquals(2, convertedMap.get(Food.create("초코케이크")).getQuantity());
        assertEquals(1, convertedMap.get(Food.create("제로콜라")).getQuantity());
    }

    @DisplayName("주문 메뉴에 중복된 메뉴가 있는 경우 변환 시 예외가 발생한다.")
    @Test
    void invalid_duplicate_food_stringToMap_test() {
        // given
        String invalidOrder = "티본스테이크-3,티본스테이크-5";

        // when & then
        assertThrows(AlreadyExistsInOrderException.class, () -> InputConverter.stringToMap(invalidOrder));
    }

    @DisplayName("주문 수량에 문자가 포함된 경우 변환 시 예외가 발생한다.")
    @Test
    void invalid_non_numeric_quantity_stringToMap_exception_test() {
        // given
        String invalidOrder = "티본스테이크-a,제로콜라-1";

        // when & then
        assertThrows(OrderMenuCountNonNumericException.class, () -> InputConverter.stringToMap(invalidOrder));
    }

    @DisplayName("주문 수량이 없는 경우 변환 시 예외가 발생한다.")
    @Test
    void invalid_no_quantity_stringToMap_exception_test() {
        // given
        String invalidOrder = "티본스테이크-,제로콜라-";

        // when & then
        assertThrows(OrderMenuCountNonNumericException.class, () -> InputConverter.stringToMap(invalidOrder));
    }

    @DisplayName("주문음식과 주문 수량이 없는 경우 변환 시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-,-", "-"})
    void invalid_order_format_stringToMap_exception_test(String invalidOrder) {
        // when & then
        assertThrows(InvalidOrderFormatException.class, () -> InputConverter.stringToMap(invalidOrder));
    }
}
