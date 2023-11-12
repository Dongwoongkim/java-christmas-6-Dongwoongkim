package christmas.controller;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.DiscountPolicy;
import christmas.model.Gift;
import christmas.model.Order;
import christmas.util.Converter;
import christmas.validation.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.DiscountAmount;
import christmas.vo.Food;
import christmas.vo.Quantity;
import christmas.vo.VisitDay;
import java.util.Map;

public class OrderController {

    private final InputView inputView;
    private final OutputView outputView;

    public OrderController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        VisitDay visitDay = initVisitDay();
        Order order = initOrder();
        Gift gift = initGift(order);

        Integer mainQuantity = order.getMainQuantity();
        Integer dessertQuantity = order.getDessertQuantity();

        Discount discount = initDiscount(mainQuantity, dessertQuantity, visitDay, gift.isReceived());
        Badge badge = initBadge(discount);

        showReceipt(order, discount, gift);
        showBadge(badge);
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

    private Gift initGift(final Order order) {
        return Gift.createGift(order.getAmount());
    }

    private Discount initDiscount(final Integer weekDayDiscountMoney, final Integer weekendDayDiscountMoney,
                                  final VisitDay visitDay, final boolean isGiftReceived) {
        return Discount.createDiscount(weekDayDiscountMoney, weekendDayDiscountMoney, visitDay, isGiftReceived);
    }

    private Badge initBadge(final Discount discount) {
        return Badge.createBadge(discount.getSumOfDiscount());
    }

    private void showReceipt(final Order order, final Discount discount, final Gift gift) {
        outputView.printPreviewEvent();

        showOrders(order.getFoodAndQuantity());

        outputView.printBeforeDiscount(order.getAmount());
        outputView.printServiceMenu(gift.getName());

        showDiscountDetails(discount.getDiscountInformation());

        outputView.printTotalBenefit(discount.getSumOfDiscount());
        outputView.printPayMoneyAfterDiscount(order.getAmount() -
                discount.getSumOfDiscount() + discount.getGiftDiscount());
    }

    private void showDiscountDetails(final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        outputView.printBenefitHeader();

        if (discountInformation.isEmpty()) {
            outputView.printNone();
            outputView.printLine();
            return;
        }

        discountInformation.forEach(outputView::printBenefit);
        outputView.printLine();
    }

    private void showOrders(final Map<Food, Quantity> foodAndQuantity) {
        outputView.printOrderHeader();
        foodAndQuantity.forEach(outputView::printOrderMenu);
        outputView.printLine();
    }

    private void showBadge(final Badge badge) {
        outputView.printBadge(badge.getName());
    }
}
