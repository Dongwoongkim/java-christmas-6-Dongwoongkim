package christmas.controller;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.DiscountPolicy;
import christmas.model.Gift;
import christmas.model.Order;
import christmas.model.vo.DiscountAmount;
import christmas.model.vo.Food;
import christmas.model.vo.Quantity;
import christmas.model.vo.VisitDay;
import christmas.util.Converter;
import christmas.validation.InputValidator;
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

        Integer orderAmount = order.getAmount();
        Integer mainQuantity = order.getMainQuantity();
        Integer dessertQuantity = order.getDessertQuantity();

        Gift gift = initGift(orderAmount);
        Discount discount = initDiscount(orderAmount, mainQuantity, dessertQuantity, visitDay, gift.isReceived());
        showReceipt(order, discount, gift);

        Badge badge = initBadge(discount);
        showBadge(badge);
    }

    private VisitDay initVisitDay() {
        while (true) {
            try {
                String day = inputView.inputDay();
                InputValidator.validateDay(day);
                return VisitDay.create(Integer.valueOf(day));
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private Order initOrder() {
        while (true) {
            try {
                String orderMenu = inputView.inputMenu();
                Map<Food, Quantity> foodAndQuantity = Converter.stringToMap(orderMenu);
                return Order.create(foodAndQuantity);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private Gift initGift(final Integer orderAmount) {
        return Gift.create(orderAmount);
    }

    private Discount initDiscount(final Integer orderAmount, final Integer mainQuantity, final Integer dessertQuantity,
                                  final VisitDay visitDay, final boolean isGiftReceived) {
        return Discount.create(orderAmount, mainQuantity, dessertQuantity, visitDay, isGiftReceived);
    }

    private Badge initBadge(final Discount discount) {
        return Badge.create(discount.getSumOfDiscount());
    }

    private void showReceipt(final Order order, final Discount discount, final Gift gift) {
        outputView.printPreviewEvent();

        showOrders(order.getFoodAndQuantity());

        outputView.printBeforeDiscountAmount(order.getAmount());
        outputView.printServiceMenu(gift.getName());

        showDiscountDetails(discount.getDiscountInformation());

        outputView.printTotalBenefit(discount.getSumOfDiscount());
        outputView.printAfterDiscountAmount(order.getAmount() -
                discount.getSumOfDiscount() + discount.getGiftDiscount());
    }

    private void showDiscountDetails(final Map<DiscountPolicy, DiscountAmount> discountInformation) {
        outputView.printBenefitHeader();
        if (discountInformation.isEmpty()) {
            outputView.printNone();
            outputView.printLine();
            return;
        }
        discountInformation.forEach(
                (discountPolicy, amount) -> outputView.printBenefit(discountPolicy.getPolicy(), amount.getAmount()));
        outputView.printLine();
    }

    private void showOrders(final Map<Food, Quantity> foodAndQuantity) {
        outputView.printOrderHeader();
        foodAndQuantity.forEach(
                (food, quantity) -> outputView.printOrderMenu(food.name(), quantity.getQuantity()));
        outputView.printLine();
    }

    private void showBadge(final Badge badge) {
        outputView.printBadge(badge.getName());
    }
}
