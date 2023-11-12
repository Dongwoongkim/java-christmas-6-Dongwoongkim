package christmas.view;

public class OutputView {

    private static final String PREVIEW_EVENT_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_AMOUNT_HEADER = "<할인 전 총주문 금액>";
    private static final String SERVICE_MENU_HEADER = "<증정 메뉴>";
    private static final String TOTAL_BENEFIT_HEADER = "<총혜택 금액>";
    private static final String BENEFIT_HEADER = "<혜택 내역>";
    private static final String AFTER_DISCOUNT_AMOUNT_HEADER = "<할인 후 예상 결제 금액>";
    private static final String DECEMBER_EVENT_BADGE_HEADER = "<12월 이벤트 배지>";
    private static final String NONE = "없음";


    public void printPreviewEvent() {
        printMessage(PREVIEW_EVENT_MESSAGE);
        printLine();
    }

    public void printOrderHeader() {
        printMessage(ORDER_MENU_HEADER);
    }

    public void printOrderMenu(final String foodName, final Integer quantity) {
        printMessage(String.format("%s %d개", foodName, quantity));
    }

    public void printBeforeDiscountAmount(final Integer price) {
        printMessage(BEFORE_DISCOUNT_AMOUNT_HEADER);
        printMessage(String.format("%,d원", price));
        printLine();
    }

    public void printServiceMenu(final String name) {
        printMessage(SERVICE_MENU_HEADER);
        printMessage(name);
        printLine();
    }

    public void printBenefitHeader() {
        printMessage(BENEFIT_HEADER);
    }

    public void printBenefit(final String discountPolicy, final Integer discountAmount) {
        printMessage(String.format("%s : -%,d원", discountPolicy, discountAmount));
    }

    public void printTotalBenefit(final Integer discountAmount) {
        printMessage(TOTAL_BENEFIT_HEADER);

        if (discountAmount == 0) {
            printMessage("0원");
            printLine();
            return;
        }

        printMessage(String.format("-%,d원", discountAmount));
        printLine();
    }

    public void printAfterDiscountAmount(final Integer payAmount) {
        printMessage(AFTER_DISCOUNT_AMOUNT_HEADER);
        printMessage(String.format("%,d원", payAmount));
        printLine();
    }

    public void printBadge(final String badgeName) {
        printMessage(DECEMBER_EVENT_BADGE_HEADER);
        printMessage(badgeName);
    }

    public void printMessage(final String message) {
        System.out.println(message);
    }

    public void printLine() {
        System.out.println();
    }

    public void printNone() {
        System.out.println(NONE);
    }
}
