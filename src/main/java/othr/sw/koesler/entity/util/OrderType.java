package othr.sw.koesler.entity.util;

public enum OrderType {
    Human_Transport("Person Transport"), Item_Transport("Item Transport");

    private final String label;

    OrderType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
