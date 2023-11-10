package christmas.model;

import java.util.Arrays;
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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (isOverMaxQuantity(order)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (!isContainMenu(order)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (isContainOnlyDrink(order)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isContainOnlyDrink(Map<String, Integer> order) {
        return order.keySet()
                .stream()
                .allMatch(this::isDrink);
    }

    private boolean isDrink(String food) {
        for (Menu menu : Menu.values()) {
            if (menu.getFood().containsKey(food)) {
                return menu == Menu.DRINK;
            }
        }
        return false;
    }

    private boolean isContainMenu(Map<String, Integer> order) {
        return order.keySet()
                .stream()
                .allMatch(food -> Arrays.stream(Menu.values())
                        .anyMatch(menu -> menu.getFood().containsKey(food)));
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
