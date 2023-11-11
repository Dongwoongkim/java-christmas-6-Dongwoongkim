package christmas.model;

import static christmas.model.EventInfo.GIFT_REQUIREMENT_AMOUNT;
import static christmas.model.EventInfo.MAX_ORDER_QUANTITY;
import static christmas.model.EventInfo.PRESENT_YEAR;
import static christmas.model.Menu.DESSERT;
import static christmas.model.Menu.MAIN;

import christmas.exception.OnlyDrinkOrderException;
import christmas.exception.OrderNotInMenuException;
import christmas.exception.OverMaxQuantityOrderException;
import christmas.exception.ZeroQuantityOrderException;
import christmas.vo.Food;
import christmas.vo.Quantity;
import christmas.vo.VisitDay;
import java.util.Arrays;
import java.util.Map;

public class Order {

    private final Map<Food, Quantity> order;

    private Order(Map<Food, Quantity> order) {
        validateOrderMenu(order);
        this.order = order;
    }

    public static Order createOrderMenu(Map<Food, Quantity> order) {
        return new Order(order);
    }

    private void validateOrderMenu(Map<Food, Quantity> order) {
        if (isContainZeroQuantity(order)) {
            throw new ZeroQuantityOrderException();
        }
        if (isOverMaxQuantity(order)) {
            throw new OverMaxQuantityOrderException();
        }
        if (!isContainInMenu(order)) {
            throw new OrderNotInMenuException();
        }
        if (isContainOnlyDrink(order)) {
            throw new OnlyDrinkOrderException();
        }
    }

    private boolean isContainOnlyDrink(Map<Food, Quantity> order) {
        return order.keySet()
                .stream()
                .allMatch(this::isDrink);
    }

    private boolean isDrink(Food food) {
        for (Menu menu : Menu.values()) {
            if (menu.getSalesMenu().containsKey(food.getName())) {
                return menu == Menu.DRINK;
            }
        }
        return false;
    }

    private boolean isContainInMenu(Map<Food, Quantity> order) {
        return order.keySet()
                .stream()
                .allMatch(food -> Arrays.stream(Menu.values())
                        .anyMatch(menu -> menu.getSalesMenu().containsKey(food.getName())));
    }

    private boolean isOverMaxQuantity(Map<Food, Quantity> order) {
        int totalQuantity = order.values().stream().mapToInt(Quantity::getQuantity).sum();
        if (totalQuantity > MAX_ORDER_QUANTITY.getValue()) {
            return true;
        }
        return false;
    }

    private boolean isContainZeroQuantity(Map<Food, Quantity> order) {
        return order.values()
                .stream()
                .anyMatch(quantity -> quantity.getQuantity() == 0);
    }

    public Integer sumAmountOfOrder() {
        return order.keySet().stream()
                .mapToInt(food -> Menu.getPriceOfFood(food) * order.get(food).getQuantity())
                .sum();
    }

    public boolean isGetGift() {
        if (sumAmountOfOrder() >= GIFT_REQUIREMENT_AMOUNT.getValue()) {
            return true;
        }
        return false;
    }

    public Integer getWeekDayDiscountMoney(VisitDay visitDay) {
        if (visitDay.isWeekDay()) {
            int dessertCount = order.entrySet().stream()
                    .filter(entry -> DESSERT.getSalesMenu().containsKey(entry.getKey().getName()))
                    .mapToInt(entry -> entry.getValue().getQuantity())
                    .sum();
            return dessertCount * PRESENT_YEAR.getValue();
        }
        return 0;
    }

    public Integer getWeekendDayDiscountMoney(VisitDay visitDay) {
        if (!visitDay.isWeekDay()) {
            int mainCount = order.entrySet().stream()
                    .filter(entry -> MAIN.getSalesMenu().containsKey(entry.getKey().getName()))
                    .mapToInt(entry -> entry.getValue().getQuantity())
                    .sum();
            return mainCount * PRESENT_YEAR.getValue();
        }
        return 0;
    }

    public Map<Food, Quantity> getOrder() {
        return order;
    }
}
