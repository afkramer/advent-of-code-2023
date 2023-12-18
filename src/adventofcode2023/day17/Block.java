package adventofcode2023.day17;

public record Block(int xVal, int yVal, int heatLoss, Block blockRight, Block blockLeft) {
}
