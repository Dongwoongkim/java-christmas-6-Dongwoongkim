package christmas.view;

import christmas.model.DiscountPolicy;
import christmas.vo.Badge;
import christmas.vo.DiscountAmount;
import christmas.vo.Food;
import christmas.vo.Quantity;

public class OutputView {

    private static final String PREVIEW_EVENT_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_HEADER = "<할인 전 총주문 금액>";
    private static final String SERVICE_MENU_HEADER = "<증정 메뉴>";
    private static final String TOTAL_BENEFIT_HEADER = "<총혜택 금액>";
    private static final String BENEFIT_HEADER = "<혜택 내역>";
    private static final String FINAL_PAYMENT_AFTER_DISCOUNT_HEADER = "<할인 후 예상 결제 금액>";
    private static final String DECEMBER_EVENT_BADGE_HEADER = "<12월 이벤트 배지>";
    private static final String NONE = "없음";


    public void printPreviewEvent() {
        printMessage(PREVIEW_EVENT_MESSAGE);
        printLine();
    }

    public void printOrderHeader() {
        printMessage(ORDER_MENU_HEADER);
    }

    public void printOrderMenu(Food food, Quantity quantity) {
        printMessage(food.getName() + " " + quantity.getQuantity() + "개");
    }

    public void printBeforeDiscount(Integer price) {
        printMessage(BEFORE_DISCOUNT_HEADER);
        printMessage(String.format("%,d원", price));
        printLine();
    }

    public void printServiceMenu(String name) {
        printMessage(SERVICE_MENU_HEADER);
        printMessage(name);
        printLine();
    }

    public void printBenefitHeader() {
        printMessage(BENEFIT_HEADER);
    }

    public void printBenefit(DiscountPolicy discountPolicy, DiscountAmount discountAmount) {
        printMessage(String.format("%s : -%,d원", discountPolicy.getPolicy(), discountAmount.getAmount()));
    }

    public void printTotalBenefit(Integer discountAmount) {
        printMessage(TOTAL_BENEFIT_HEADER);
        printMessage(String.format("-%,d원", discountAmount));
        printLine();
    }

    public void printPayMoneyAfterDiscount(Integer payAmount) {
        printMessage(FINAL_PAYMENT_AFTER_DISCOUNT_HEADER);
        printMessage(String.format("%,d원", payAmount));
        printLine();
    }

    public void printBadge(Badge badge) {
        printMessage(DECEMBER_EVENT_BADGE_HEADER);
        printMessage(badge.getName());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printLine() {
        System.out.println();
    }

    public void printNone() {
        System.out.println(NONE);
    }
}
