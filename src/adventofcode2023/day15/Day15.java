package adventofcode2023.day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode2023.Day;
import adventofcode2023.TextParserUtil;

public class Day15 implements Day {
    List<String> rawInput = TextParserUtil.readData("day15.txt");
    List<String> instructionSteps = reformData();
    Map<Integer, List<Lens>> lensMap = new HashMap<>();

    @Override
    public void partOne() {
        System.out.println(sumAllInputs());
    }

    @Override
    public void partTwo() {
        initializeLensMap();
        printHashMap();
        System.out.println(sumUpHashMap());
    }

    private List<String> reformData() {
        return Arrays.stream(rawInput.get(0).split(",")).toList();
    }

    public void initializeLensMap() {
        for (String instruction : instructionSteps) {
            updateLensMap(instruction);
        }
    }

    public void updateLensMap(String instruction) {
        String[] instructionElements = instruction.split("");
        String lensName = "";
        int toContinue = 0;
        for (int i = 0; i < instructionElements.length; i++) {
            if (instructionElements[i].equals("=") || instructionElements[i].equals("-")) {
                toContinue = i;
                break;
            } else {
                lensName += instructionElements[i];
            }
        }
        String toDo = instructionElements[toContinue];
        Integer focalLength = null;
        if (instructionElements.length > toContinue + 1) {
            String focalString = "";
            for (int i = toContinue + 1; i < instructionElements.length; i++) {
                focalString += instructionElements[i];
            }
            focalLength = TextParserUtil.parseInteger(focalString);
        }

        Lens lens = new Lens(lensName, focalLength);
        int hashOfLens = hashOneInput(lensName);

        List<Lens> lensesAtHash = lensMap.get(hashOfLens);
        if (lensesAtHash == null) {
            lensMap.put(hashOfLens, new ArrayList<>());
            lensesAtHash = lensMap.get(hashOfLens);
        }

        if (toDo.equals("-")) {
            if (lensesAtHash != null) {
                // start here -> find a lens by its name, but ignore focallength
                Lens lensToRemove = getLensByLabel(lensesAtHash, lensName);
                if (lensToRemove != null) {
                    lensesAtHash.remove(lensToRemove);
                }

            }
        } else {
            Lens lensToExchange = getLensByLabel(lensesAtHash, lensName);
            if (lensesAtHash.contains(lensToExchange)) {
                int index = lensesAtHash.indexOf(lensToExchange);
                lensesAtHash.remove(lensToExchange);
                lensesAtHash.add(index, lens);
            } else {
                lensesAtHash.add(lens);
            }
        }
    }

    public Lens getLensByLabel(List<Lens> lenses, String label) {
        for (Lens lens : lenses) {
            if (lens.label().equals(label)) {
                return lens;
            }
        }
        return null;
    }

    public void printHashMap() {
        for (Map.Entry<Integer, List<Lens>> entry : lensMap.entrySet()) {
            System.out.printf("Lenses at index: %d%n", entry.getKey());
            for (Lens lens : entry.getValue()) {
                System.out.printf("Lens: label: %s, focalLength: %d%n", lens.label(), lens.focalLength());
            }
        }
    }

    public long sumUpHashMap() {
        long sum = 0;
        for (Map.Entry<Integer, List<Lens>> box : lensMap.entrySet()) {
            int counter = 1;
            for (Lens lens : box.getValue()) {
                sum += (box.getKey() + 1) * counter * lens.focalLength();
                counter++;
            }
        }
        return sum;
    }

    public long sumAllInputs() {
        long sum = 0;
        for (String string : instructionSteps) {
            sum += hashOneInput(string);
        }
        return sum;
    }

    public int hashOneInput(String input) {
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            sum += (int) input.charAt(i);
            sum *= 17;
            sum = sum % 256;
        }
        return sum;
    }

}
