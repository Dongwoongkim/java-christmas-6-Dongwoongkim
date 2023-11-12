package christmas.vo;

import static christmas.model.Menu.DRINK;

import christmas.exception.OrderNotInMenuException;
import christmas.model.Menu;
import java.util.Arrays;

public record Food(String name) {

    public Food {
        validate(name);
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
}
