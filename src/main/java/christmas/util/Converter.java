package christmas.util;

import christmas.exception.AlreadyExistsInOrderException;
import christmas.exception.InvalidOrderFormatException;
import christmas.exception.OrderMenuCountNonNumericException;
import christmas.model.vo.Food;
import christmas.model.vo.Quantity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {

    private static final String MENU_DELIMITER = ",";
    private static final String QUANTITY_DELIMITER = "-";
    private static final Integer FOOD_INDEX = 0;
    private static final Integer QUANTITY_INDEX = 1;

    private Converter() {
    }

    public static Map<Food, Quantity> stringToMap(final String menu) {
        try {
            List<String> menuItems = stringToList(menu);
            Map<Food, Quantity> foodAndQuantity = splitAndMapping(menuItems);
            return Collections.unmodifiableMap(foodAndQuantity);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidOrderFormatException();
        }
    }

    private static List<String> stringToList(final String menu) {
        return Arrays.asList(menu.split(MENU_DELIMITER));
    }

    private static Map<Food, Quantity> splitAndMapping(final List<String> menuItems) {
        return menuItems.stream()
                .map(item -> item.split(QUANTITY_DELIMITER))
                .collect(Collectors.toMap(
                        foodAndQuantity -> getFood(Arrays.asList(foodAndQuantity)),
                        foodAndQuantity -> getQuantity(Arrays.asList(foodAndQuantity)),
                        (existing, replacement) -> {
                            throw new AlreadyExistsInOrderException();
                        }
                ));
    }

    private static Food getFood(final List<String> foodAndQuantity) {
        return Food.create(foodAndQuantity.get(FOOD_INDEX).trim());
    }

    private static Quantity getQuantity(final List<String> foodAndQuantity) {
        try {
            String quantity = foodAndQuantity.get(QUANTITY_INDEX);
            return Quantity.create(Integer.valueOf(quantity));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new OrderMenuCountNonNumericException();
        }
    }
}
