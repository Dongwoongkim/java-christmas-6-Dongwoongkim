package christmas.model.vo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.ZeroQuantityOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuantityTest {

    @DisplayName("값이 0인경우 검증에 실패하여 객체가 생성되지 않는다.")
    @Test
    void invalid_amount_create_Quantity_test() {
        // when
        Integer zeroAmount = 0;

        // then
        assertThrows(ZeroQuantityOrderException.class, () -> new Quantity(zeroAmount));
    }

    @DisplayName("값이 0이 아닌 경우 검증에 성공하여 객체가 생성된다.")
    @Test
    void valid_amount_create_Quantity_test() {
        // when
        Integer validAmount = 3;

        // then
        assertDoesNotThrow(() -> new Quantity(validAmount));
    }

    @DisplayName("필드값이 동일한 경우 두 객체는 동등, 동일한 객체로 취급한다.")
    @Test
    void equalsAndHashCode_test() {
        // given
        Integer amount = 3;

        // when
        Quantity quantity1 = new Quantity(amount);
        Quantity quantity2 = new Quantity(amount);

        // then
        assertEquals(quantity1, quantity2);
        assertEquals(quantity1.hashCode(), quantity2.hashCode());
    }

    @DisplayName("필드값이 다른 경우 두 객체는 다른 객체로 취급한다.")
    @Test
    void not_EqualsAndHashCode_test() {
        // when
        Quantity quantity1 = new Quantity(1);
        Quantity quantity2 = new Quantity(2);

        // then
        assertNotEquals(quantity1, quantity2);
        assertNotEquals(quantity1.hashCode(), quantity2.hashCode());
    }
}
