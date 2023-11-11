package christmas.controller;

import christmas.model.Discount;
import christmas.model.Order;
import christmas.util.Converter;
import christmas.validation.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.Food;
import christmas.vo.Quantity;
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
        Order order = initOrderMenu();
        boolean getGift = order.isGetGift();
        Integer weekDayDiscount = order.getWeekDayDiscount(visitDay);
        Integer weekendDayDiscount = order.getWeekendDayDiscount(visitDay);
        Discount discount = Discount.createDiscount(weekDayDiscount, weekendDayDiscount, visitDay, getGift);

        showReceipt(order, discount, getGift);
    }

    private void showReceipt(Order order, Discount discount, boolean getGift) {
        outputView.printPreviewEvent();
        outputView.printOrderMenu(order);
        outputView.printBeforeDiscount(order.sumAmountOfOrder());
        outputView.printServiceMenu(getGift);
        outputView.printBenefit(discount);
        outputView.printTotalBenefit(discount);
        outputView.printPayMoneyAfterDiscount(
                order.sumAmountOfOrder() - discount.getSumOfDiscount() + discount.getGiftDiscount());
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

    private Order initOrderMenu() {
        while (true) {
            try {
                String menu = inputView.inputMenu();
                Map<Food, Quantity> order = Converter.stringToMap(menu);
                return Order.createOrderMenu(order);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }
}
