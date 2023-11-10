package christmas.controller;

import christmas.model.Discount;
import christmas.model.OrderMenu;
import christmas.util.Converter;
import christmas.validation.InputValidator;
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
        outputView.printOrderMenu(orderMenu);
        outputView.printBeforeDiscount(orderMenu.sumAmountOfOrder());

        boolean getGift = orderMenu.isGetGift();
        outputView.printServiceMenu(getGift);

        Integer weekDayDiscount = orderMenu.getWeekDayDiscount(date);
        Integer weekendDayDiscount = orderMenu.getWeekendDayDiscount(date);
        Discount discount = Discount.createDiscount(weekDayDiscount, weekendDayDiscount, date, getGift);
        outputView.printBenefit(discount);

        outputView.printTotalBenefit(discount);

        outputView.printPayMoneyAfterDiscount(
                orderMenu.sumAmountOfOrder() - discount.getSumOfDiscount() + discount.getGiftDiscount());
        
        outputView.printBadge(discount.getBadge());
    }

    private Date initDay() {
        while (true) {
            try {
                String day = inputView.inputDay();
                InputValidator.validateDay(day);
                return new Date(Integer.valueOf(day));
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
