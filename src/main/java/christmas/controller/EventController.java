package christmas.controller;

import christmas.dto.DiscountDto;
import christmas.dto.OrderDto;
import christmas.model.Discount;
import christmas.model.Gift;
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
        Gift gift = Gift.createGift(order.sumAmountOfOrder());

        Integer weekDayDiscountMoney = order.getWeekDayDiscountMoney(visitDay);
        Integer weekendDayDiscountMoney = order.getWeekendDayDiscountMoney(visitDay);
        Discount discount = Discount.createDiscount(weekDayDiscountMoney, weekendDayDiscountMoney, visitDay, gift);

        OrderDto orderDto = OrderDto.create(order);
        DiscountDto discountDto = DiscountDto.create(discount);
        showReceipt(order, orderDto, discount, discountDto, gift);
    }

    private void showReceipt(Order order, OrderDto orderDto, Discount discount, DiscountDto discountDto, Gift gift) {
        outputView.printPreviewEvent();
        outputView.printOrderMenu(orderDto);
        outputView.printBeforeDiscount(order.sumAmountOfOrder());
        outputView.printServiceMenu(gift);
        outputView.printBenefit(discountDto);
        outputView.printTotalBenefit(discount.getSumOfDiscount());
        outputView.printPayMoneyAfterDiscount(
                order.sumAmountOfOrder() - discount.getSumOfDiscount() + discountDto.getGiftDiscount());
//        outputView.printBadge();
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
