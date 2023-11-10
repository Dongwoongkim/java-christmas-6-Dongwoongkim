package christmas.model;

import static christmas.model.EventInfo.GIFT_REQUIREMENT_AMOUNT;
import static christmas.model.EventInfo.MAX_ORDER_QUANTITY;
import static christmas.model.EventInfo.PRESENT_YEAR;
import static christmas.model.Menu.MAIN;

import christmas.exception.OnlyDrinkOrderException;
import christmas.exception.OverMaxQuantityOrderException;
import christmas.exception.ZeroQuantityOrderException;
import christmas.vo.Date;
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
            throw new ZeroQuantityOrderException();
        }
        if (isOverMaxQuantity(order)) {
            throw new OverMaxQuantityOrderException();
        }
        if (!isContainMenu(order)) {
            throw new IllegalArgumentException();
        }
        if (isContainOnlyDrink(order)) {
            throw new OnlyDrinkOrderException();
        }
    }

    private boolean isContainOnlyDrink(Map<String, Integer> order) {
        return order.keySet()
                .stream()
                .allMatch(this::isDrink);
    }

    private boolean isDrink(String food) {
        for (Menu menu : Menu.values()) {
            if (menu.getSalesMenu().containsKey(food)) {
                return menu == Menu.DRINK;
            }
        }
        return false;
    }

    private boolean isContainMenu(Map<String, Integer> order) {
        return order.keySet()
                .stream()
                .allMatch(food -> Arrays.stream(Menu.values())
                        .anyMatch(menu -> menu.getSalesMenu().containsKey(food)));
    }

    private boolean isOverMaxQuantity(Map<String, Integer> order) {
        int totalQuantity = order.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity > MAX_ORDER_QUANTITY.getValue()) {
            return true;
        }
        return false;
    }

    private boolean isContainZeroQuantity(Map<String, Integer> order) {
        return order.values()
                .stream()
                .anyMatch(quantity -> quantity == 0);
    }

    public Integer sumAmountOfOrder() {
        return order.keySet().stream()
                .mapToInt(food -> Menu.getPriceOfFood(food) * order.get(food))
                .sum();
    }

    public boolean isGetGift() {
        if (sumAmountOfOrder() >= GIFT_REQUIREMENT_AMOUNT.getValue()) {
            return true;
        }
        return false;
    }

    public Integer getWeekDayDiscount(Date date) {
        Integer day = date.getDay();
        if (isWeekDay(day)) {
            int dessertCount = order.entrySet().stream()
                    .filter(entry -> Menu.DESSERT.getSalesMenu().containsKey(entry.getKey()))
                    .mapToInt(Map.Entry::getValue)
                    .sum();
            return dessertCount * PRESENT_YEAR.getValue();
        }
        return 0;
    }

    public Integer getWeekendDayDiscount(Date date) {
        Integer day = date.getDay();
        if (!isWeekDay(day)) {
            int mainCount = order.entrySet().stream()
                    .filter(entry -> MAIN.getSalesMenu().containsKey(entry.getKey()))
                    .mapToInt(Map.Entry::getValue)
                    .sum();
            return mainCount * PRESENT_YEAR.getValue();
        }
        return 0;
    }

    private boolean isWeekDay(Integer day) {
        return (day >= 3 && day <= 8) || (day >= 10 && day <= 15) || (day >= 17 && day <= 22) || (day >= 24
                && day <= 29) || day == 31;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }
}
