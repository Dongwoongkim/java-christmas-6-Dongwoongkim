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
        outputView.printPreviewEvent();
        outputView.printBeforeDiscount(orderMenu.sumAmountOfOrder());

    }

    private Date initDay() {
        while (true) {
            try {
                Integer day = inputView.inputDay();
                return new Date(day);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private OrderMenu initOrderMenu() {
        while (true) {
            try {
                String menu = inputView.inputMenu();
                Map<String, Integer> order = Converter.stringToMap(menu);
                return OrderMenu.createOrderMenu(order);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }
}
