package christmas.model.vo;

import static christmas.model.Menu.DRINK;

import christmas.exception.OrderNotInMenuException;
import christmas.model.Menu;
import java.util.Arrays;
import java.util.Objects;

public class Food {

    private final String name;

    private Food(String name) {
        validate(name);
        this.name = name;
    }

    public static Food create(String name) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Food) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
