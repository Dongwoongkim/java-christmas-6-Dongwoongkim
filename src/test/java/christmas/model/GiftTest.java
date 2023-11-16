package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftTest {

    @DisplayName("증정 상품 생성 테스트 ")
    @Test
    void createGift_test() {
        assertEquals("샴페인 1개", Gift.create(120000).getName());
        assertEquals("없음", Gift.create(5000).getName());
    }
    
    @DisplayName("증정 상품 증정 여부 테스트")
    @Test
    void isReceived_test() {
        assertEquals(true, Gift.create(120000).isReceived());
        assertEquals(false, Gift.create(5000).isReceived());
    }
}
