package christmas.model;

import static christmas.model.EventInfo.GIFT_REQUIREMENT_AMOUNT;
import static christmas.model.EventInfo.MAX_ORDER_QUANTITY;
import static christmas.model.EventInfo.PRESENT_YEAR;
import static christmas.model.Menu.DESSERT;
import static christmas.model.Menu.MAIN;

import christmas.exception.OnlyDrinkOrderException;
import christmas.exception.OverMaxQuantityOrderException;
import christmas.vo.Food;
import christmas.vo.Quantity;
import christmas.vo.VisitDay;
import java.util.Map;

public class Order {

    private final Map<Food, Quantity> foodAndQuantity;

    private Order(Map<Food, Quantity> foodAndQuantity) {
        validateOrderMenu(foodAndQuantity);
        this.foodAndQuantity = foodAndQuantity;
    }

    public static Order createOrderMenu(Map<Food, Quantity> foodAndQuantity) {
        return new Order(foodAndQuantity);
    }

    private void validateOrderMenu(Map<Food, Quantity> foodAndQuantity) {
        if (isOrderOverMaxQuantity(foodAndQuantity)) {
            throw new OverMaxQuantityOrderException();
        }
        if (isContainOnlyDrink(foodAndQuantity)) {
            throw new OnlyDrinkOrderException();
        }
    }

    private boolean isContainOnlyDrink(Map<Food, Quantity> foodAndQuantity) {
        return foodAndQuantity.keySet()
                .stream()
                .allMatch(Food::isDrink);
    }

    private boolean isOrderOverMaxQuantity(Map<Food, Quantity> foodAndQuantity) {
        int totalQuantity = foodAndQuantity.values()
                .stream()
                .mapToInt(Quantity::getQuantity).sum();

        if (totalQuantity > MAX_ORDER_QUANTITY.getValue()) {
            return true;
        }
        return false;
    }

    public Integer sumAmountOfOrder() {
        return foodAndQuantity.keySet().stream()
                .mapToInt(food -> Menu.getPriceOfFood(food) * foodAndQuantity.get(food).getQuantity())
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
            int dessertQuantity = foodAndQuantity.entrySet().stream()
                    .filter(entry -> DESSERT.getSalesMenu().containsKey(entry.getKey().getName()))
                    .mapToInt(entry -> entry.getValue().getQuantity())
                    .sum();
            return dessertQuantity * PRESENT_YEAR.getValue();
        }
        return 0;
    }

    public Integer getWeekendDayDiscountMoney(VisitDay visitDay) {
        if (!visitDay.isWeekDay()) {
            int mainCount = foodAndQuantity.entrySet().stream()
                    .filter(entry -> MAIN.getSalesMenu().containsKey(entry.getKey().getName()))
                    .mapToInt(entry -> entry.getValue().getQuantity())
                    .sum();
            return mainCount * PRESENT_YEAR.getValue();
        }
        return 0;
    }

    public Map<Food, Quantity> getFoodAndQuantity() {
        return foodAndQuantity;
    }
}
