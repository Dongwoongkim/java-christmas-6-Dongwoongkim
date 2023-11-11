package christmas.util;

import christmas.exception.AlreadyExistsInOrderException;
import christmas.exception.InvalidOrderFormatException;
import christmas.exception.OrderMenuCountNonNumericException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {

    private static final String MENU_DELIMITER = ",";
    private static final String QUANTITY_DELIMITER = "-";

    private Converter() {
    }

    public static Map<String, Integer> stringToMap(String menu) {
        try {
            return splitAndMapping(menu);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidOrderFormatException();
        }
    }

    private static Map<String, Integer> splitAndMapping(String menu) {
        List<String> foodsAndQuantity = stringToListByDelimiter(menu);

        return foodsAndQuantity.stream()
                .map(food -> food.split(QUANTITY_DELIMITER))
                .collect(Collectors.toMap(
                        eachMenuAndCount -> getFood(eachMenuAndCount),
                        eachMenuAndCount -> getFoodCount(eachMenuAndCount),
                        (existing, replacement) -> {
                            throw new AlreadyExistsInOrderException();
                        }
                ));
    }

    private static List<String> stringToListByDelimiter(String menu) {
        return Arrays.stream(menu.split(MENU_DELIMITER)).toList();
    }

    private static String getFood(String[] parts) {
        return parts[0].trim();
    }

    private static int getFoodCount(String[] parts) {
        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new OrderMenuCountNonNumericException();
        }
    }
}
