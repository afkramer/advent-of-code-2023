package adventofcode2023.day13;

import java.util.ArrayList;
import java.util.List;

import adventofcode2023.TextParserUtil;

public class MirrorAnalyzer {
    private final List<String> rawData;
    private final List<List<String>> mirrors;

    public MirrorAnalyzer() {
        this.rawData = TextParserUtil.readData("day13.txt");
        this.mirrors = generateMirrorInputs();
    }

    public long sumMirrors() {
        long sum = 0;
        for (List<String> mirrorInput : mirrors) {
            Mirror mirror = new Mirror(mirrorInput);
            sum += mirror.horizontalMatchRowCount() * 100;
            sum += mirror.verticalMatchColumnCount();
        }
        return sum;
    }

    public long sumMirrorsChanged() {
        long sum = 0;
        for (List<String> mirrorInput : mirrors) {
            Mirror mirror = new Mirror(mirrorInput);
            sum += mirror.changedSums();
        }
        return sum;
    }

    public List<List<String>> generateMirrorInputs() {
        List<String> mirrorInputs = new ArrayList<>();
        List<List<String>> listOfMirrors = new ArrayList<>();
        for (String string : this.rawData) {
            if (string.isBlank() && !mirrorInputs.isEmpty()) {
                listOfMirrors.add(mirrorInputs);
                mirrorInputs = new ArrayList<>();
            } else {
                mirrorInputs.add(string);
            }
        }

        listOfMirrors.add(mirrorInputs);

        return listOfMirrors;
    }
}
