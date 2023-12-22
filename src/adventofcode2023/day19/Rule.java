package adventofcode2023.day19;

import java.util.List;

public class Rule {
    private String name;
    private List<Condition> conditions;

    public Rule(String name, List<Condition> conditions) {
        this.name = name;
        this.conditions = conditions;
    }

    public String getName() {
        return name;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

}
