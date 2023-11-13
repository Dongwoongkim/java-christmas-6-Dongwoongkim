package christmas.model.vo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.exception.OrderNotInMenuException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FoodTest {

    @DisplayName("메뉴에 있는 음식의 이름으로 생성 시 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "양송이수프", "아이스크림", "레드와인"})
    void create_validName_test(String validFoodName) {
        // when & then
        assertDoesNotThrow(() -> new Food(validFoodName));
    }

    @DisplayName("메뉴에 없는 음식의 이름으로 생성 시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"떡볶이", "타코야끼", "붕어빵", "잉어빵"})
    void create_invalidName_test(String invalidFoodName) {
        // when & then
        assertThrows(OrderNotInMenuException.class, () -> new Food(invalidFoodName));
    }

    @DisplayName("주문한 음식이 음료인 경우 true를 리턴한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    void isDrink_true_test(String foodName) {
        // when
        Food drink = new Food(foodName);

        // then
        assertTrue(drink.isDrink());
    }

    @DisplayName("주문한 음식이 음료가 아닌 경우 false를 리턴한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "양송이수프", "아이스크림"})
    void isDrink_false_test(String foodName) {
        // when
        Food drink = new Food(foodName);

        // then
        assertFalse(drink.isDrink());
    }

    @DisplayName("필드값이 동일한 경우 두 객체는 동등, 동일한 객체로 취급한다.")
    @Test
    void equalsAndHashCode_test() {
        // given
        String foodName = "티본스테이크";

        // when
        Food food1 = new Food(foodName);
        Food food2 = new Food(foodName);

        // then
        assertEquals(food1, food2);
        assertEquals(food1.hashCode(), food2.hashCode());
    }

    @DisplayName("필드값이 다른 경우 두 객체는 다른 객체로 취급한다.")
    @Test
    void not_EqualsAndHashCode_test() {
        // when
        Food food1 = new Food("티본스테이크");
        Food food2 = new Food("해산물파스타");

        // then
        assertNotEquals(food1, food2);
        assertNotEquals(food1.hashCode(), food2.hashCode());
    }
}
