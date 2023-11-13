package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("총 할인 금액에 따른 배지 생성 테스트")
    @Test
    void testCreateBadge() {
        assertEquals("산타", Badge.create(25000).getName());
        assertEquals("트리", Badge.create(15000).getName());
        assertEquals("별", Badge.create(6000).getName());
        assertEquals("없음", Badge.create(1000).getName());
        assertEquals("없음", Badge.create(0).getName());
    }
}
