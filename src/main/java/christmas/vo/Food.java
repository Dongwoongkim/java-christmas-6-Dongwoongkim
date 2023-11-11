package christmas.vo;

import static christmas.model.Menu.DRINK;

import christmas.exception.OrderNotInMenuException;
import christmas.model.Menu;
import java.util.Arrays;

public class Food {

    private final String name;

    public Food(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!isContainInMenu(name)) {
            throw new OrderNotInMenuException();
        }
    }

    private boolean isContainInMenu(String name) {
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
