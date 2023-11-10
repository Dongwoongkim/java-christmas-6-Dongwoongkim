package christmas.controller;

import christmas.model.OrderMenu;
import christmas.util.Converter;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.Date;
import java.util.Map;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Date date = initDay();
        OrderMenu orderMenu = initOrderMenu();
    }

    private Date initDay() {
        Integer day = inputView.inputDay();
        return new Date(day);
    }

    private OrderMenu initOrderMenu() {
        String menu = inputView.inputMenu();
        Map<String, Integer> order = Converter.stringToMap(menu);
        return OrderMenu.createOrderMenu(order);
    }
}
