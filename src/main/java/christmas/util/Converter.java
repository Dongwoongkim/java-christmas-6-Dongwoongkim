package christmas.util;

import christmas.exception.AlreadyExistsInOrderException;
import christmas.exception.InvalidOrderFormatException;
import christmas.exception.OrderMenuCountNonNumericException;
import christmas.vo.Food;
import christmas.vo.Quantity;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {

    private static final String MENU_DELIMITER = ",";
    private static final String QUANTITY_DELIMITER = "-";

    private Converter() {
    }

    public static Map<Food, Quantity> stringToMap(String menu) {
        try {
            return splitAndMapping(menu);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidOrderFormatException();
        }
    }

    private static Map<Food, Quantity> splitAndMapping(String menu) {
        Map<Food, Quantity> foodMap = Arrays.stream(menu.split(MENU_DELIMITER))
                .map(item -> item.split(QUANTITY_DELIMITER))
                .collect(Collectors.toMap(
                        eachFoodAndQuantity -> getFood(eachFoodAndQuantity),
                        eachFoodAndQuantity -> getFoodCount(eachFoodAndQuantity),
                        (existing, replacement) -> {
                            throw new AlreadyExistsInOrderException();
                        }
                ));

        return foodMap;
    }

    private static Food getFood(String[] parts) {
        return new Food(parts[0].trim());
    }

    private static Quantity getFoodCount(String[] parts) {
        try {
            return new Quantity(Integer.parseInt(parts[1]));
        } catch (NumberFormatException e) {
            throw new OrderMenuCountNonNumericException();
        }
    }
}
