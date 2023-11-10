package christmas.model;

public class Gift {

    private final String name;

    public Gift(String name) {
        this.name = name;
    }

    public static Gift createGift(Integer amount) {
        if (amount >= 120000) {
            return new Gift("샴페인 1개");
        }
        return new Gift("없음");
    }

    public String getName() {
        return name;
    }
}
