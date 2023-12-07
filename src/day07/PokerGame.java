package day07;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import adventofcode2023.TextParserUtil;

public class PokerGame {
    private List<PokerHand> hands;
    private boolean useJokers;

    public PokerGame(boolean useJokers) {
        this.useJokers = useJokers;
        initializeHands();

    }

    public void initializeHands() {
        List<String> rawData = TextParserUtil.readData("day07.txt");
        List<PokerHand> newHands = new ArrayList<>();
        for (String rawLine : rawData) {
            String[] elements = rawLine.split("\\s");
            String[] cardValues = elements[0].split("");
            List<Card> cards = new ArrayList<>();
            for (String cardValue : cardValues) {
                Card card = new Card(cardValue.charAt(0), this.useJokers);
                cards.add(card);
            }
            PokerHand hand = new PokerHand(cards, TextParserUtil.parseLong(elements[1]), this.useJokers);
            newHands.add(hand);
        }
        hands = newHands;
    }

    public long rankAndScoreHands() {
        // Collections.sort(hands);
        hands.sort(PokerHand::compareTo);
        final List<PokerHand> handsFinal = hands;
        return IntStream.rangeClosed(1, handsFinal.size()).boxed()
                .map((Integer i) -> i * handsFinal.get(i - 1).getBidValue()).reduce(0L,
                        (a, b) -> a + b);
    }

}
