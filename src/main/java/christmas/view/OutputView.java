package christmas.view;

import christmas.model.Discount;

public class OutputView {

    public void printMenu() {
        System.out.println("<주문 메뉴>");
    }

    public void printPreviewEvent() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public void printBeforeDiscount(Integer price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", price));
        System.out.println();
    }

    public void printServiceMenu(String giftName) {
        System.out.println("<증정 메뉴>");
        System.out.println(giftName);
        System.out.println();
    }

    public void printBenefit(Discount discount) {
        System.out.println("<혜택 내역>");
        System.out.println(String.format("평일 할인 : -%,d원", discount.getDiscountInfo().getOrDefault("평일 할인", 0)));
        System.out.println(String.format("주말 할인 : -%,d원", discount.getDiscountInfo().getOrDefault("주말 할인", 0)));
        System.out.println();
    }

    public void printTotalBenefit() {
        System.out.println("<총혜택 금액>");
        System.out.println();
    }

    public void printPayMoneyAfterDiscount() {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println();
    }

    public void printBadge() {
        System.out.println("<12월 이벤트 배지>");
        System.out.println();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
