package christmas.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {

    private Converter() {
    }

    public static Map<String, Integer> stringToMap(String menu) {
        try {
            return splitAndMapping(menu);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static Map<String, Integer> splitAndMapping(String menu) {
        Map<String, Integer> foodMap = Arrays.stream(menu.split(","))
                .map(item -> item.split("-"))
                .collect(Collectors.toMap(
                        parts -> parts[0].trim(),
                        parts -> getFoodCount(parts),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                        }
                ));
        return foodMap;
    }

    private static int getFoodCount(String[] parts) {
        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
