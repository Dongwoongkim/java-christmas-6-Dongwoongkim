package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.OnlyDrinkOrderException;
import christmas.exception.OverMaxQuantityOrderException;
import christmas.model.vo.Food;
import christmas.model.vo.Quantity;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("최대 주문량을 초과하는 경우 예외가 발생한다.")
    @Test
    void create_order_overMaxQuantity_test() {
        // given
        Map<Food, Quantity> foodAndQuantity = new HashMap<>();

        // when
        foodAndQuantity.put(new Food("양송이수프"), new Quantity(21));

        // then
        assertThrows(OverMaxQuantityOrderException.class, () -> Order.create(foodAndQuantity));
    }

    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void create_order_containsOnlyDrinks_test() {
        // given
        Map<Food, Quantity> foodAndQuantity = new HashMap<>();

        // when
        foodAndQuantity.put(new Food("제로콜라"), new Quantity(5));
        foodAndQuantity.put(new Food("레드와인"), new Quantity(3));

        // then
        assertThrows(OnlyDrinkOrderException.class, () -> Order.create(foodAndQuantity));
    }

    @DisplayName("총 주문 금액을 반환한다.")
    @Test
    void get_amount_test() {
        // given
        Map<Food, Quantity> foodAndQuantity = new HashMap<>();

        // when
        foodAndQuantity.put(new Food("양송이수프"), new Quantity(2));
        foodAndQuantity.put(new Food("티본스테이크"), new Quantity(1));
        Order order = Order.create(foodAndQuantity);

        // then
        assertEquals(6000 * 2 + 55000 * 1, order.getAmount());
    }

    @Test
    @DisplayName("주문에 대한 디저트의 수량을 리턴한다.")
    void get_dessertQuantity_test() {
        // given
        Map<Food, Quantity> foodAndQuantity = new HashMap<>();

        // when
        foodAndQuantity.put(new Food("초코케이크"), new Quantity(2));
        foodAndQuantity.put(new Food("아이스크림"), new Quantity(1));
        foodAndQuantity.put(new Food("레드와인"), new Quantity(1));

        Order order = Order.create(foodAndQuantity);

        // then
        assertEquals(3, order.getDessertQuantity());
    }

    @Test
    @DisplayName("주문에 대한 메인 메뉴 수량을 리턴한다.")
    void get_mainQuantity_test() {
        // given
        Map<Food, Quantity> foodAndQuantity = new HashMap<>();

        // when
        foodAndQuantity.put(new Food("티본스테이크"), new Quantity(2));
        foodAndQuantity.put(new Food("바비큐립"), new Quantity(1));
        foodAndQuantity.put(new Food("레드와인"), new Quantity(1));
        Order order = Order.create(foodAndQuantity);

        // then
        assertEquals(3, order.getMainQuantity());
    }
}
