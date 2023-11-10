package christmas.view;

public class OutputView {

    public void printMenu() {
        System.out.println("<주문 메뉴>");
    }

    public void printBeforeDiscount() {
        System.out.println("<할인 전 총주문 금액>");
    }

    public void printServiceMenu() {
        System.out.println("<증정 메뉴>");
    }

    public void printBenefit() {
        System.out.println("<혜택 내역>");
    }

    public void printTotalBenefit() {
        System.out.println("<총혜택 금액>");
    }

    public void printPayMoneyAfterDiscount() {
        System.out.println("<할인 후 예상 결제 금액>");
    }

    public void printBadge() {
        System.out.println("<12월 이벤트 배지>");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
