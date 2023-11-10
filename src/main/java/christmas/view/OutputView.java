package christmas.view;

import christmas.model.Discount;
import christmas.model.Menu;
import christmas.model.OrderMenu;
import java.util.Map;

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

    public void printServiceMenu(boolean isGetGift) {
        System.out.println("<증정 메뉴>");
        if (isGetGift) {
            System.out.println(Menu.getChampagne() + " 1개");
        }
        System.out.println();
    }

    public void printBenefit(Discount discount) {
        System.out.println("<혜택 내역>");

        if (discount.getDayDiscount() != 0) {
            System.out.println(String.format("크리스마스 디데이 할인 : -%,d원", discount.getDayDiscount()));
        }
        if (discount.getWeekdayDiscount() != 0) {
            System.out.println(String.format("평일 할인 : -%,d원", discount.getWeekdayDiscount()));
        }
        if (discount.getWeekendDayDiscount() != 0) {
            System.out.println(String.format("주말 할인 : -%,d원", discount.getWeekendDayDiscount()));
        }
        if (discount.getSpecialDayDiscount() != 0) {
            System.out.println(String.format("특별 할인 : -%,d원", discount.getSpecialDayDiscount()));
        }
        if (discount.getGiftDiscount() != 0) {
            System.out.println(String.format("증정 이벤트 : -%,d원", discount.getGiftDiscount()));
        }
        if (discount.getDiscountInfo().isEmpty()) {
            System.out.println("없음");
        }

        System.out.println();
    }

    public void printTotalBenefit(Discount discount) {
        System.out.println("<총혜택 금액>");
        System.out.println(String.format("-%,d원", discount.getSumOfDiscount()));
        System.out.println();
    }

    public void printPayMoneyAfterDiscount(Integer payAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", payAmount));
        System.out.println();
    }

    public void printBadge(Discount discount) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(discount.getBadge());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printOrderMenu(OrderMenu orderMenu) {
        System.out.println("<주문 메뉴>");
        Map<String, Integer> order = orderMenu.getOrder();
        for (String food : order.keySet()) {
            System.out.println(food + " " + order.get(food) + "개");
        }
        System.out.println();
    }
}
