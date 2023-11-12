package christmas.vo;

import static christmas.model.Menu.DRINK;

import christmas.exception.OrderNotInMenuException;
import christmas.model.Menu;
import java.util.Arrays;

public class Food {

    private final String name;

    private Food(final String name) {
        validate(name);
        this.name = name;
    }

    public static Food create(final String name) {
        return new Food(name);
    }

    private void validate(final String name) {
        if (!isContainInMenu(name)) {
            throw new OrderNotInMenuException();
        }
    }

    private boolean isContainInMenu(final String name) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.getSalesMenu().containsKey(name));
    }

    public boolean isDrink() {
        return DRINK.getSalesMenu()
                .containsKey(name);
    }

    public String getName() {
        return name;
    }
}
