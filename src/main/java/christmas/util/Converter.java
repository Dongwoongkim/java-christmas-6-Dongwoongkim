package christmas.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {

    private Converter() {
    }

    public static List<String> stringToStringListByDelimiter(String menu) {
        return Arrays.stream(menu.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Map<String, Integer> ListToMap(List<String> menu) {
        return menu.stream()
                .map(item -> item.split("-"))
                .collect(Collectors.toMap(
                        parts -> parts[0].trim(),
                        parts -> Integer.parseInt(parts[1].trim()),
                        (existing, replacement) -> {
                            throw new IllegalStateException("중복된 키가 발생했습니다: " + existing);
                        }
                ));
    }
}
