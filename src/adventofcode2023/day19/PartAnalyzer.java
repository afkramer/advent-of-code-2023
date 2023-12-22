package adventofcode2023.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import adventofcode2023.TextParserUtil;

public class PartAnalyzer {
    private List<String> rawInput = TextParserUtil.readData("day19.txt");
    private List<Rule> rules;
    private List<Part> parts;

    public PartAnalyzer() {
        int indexOfBlank = rawInput.indexOf("");
        initializeRules(rawInput.subList(0, indexOfBlank));
        initializeParts(rawInput.subList(indexOfBlank + 1, rawInput.size()));
    }

    private void initializeRules(List<String> rawInputRules) {
        this.rules = new ArrayList<>();
        for (String string : rawInputRules) {
            int ruleStartIndex = string.indexOf("{");
            String name = string.substring(0, ruleStartIndex);
            // System.out.printf("Rule name: %s%n", name);
            String[] conditions = string.substring(ruleStartIndex + 1, string.length() - 1).split(",");
            List<Condition> conditionsToAdd = new ArrayList<>();
            for (String condition : conditions) {
                String[] elements = condition.split(":");
                if (elements.length == 1) {
                    String result = elements[0];
                    conditionsToAdd.add(new Condition(result));
                    // System.out.printf("Adding simple condition just with result %s%n", result);
                } else {
                    String variable = elements[0].substring(0, 1);
                    String operator = elements[0].substring(1, 2);
                    Integer value = TextParserUtil.parseInteger(elements[0].substring(2));
                    String result = elements[1];
                    // System.out.printf("Adding condition: %s %s %d -> %s%n", variable, operator,
                    // value, result);
                    conditionsToAdd.add(new Condition(variable, operator, value, result));
                }
            }
            this.rules.add(new Rule(name, conditionsToAdd));

        }
    }

    private void initializeParts(List<String> rawInputParts) {
        this.parts = new ArrayList<>();
        for (String string : rawInputParts) {
            String[] variableValues = string.substring(1, string.length() - 1).split(",");
            int x = TextParserUtil.parseInteger(variableValues[0].substring(2));
            int m = TextParserUtil.parseInteger(variableValues[1].substring(2));
            int a = TextParserUtil.parseInteger(variableValues[2].substring(2));
            int s = TextParserUtil.parseInteger(variableValues[3].substring(2));
            // System.out.printf("Adding new part x=%d, m=%d, a=%d, s=%s%n", x, m, a, s);
            parts.add(new Part(x, m, a, s));
        }

    }

    private Rule findRuleByName(String nameToFind) {
        return this.rules.stream().filter(rule -> rule.getName().equals(nameToFind)).toList().get(0);
    }

    public long addUpValues() {
        long sum = 0l;
        List<Part> acceptedParts = findAcceptedParts();
        for (Part part : acceptedParts) {
            sum += part.getX() + part.getM() + part.getA() + part.getS();
        }
        return sum;
    }

    // TODO: could probably access the variables using reflection
    private List<Part> findAcceptedParts() {
        List<Part> acceptedParts = new ArrayList<>();
        List<Part> rejectedParts = new ArrayList<>();
        for (Part part : parts) {
            System.out.printf("Analyzing part %s%n", part);
            Rule rule = findRuleByName("in");
            boolean partProcessed = false;
            while (!partProcessed) {
                System.out.printf("Analyzing rule %s%n", rule.getName());
                for (Condition condition : rule.getConditions()) {
                    System.out.printf("Analyzing condition %s%n", condition.toString());
                    if (condition.getVariable() == null) {
                        if (condition.getResult().equals("A")) {
                            System.out.printf("Simple condition A for part %s fulfilled!%n", part.toString());
                            acceptedParts.add(part);
                            partProcessed = true;
                            break;
                        } else if (condition.getResult().equals("R")) {
                            System.out.printf("Simple condition R for part %s fulfilled!%n", part.toString());
                            rejectedParts.add(part);
                            partProcessed = true;
                            break;
                        } else {
                            rule = findRuleByName(condition.getResult());
                            break;
                        }
                    }

                    int valueToCheck = switch (condition.getVariable()) {
                        case "x" -> part.getX();
                        case "m" -> part.getM();
                        case "a" -> part.getA();
                        case "s" -> part.getS();
                        default -> 0;
                    };

                    boolean conditionFulfilled = checkCondition(condition.getOperator(), condition.getValue(),
                            valueToCheck);

                    // Start here with new logic
                    if (conditionFulfilled) {
                        if (condition.getResult().equals("A")) {
                            System.out.printf("Condition fulfilled and going to A%n", condition.getVariable(),
                                    condition.getOperator(), condition.getValue(), valueToCheck);
                            acceptedParts.add(part);
                            partProcessed = true;
                            break;
                        } else if (condition.getResult().equals("R")) {
                            System.out.printf("Condition fulfilled and going to R%n", condition.getVariable(),
                                    condition.getOperator(), condition.getValue(), valueToCheck);
                            rejectedParts.add(part);
                            partProcessed = true;
                            break;
                        } else {
                            System.out.printf("Condition fulfilled and going to %s%n", condition.getResult());
                            rule = findRuleByName(condition.getResult());
                            break;
                        }
                    }
                }
            }
        }
        return acceptedParts;
    }

    private boolean checkCondition(String operator, Integer value, int variable) {
        System.out.printf("Checking if %d %s %d%n", variable, operator, value);
        return switch (operator) {
            case "<" -> variable < value;
            case ">" -> variable > value;
            default -> false;
        };
    }

    public long calculatePossibleCombos() {
        long sum = 0L;

        return sum;
    }
}
