package christmas;

import christmas.controller.OrderController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OrderController orderController = new OrderController(new InputView(), new OutputView());
        orderController.run();
    }
}
