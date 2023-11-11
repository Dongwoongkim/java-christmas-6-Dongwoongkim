package christmas.dto;

import christmas.model.Order;
import christmas.vo.Food;
import christmas.vo.Quantity;
import java.util.Map;

public class OrderDto {

    private final Map<Food, Quantity> foodAndQuantity;

    private OrderDto(Map<Food, Quantity> foodAndQuantity) {
        this.foodAndQuantity = foodAndQuantity;
    }

    public static OrderDto create(Order order) {
        return new OrderDto(order.getFoodAndQuantity());
    }

    public Map<Food, Quantity> getFoodAndQuantity() {
        return foodAndQuantity;
    }
}
