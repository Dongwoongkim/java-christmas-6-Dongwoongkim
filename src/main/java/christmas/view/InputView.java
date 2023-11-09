package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String day = Console.readLine();
        return day;
    }

    public String inputMenu() {
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String menu = Console.readLine();
        return menu;
    }
}
