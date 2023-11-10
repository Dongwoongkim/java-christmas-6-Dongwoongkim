package christmas.controller;

import christmas.model.Discount;
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
        outputView.printOrderMenu(orderMenu);
        outputView.printBeforeDiscount(orderMenu.sumAmountOfOrder());

        boolean getGift = orderMenu.isGetGift();
        outputView.printServiceMenu(getGift);

        // 혜택내역
        Integer weekDayDiscount = orderMenu.getWeekDayDiscount(date);
        Integer weekendDayDiscount = orderMenu.getWeekendDayDiscount(date);
        Discount discount = Discount.createDiscount(weekDayDiscount, weekendDayDiscount, date, getGift);
        outputView.printBenefit(discount);

        // 총혜택 금액
        outputView.printTotalBenefit(discount);

        // 할인 후 예상 결제 금액
        outputView.printPayMoneyAfterDiscount(
                orderMenu.sumAmountOfOrder() - discount.getSumOfDiscount() + discount.getGiftDiscount());

        // 12월 이벤트 배지
        outputView.printBadge(discount);
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
