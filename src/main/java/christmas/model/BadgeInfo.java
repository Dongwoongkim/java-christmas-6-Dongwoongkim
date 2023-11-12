package christmas.model;

public enum BadgeInfo {

    SANTA_BADGE("산타", 20000),
    TREE_BADGE("트리", 10000),
    STAR_BADGE("별", 5000),
    NONE_BADGE("없음", 0);

    private final String badgeName;
    private final Integer requireAmount;

    BadgeInfo(final String badgeName, final Integer requireAmount) {
        this.badgeName = badgeName;
        this.requireAmount = requireAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public Integer getRequireAmount() {
        return requireAmount;
    }
}
