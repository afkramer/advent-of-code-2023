package adventofcode2023.day10;

public enum FillType {
    INSIDE("I"), OUTSIDE("O"), SEQUENCE("S"), NONE("N");

    private String string;

    private FillType(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
