package christmas.model;

import static christmas.model.Menu.DESSERT;
import static christmas.model.Menu.MAIN;

import christmas.exception.OnlyDrinkOrderException;
import christmas.exception.OverMaxQuantityOrderException;
import christmas.model.vo.Food;
import christmas.model.vo.Quantity;
import java.util.Collections;
import java.util.Map;

public class Order {

    private static final Integer MAX_ORDER_QUANTITY = 20;

    private final Map<Food, Quantity> foodAndQuantity;

    private Order(final Map<Food, Quantity> foodAndQuantity) {
        validate(foodAndQuantity);
        this.foodAndQuantity = foodAndQuantity;
    }

    public static Order create(final Map<Food, Quantity> foodAndQuantity) {
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
        int totalQuantity = getTotalQuantity(foodAndQuantity);

        if (totalQuantity > MAX_ORDER_QUANTITY) {
            return true;
        }
        return false;
    }

    private int getTotalQuantity(final Map<Food, Quantity> foodAndQuantity) {
        return foodAndQuantity.values()
                .stream()
                .mapToInt(Quantity::amount)
                .sum();
    }

    public Integer getAmount() {
        return foodAndQuantity.keySet().stream()
                .mapToInt(food -> Menu.getPriceOfFood(food) * foodAndQuantity.get(food).amount())
                .sum();
    }

    public Integer getDessertQuantity() {
        return foodAndQuantity.entrySet()
                .stream()
                .filter(order -> DESSERT.getSalesMenu().containsKey(order.getKey().name()))
                .mapToInt(order -> order.getValue().amount())
                .sum();
    }

    public Integer getMainQuantity() {
        return foodAndQuantity.entrySet()
                .stream()
                .filter(order -> MAIN.getSalesMenu().containsKey(order.getKey().name()))
                .mapToInt(order -> order.getValue().amount())
                .sum();
    }

    public Map<Food, Quantity> getFoodAndQuantity() {
        return Collections.unmodifiableMap(foodAndQuantity);
    }
}
