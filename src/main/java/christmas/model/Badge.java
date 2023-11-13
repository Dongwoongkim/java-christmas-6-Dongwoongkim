package christmas.model;

import static christmas.model.BadgeInfo.NONE_BADGE;
import static christmas.model.BadgeInfo.SANTA_BADGE;
import static christmas.model.BadgeInfo.STAR_BADGE;
import static christmas.model.BadgeInfo.TREE_BADGE;

public class Badge {

    private final String name;

    private Badge(final String name) {
        this.name = name;
    }

    public static Badge create(final Integer sumOfDiscount) {
        if (sumOfDiscount >= SANTA_BADGE.getRequiredAmount()) {
            return new Badge(SANTA_BADGE.getBadgeName());
        }
        if (sumOfDiscount >= TREE_BADGE.getRequiredAmount()) {
            return new Badge(TREE_BADGE.getBadgeName());
        }
        if (sumOfDiscount >= STAR_BADGE.getRequiredAmount()) {
            return new Badge(STAR_BADGE.getBadgeName());
        }
        return new Badge(NONE_BADGE.getBadgeName());
    }

    public String getName() {
        return name;
    }
}
