package christmas.controller;

import christmas.model.Discount;
import christmas.model.DiscountPolicy;
import christmas.model.Gift;
import christmas.model.Order;
import christmas.util.Converter;
import christmas.validation.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.Badge;
import christmas.vo.DiscountAmount;
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
        VisitDay visitDay = initVisitDay();
        Order order = initOrder();
        Gift gift = initGift(order);

        Integer weekDayDiscountMoney = order.getWeekDayDiscountMoney(visitDay);
        Integer weekendDayDiscountMoney = order.getWeekendDayDiscountMoney(visitDay);
        Discount discount = initDiscount(weekDayDiscountMoney, weekendDayDiscountMoney, visitDay, gift.isExist());

        Badge badge = initBadge(discount);

        showReceipt(order, discount, gift);
        showBadge(badge);
    }

    private Badge initBadge(Discount discount) {
        return Badge.createBadge(discount.getSumOfDiscount());
    }

    private Gift initGift(Order order) {
        return Gift.createGift(order.getAmount());
    }

    private Discount initDiscount(Integer weekDayDiscountMoney, Integer weekendDayDiscountMoney,
                                  VisitDay visitDay, boolean isGiftGiven) {
        return Discount.createDiscount(weekDayDiscountMoney, weekendDayDiscountMoney, visitDay, isGiftGiven);
    }

    private VisitDay initVisitDay() {
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

    private Order initOrder() {
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

    private void showReceipt(Order order, Discount discount, Gift gift) {
        outputView.printPreviewEvent();

        showOrders(order.getFoodAndQuantity());

        outputView.printBeforeDiscount(order.getAmount());
        outputView.printServiceMenu(gift.getName());

        showDiscountDetails(discount.getDiscountInformation());

        outputView.printTotalBenefit(discount.getSumOfDiscount());
        outputView.printPayMoneyAfterDiscount(order.getAmount() -
                discount.getSumOfDiscount() + discount.getGiftDiscount());
    }

    private void showDiscountDetails(Map<DiscountPolicy, DiscountAmount> discountInformation) {
        outputView.printBenefitHeader();

        if (discountInformation.isEmpty()) {
            outputView.printNone();
            outputView.printLine();
            return;
        }

        discountInformation.forEach(outputView::printBenefit);
        outputView.printLine();
    }

    private void showOrders(Map<Food, Quantity> foodAndQuantity) {
        outputView.printOrderHeader();
        foodAndQuantity.forEach(outputView::printOrderMenu);
        outputView.printLine();
    }

    private void showBadge(Badge badge) {
        outputView.printBadge(badge.getName());
    }
}
