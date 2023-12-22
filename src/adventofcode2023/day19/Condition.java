package adventofcode2023.day19;

public class Condition {
    private String variable;
    private String operator;
    private Integer value;
    private String result;

    public Condition(String variable, String operator, Integer value, String result) {
        this.variable = variable;
        this.operator = operator;
        this.value = value;
        this.result = result;
    }

    public Condition(String result) {
        this(null, null, null, result);
    }

    public String getVariable() {
        return variable;
    }

    public String getOperator() {
        return operator;
    }

    public Integer getValue() {
        return value;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Condition [variable=" + variable + ", operator=" + operator + ", value=" + value + ", result=" + result
                + "]";
    }

}
