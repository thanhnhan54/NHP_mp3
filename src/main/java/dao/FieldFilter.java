package dao;

public class FieldFilter {
    private String field;
    int value;

    public FieldFilter(String field, int value) {
        this.field = field;
        this.value = value;
    }

    public FieldFilter() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
