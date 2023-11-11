package christmas.view;

import christmas.model.Gift;
import christmas.vo.Badge;
import christmas.vo.Food;
import christmas.vo.Quantity;
import java.util.Map;

public class OutputView {

    public void printPreviewEvent() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public void printBeforeDiscount(Integer price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", price));
        System.out.println();
    }

    public void printServiceMenu(Gift gift) {
        System.out.println("<증정 메뉴>");
        System.out.println(gift.getName());
        System.out.println();
    }

    public void printBenefitHeader() {
        System.out.println("<혜택 내역>");
    }

    public void printBenefit(String discountPolicy, Integer discountAmount) {
        System.out.println(String.format("%s : -%,d원", discountPolicy, discountAmount));
    }

    public void printTotalBenefit(Integer discountAmount) {
        System.out.println("<총혜택 금액>");
        System.out.println(String.format("-%,d원", discountAmount));
        System.out.println();
    }

    public void printPayMoneyAfterDiscount(Integer payAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", payAmount));
        System.out.println();
    }

    public void printBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.name());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printOrderMenu(Map<Food, Quantity> foodAndQuantity) {
        System.out.println("<주문 메뉴>");
        for (Food food : foodAndQuantity.keySet()) {
            System.out.println(food.getName() + " " + foodAndQuantity.get(food).getQuantity() + "개");
        }
        System.out.println();
    }

    public void printLine() {
        System.out.println();
    }
}
