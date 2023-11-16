package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.model.vo.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("메뉴에 속한 음식에 대한 가격을 리턴한다.")
    @Test
    public void testGetPriceOfFood() {
        assertEquals(6000, Menu.getPriceOfFood(new Food("양송이수프")));
        assertEquals(55000, Menu.getPriceOfFood(new Food("티본스테이크")));
        assertEquals(15000, Menu.getPriceOfFood(new Food("초코케이크")));
        assertEquals(3000, Menu.getPriceOfFood(new Food("제로콜라")));
    }
}
