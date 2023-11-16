package christmas.view;

public class OutputView {

    public void printWelcomeMessage() {
        printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printPreviewEvent(final Integer day) {
        printMessage(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", day));
        printLine();
    }

    public void printOrderHeader() {
        printMessage("<주문 메뉴>");
    }

    public void printOrderMenu(final String foodName, final Integer quantity) {
        printMessage(String.format("%s %d개", foodName, quantity));
    }

    public void printBeforeDiscountAmount(final Integer price) {
        printMessage("<할인 전 총주문 금액>");
        printMessage(String.format("%,d원", price));
        printLine();
    }

    public void printServiceMenu(final String name) {
        printMessage("<증정 메뉴>");
        printMessage(name);
        printLine();
    }

    public void printBenefitHeader() {
        printMessage("<혜택 내역>");
    }

    public void printBenefit(final String discountPolicy, final Integer discountAmount) {
        printMessage(String.format("%s : -%,d원", discountPolicy, discountAmount));
    }

    public void printTotalBenefit(final Integer discountAmount) {
        printMessage("<총혜택 금액>");

        if (discountAmount == 0) {
            printMessage("0원");
            printLine();
            return;
        }

        printMessage(String.format("-%,d원", discountAmount));
        printLine();
    }

    public void printAfterDiscountAmount(final Integer payAmount) {
        printMessage("<할인 후 예상 결제 금액>");
        printMessage(String.format("%,d원", payAmount));
        printLine();
    }

    public void printBadge(final String badgeName) {
        printMessage("<12월 이벤트 배지>");
        printMessage(badgeName);
    }

    public void printMessage(final String message) {
        System.out.println(message);
    }

    public void printLine() {
        System.out.println();
    }

    public void printNone() {
        System.out.println("없음");
    }
}
