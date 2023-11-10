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

        // 증정메뉴
        outputView.printServiceMenu(orderMenu.getGift());

        // 혜택내역

        outputView.printBenefit();

        // 총혜택 금액
        outputView.printTotalBenefit();

        // 할인 후 예상 결제 금액
        outputView.printPayMoneyAfterDiscount();

        // 12월 이벤트 배지
        outputView.printBadge();

    }

    private void showReceipt(OrderMenu orderMenu, Date date) {
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
