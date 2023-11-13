package christmas.controller;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.DiscountAmount;
import christmas.model.DiscountPolicy;
import christmas.model.Gift;
import christmas.model.Order;
import christmas.model.vo.Food;
import christmas.model.vo.Quantity;
import christmas.model.vo.VisitDay;
import christmas.util.InputConverter;
import christmas.util.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
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
        Discount discount = initDiscount(order, visitDay, gift);

        showReceipt(visitDay, order, discount, gift);

        Badge badge = initBadge(discount);
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
                String orderMenu = inputView.inputMenu();
                Map<Food, Quantity> foodAndQuantity = InputConverter.stringToMap(orderMenu);
                return Order.create(foodAndQuantity);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private Gift initGift(final Order order) {
        return Gift.create(order.getAmount());
    }

    private Discount initDiscount(final Order order,
                                  final VisitDay visitDay, final Gift gift) {
        return Discount.create(order.getAmount(), order.getMainQuantity(),
                order.getDessertQuantity(), visitDay, gift.isReceived());
    }

    private Badge initBadge(final Discount discount) {
        return Badge.create(discount.getSumOfDiscount());
    }

    private void showReceipt(final VisitDay visitDay, final Order order, final Discount discount, final Gift gift) {
        outputView.printPreviewEvent(visitDay.day());

        showOrders(order.getFoodAndQuantity());

        outputView.printBeforeDiscountAmount(order.getAmount());
        outputView.printServiceMenu(gift.getName());

        showDiscountDetails(discount.getDiscountDetails());

        outputView.printTotalBenefit(discount.getSumOfDiscount());
        outputView.printAfterDiscountAmount(order.getAmount() -
                discount.getSumOfDiscount() + discount.getGiftDiscount());
    }

    private void showDiscountDetails(final Map<DiscountPolicy, DiscountAmount> discountDetails) {
        outputView.printBenefitHeader();
        if (discountDetails.isEmpty()) {
            outputView.printNone();
            outputView.printLine();
            return;
        }
        discountDetails.forEach(
                (policy, amount) -> outputView.printBenefit(policy.getPolicy(), amount.getAmount()));
        outputView.printLine();
    }

    private void showOrders(final Map<Food, Quantity> foodAndQuantity) {
        outputView.printOrderHeader();
        foodAndQuantity.forEach(
                (food, quantity) -> outputView.printOrderMenu(food.name(), quantity.amount()));
        outputView.printLine();
    }

    private void showBadge(final Badge badge) {
        outputView.printBadge(badge.getName());
    }
}
