package adventofcode2023.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import adventofcode2023.TextParserUtil;

public class GalaxyMap {
    private List<String> map;
    private List<Galaxy> galaxies;

    public GalaxyMap() {
        this.map = TextParserUtil.readModifiableData("day11.txt");

    }

    public void initializeSmall() {
        expandMap(this.map);
        this.galaxies = findGalaxiesInMap(this.map);
    }

    public void initializeBig(int expandFactor) {
        this.galaxies = findGalaxiesInMap(this.map);
        expandMapBig(this.map, expandFactor);
    }

    public void expandMap(List<String> mapToExpand) {
        expandMapRows(mapToExpand);
        expandMapCols(mapToExpand);
    }

    public void expandMapBig(List<String> mapToExpand, int expandFactor) {
        expandMapRowsBig(mapToExpand, expandFactor);
        expandMapColsBig(mapToExpand, expandFactor);
        for (Galaxy galaxy : this.galaxies) {
            galaxy.updateCoordinateAfterExpansion();
        }
    }

    public void expandMapRowsBig(List<String> mapToExpand, int expandFactor) {
        for (int i = 0; i < mapToExpand.size(); i++) {
            String mapString = mapToExpand.get(i);
            if (mapString.matches(String.format("\\.{%d}", mapString.length()))) {
                for (Galaxy galaxy : this.galaxies) {
                    if (galaxy.getxCoord() >= i) {
                        galaxy.increaseXValuesToAdd(expandFactor);
                    }
                }
            }
        }
    }

    public void expandMapColsBig(List<String> mapToExpand, int expandFactor) {
        mapToExpand = transposeMap(mapToExpand);
        for (int i = 0; i < mapToExpand.size(); i++) {
            String mapString = mapToExpand.get(i);
            if (mapString.matches(String.format("\\.{%d}", mapString.length()))) {
                for (Galaxy galaxy : this.galaxies) {
                    if (galaxy.getyCoord() >= i) {
                        galaxy.increaseYValuesToAdd(expandFactor);
                    }
                }
            }
        }
    }

    public void expandMapRows(List<String> mapToExpand) {
        ListIterator<String> mapIter = mapToExpand.listIterator();

        while (mapIter.hasNext()) {
            String mapString = mapIter.next();
            if (mapString.matches(String.format("\\.{%d}", mapString.length()))) {
                mapIter.add(".".repeat(mapToExpand.get(0).length()));
            }
        }
    }

    public void expandMapCols(List<String> mapToExpand) {
        List<String> transposedMap = transposeMap(mapToExpand);
        expandMapRows(transposedMap);
        this.map = transposeMap(transposedMap);
    }

    public List<String> transposeMap(List<String> mapToTranspose) {
        int mapRows = mapToTranspose.size();
        int mapCols = mapToTranspose.get(0).length();

        String[][] transposedMap = new String[mapCols][mapRows];

        for (int row = 0; row < mapCols; row++) {
            for (int col = 0; col < mapRows; col++) {
                transposedMap[row][col] = mapToTranspose.get(col).charAt(row) + "";
            }
        }

        List<String> transposedListMap = new ArrayList<>();

        for (int row = 0; row < transposedMap.length; row++) {
            String mapString = Arrays.stream(transposedMap[row]).collect(Collectors.joining(""));
            transposedListMap.add(mapString);
        }

        return transposedListMap;
    }

    public List<Galaxy> findGalaxiesInMap(List<String> galaxyMap) {
        List<Galaxy> foundGalaxies = new ArrayList<>();

        for (int row = 0; row < galaxyMap.size(); row++) {
            for (int col = 0; col < galaxyMap.get(0).length(); col++) {
                if (galaxyMap.get(row).charAt(col) == '#') {
                    Galaxy galaxy = new Galaxy(row, col);
                    foundGalaxies.add(galaxy);
                }
            }
        }

        return foundGalaxies;
    }

    public List<String> getMap() {
        return map;
    }

    public List<Galaxy> getGalaxies() {
        return this.galaxies;
    }

}
