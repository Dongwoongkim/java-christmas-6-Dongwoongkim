package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.Date;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Integer day = inputView.inputDay();
        Date date = new Date(day);
        
    }
}
