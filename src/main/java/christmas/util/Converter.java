package christmas.util;

import christmas.exception.AlreadyExistsInOrderException;
import christmas.exception.InvalidOrderFormatException;
import christmas.exception.OrderMenuCountNonNumericException;
import christmas.vo.Food;
import christmas.vo.Quantity;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {

    private static final String MENU_DELIMITER = ",";
    private static final String QUANTITY_DELIMITER = "-";

    private Converter() {
    }

    public static Map<Food, Quantity> stringToMap(final String menu) {
        try {
            Map<Food, Quantity> foodAndQuantity = splitAndMapping(menu);
            return Collections.unmodifiableMap(foodAndQuantity);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidOrderFormatException();
        }
    }

    private static Map<Food, Quantity> splitAndMapping(final String menu) {
        Map<Food, Quantity> foodMap = Arrays.stream(menu.split(MENU_DELIMITER))
                .map(item -> item.split(QUANTITY_DELIMITER))
                .collect(Collectors.toMap(
                        eachFoodAndQuantity -> getFood(eachFoodAndQuantity),
                        eachFoodAndQuantity -> getQuantity(eachFoodAndQuantity),
                        (existing, replacement) -> {
                            throw new AlreadyExistsInOrderException();
                        }
                ));

        return foodMap;
    }

    private static Food getFood(final String[] parts) {
        return new Food(parts[0].trim());
    }

    private static Quantity getQuantity(final String[] parts) {
        try {
            return Quantity.create(Integer.parseInt(parts[1]));
        } catch (NumberFormatException e) {
            throw new OrderMenuCountNonNumericException();
        }
    }
}
