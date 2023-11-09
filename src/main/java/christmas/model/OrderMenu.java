package christmas.model;

import java.util.Map;

public class OrderMenu {

    private final Map<String, Integer> order;

    private OrderMenu(Map<String, Integer> order) {
        validateOrderMenu(order);
        this.order = order;
    }

    public static OrderMenu createOrderMenu(Map<String, Integer> order) {
        return new OrderMenu(order);
    }

    private void validateOrderMenu(Map<String, Integer> order) {
        if (isContainZeroQuantity(order)) {
            throw new IllegalArgumentException("메뉴의 수량에 0은 불가능 합니다.");
        }
        if (isOverMaxQuantity(order)) {
            throw new IllegalArgumentException("주문 수량의 총 합은 20 이하여야 합니다.");
        }
        // TODO : Menu에 있는 음식인지 검증
    }

    private boolean isOverMaxQuantity(Map<String, Integer> order) {
        int totalQuantity = order.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity >= 20) {
            return true;
        }
        return false;
    }

    private boolean isContainZeroQuantity(Map<String, Integer> order) {
        return order.values()
                .stream()
                .anyMatch(quantity -> quantity == 0);
    }
}
