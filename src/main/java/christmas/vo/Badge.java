package christmas.vo;

import static christmas.model.EventInfo.SANTA_BADGE_REQUIREMENT_AMOUNT;
import static christmas.model.EventInfo.STAR_BADGE_REQUIREMENT_AMOUNT;
import static christmas.model.EventInfo.TREE_BADGE_REQUIREMENT_AMOUNT;

public class Badge {

    private static final String SANTA_BADGE = "산타";
    private static final String TREE_BADGE = "트리";
    private static final String STAR_BADGE = "별";
    private static final String NONE_BADGE = "없음";

    private final String name;

    private Badge(String name) {
        this.name = name;
    }

    public static Badge createBadge(Integer sumOfDiscount) {
        if (sumOfDiscount >= SANTA_BADGE_REQUIREMENT_AMOUNT.getValue()) {
            return new Badge(SANTA_BADGE);
        }
        if (sumOfDiscount >= TREE_BADGE_REQUIREMENT_AMOUNT.getValue()) {
            return new Badge(TREE_BADGE);
        }
        if (sumOfDiscount >= STAR_BADGE_REQUIREMENT_AMOUNT.getValue()) {
            return new Badge(STAR_BADGE);
        }
        return new Badge(NONE_BADGE);
    }

    public String getName() {
        return name;
    }
}
