package adventofcode2023.day02;

import adventofcode2023.TextParserUtil;

public class ElfGameRound {
    private int red;
    private int green;
    private int blue;

    public ElfGameRound(String gameInput) {
        initializeElfGameRound(gameInput);
    }

    private void initializeElfGameRound(String gameInput) {
        gameInput = gameInput.replace(",", "");
        String[] cubeQuantitiesAndColors = gameInput.split(" ");
        int currentNumber = 0;
        for (String quantityOrColor : cubeQuantitiesAndColors) {
            if (TextParserUtil.parseInteger(quantityOrColor) != null) {
                currentNumber = TextParserUtil.parseInteger(quantityOrColor);
            } else {
                setColorValue(quantityOrColor, currentNumber);
            }
        }
    }

    private void setColorValue(String color, int quantity) {
        switch (color) {
            case "red":
                this.red = quantity;
                break;
            case "green":
                this.green = quantity;
                break;
            case "blue":
                this.blue = quantity;
                break;
        }
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public boolean roundValuesAreUnderMax(int maxRed, int maxGreen, int maxBlue) {
        return this.red <= maxRed && this.green <= maxGreen && this.blue <= maxBlue;
    }
}
