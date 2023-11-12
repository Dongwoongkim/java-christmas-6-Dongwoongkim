package christmas.model;

import static christmas.model.EventInfo.MAX_ORDER_QUANTITY;
import static christmas.model.Menu.DESSERT;
import static christmas.model.Menu.MAIN;

import christmas.exception.OnlyDrinkOrderException;
import christmas.exception.OverMaxQuantityOrderException;
import christmas.vo.Food;
import christmas.vo.Quantity;
import java.util.Collections;
import java.util.Map;

public class Order {

    private final Map<Food, Quantity> foodAndQuantity;

    private Order(final Map<Food, Quantity> foodAndQuantity) {
        validate(foodAndQuantity);
        this.foodAndQuantity = foodAndQuantity;
    }

    public static Order createOrderMenu(final Map<Food, Quantity> foodAndQuantity) {
        return new Order(foodAndQuantity);
    }

    private void validate(final Map<Food, Quantity> foodAndQuantity) {
        if (isOrderOverMaxQuantity(foodAndQuantity)) {
            throw new OverMaxQuantityOrderException();
        }
        if (isContainOnlyDrink(foodAndQuantity)) {
            throw new OnlyDrinkOrderException();
        }
    }

    private boolean isContainOnlyDrink(final Map<Food, Quantity> foodAndQuantity) {
        return foodAndQuantity.keySet()
                .stream()
                .allMatch(Food::isDrink);
    }

    private boolean isOrderOverMaxQuantity(final Map<Food, Quantity> foodAndQuantity) {
        int totalQuantity = foodAndQuantity.values()
                .stream()
                .mapToInt(Quantity::getQuantity).sum();

        if (totalQuantity > MAX_ORDER_QUANTITY.getValue()) {
            return true;
        }
        return false;
    }

    public Integer getAmount() {
        return foodAndQuantity.keySet().stream()
                .mapToInt(food -> Menu.getPriceOfFood(food) * foodAndQuantity.get(food).getQuantity())
                .sum();
    }

    public Integer getDessertQuantity() {
        return foodAndQuantity.entrySet()
                .stream()
                .filter(order -> DESSERT.getSalesMenu().containsKey(order.getKey().getName()))
                .mapToInt(order -> order.getValue().getQuantity())
                .sum();
    }

    public Integer getMainQuantity() {
        return foodAndQuantity.entrySet()
                .stream()
                .filter(order -> MAIN.getSalesMenu().containsKey(order.getKey().getName()))
                .mapToInt(order -> order.getValue().getQuantity())
                .sum();
    }

    public Map<Food, Quantity> getFoodAndQuantity() {
        return Collections.unmodifiableMap(foodAndQuantity);
    }
}
