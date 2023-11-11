package christmas.controller;

import christmas.model.Discount;
import christmas.model.OrderMenu;
import christmas.util.Converter;
import christmas.validation.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.VisitDay;
import java.util.Map;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        VisitDay visitDay = initDay();
        OrderMenu orderMenu = initOrderMenu();
        boolean getGift = orderMenu.isGetGift();
        Integer weekDayDiscount = orderMenu.getWeekDayDiscount(visitDay);
        Integer weekendDayDiscount = orderMenu.getWeekendDayDiscount(visitDay);
        Discount discount = Discount.createDiscount(weekDayDiscount, weekendDayDiscount, visitDay, getGift);

        showReceipt(orderMenu, discount, getGift);
    }

    private void showReceipt(OrderMenu orderMenu, Discount discount, boolean getGift) {
        outputView.printPreviewEvent();
        outputView.printOrderMenu(orderMenu);
        outputView.printBeforeDiscount(orderMenu.sumAmountOfOrder());
        outputView.printServiceMenu(getGift);
        outputView.printBenefit(discount);
        outputView.printTotalBenefit(discount);
        outputView.printPayMoneyAfterDiscount(
                orderMenu.sumAmountOfOrder() - discount.getSumOfDiscount() + discount.getGiftDiscount());
        outputView.printBadge(discount.getBadge());
    }

    private VisitDay initDay() {
        while (true) {
            try {
                String day = inputView.inputDay();
                InputValidator.validateDay(day);
                return new VisitDay(Integer.valueOf(day));
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
