package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.model.vo.VisitDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {

    @Test
    @DisplayName("증정 상품을 받은 경우 증정 상품의 가격을 리턴한다.")
    void getGiftDiscount_giftReceived() {
        // given
        VisitDay visitDay = new VisitDay(25);

        // when
        Discount discount = Discount.create(60000, 4, 2, visitDay, true);

        // then
        assertEquals(25000, discount.getGiftDiscount());
    }

    @Test
    @DisplayName("증정 상품을 받지 않은 경우 0원을 리턴한다.")
    void getGiftDiscount_giftIsNotReceived() {
        // given
        VisitDay visitDay = new VisitDay(25);

        // when
        Discount discount = Discount.create(60000, 4, 2, visitDay, false);

        // then
        assertEquals(0, discount.getGiftDiscount());
    }


    @Test
    @DisplayName("주문 금액이 1만원을 넘지 않는 경우 할인이 적용되지 않는다.")
    void event_attend_minimum_order_amount_test() {
        // given
        VisitDay visitDay = new VisitDay(3);

        // when
        Discount discount = Discount.create(9999, 1, 0, visitDay, false);

        // then
        assertEquals(0, discount.getSumOfDiscount());
    }

    @Test
    @DisplayName("스페셜 데이에 대한 할인금액")
    void specialDayDiscount_test() {
        // given
        VisitDay visitDay = new VisitDay(3);

        // when
        Discount discount = Discount.create(11000, 1, 0, visitDay, false);

        // then
        assertEquals(1000 + 1200, discount.getSumOfDiscount());
    }

    @Test
    @DisplayName("D-Day에 대한 할인 테스트")
    void d_dayDiscount_test() {
        // given
        VisitDay visitDay = new VisitDay(25);

        // when
        Discount discount = Discount.create(25000, 3, 2, visitDay, false);

        // then
        assertEquals(1000 + 3400 + 2023 * 2, discount.getSumOfDiscount());
    }

    @Test
    @DisplayName("평일에 대한 할인 테스트")
    void weekendDayDiscount_test() {
        // given
        VisitDay visitDay = new VisitDay(17);

        // when
        Discount discount = Discount.create(50000, 3, 2, visitDay, false);

        // then
        assertEquals(1000 + 2 * 2023 + 2600, discount.getSumOfDiscount());
    }

    @Test
    @DisplayName("주말에 대한 할인 테스트")
    void weekDayDiscount_test() {
        // given
        VisitDay visitDay = new VisitDay(16);

        // when
        Discount discount = Discount.create(50000, 3, 2, visitDay, false);

        // then
        assertEquals(3 * 2023 + 2500, discount.getSumOfDiscount());
    }
}
