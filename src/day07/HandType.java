package day07;

import java.util.List;

public enum HandType {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    private Integer ranking;

    private HandType(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getRanking() {
        return this.ranking;
    }

    public static HandType parseHandTypeFromHand(PokerHand hand) {
        List<Long> cardCountsFromHand = hand.getCardCounts();
        if (cardCountsFromHand.equals(List.of(5L))) {
            return FIVE_OF_A_KIND;
        } else if (cardCountsFromHand.equals(List.of(1L, 4L))) {
            return FOUR_OF_A_KIND;
        } else if (cardCountsFromHand.equals(List.of(2L, 3L))) {
            return FULL_HOUSE;
        } else if (cardCountsFromHand.equals(List.of(1L, 1L, 3L))) {
            return THREE_OF_A_KIND;
        } else if (cardCountsFromHand.equals(List.of(1L, 2L, 2L))) {
            return TWO_PAIR;
        } else if (cardCountsFromHand.equals(List.of(1L, 1L, 1L, 2L))) {
            return ONE_PAIR;
        } else {
            return HIGH_CARD;
        }
    }
}
