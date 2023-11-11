package christmas.dto;

import christmas.model.Order;
import christmas.vo.Food;
import christmas.vo.Quantity;
import java.util.Map;

public class OrderDto {

    private final Map<Food, Quantity> order;

    private OrderDto(Map<Food, Quantity> order) {
        this.order = order;
    }

    public static OrderDto create(Order order) {
        return new OrderDto(order.getOrder());
    }

    public Map<Food, Quantity> getOrder() {
        return order;
    }
}
