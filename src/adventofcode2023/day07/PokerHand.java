package adventofcode2023.day07;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private List<Card> cards;
    private long bidValue;
    // private Map<Character, Long> handAnalysis;
    List<Long> cardCounts;
    private HandType handType;
    private boolean useJokers;

    public PokerHand(List<Card> cards, long bidValue, boolean useJokers) {
        this.cards = cards;
        this.bidValue = bidValue;
        this.useJokers = useJokers;
        this.cardCounts = analyzeHand();
        this.handType = HandType.parseHandTypeFromHand(this);

    }

    private List<Long> analyzeHand() {
        Map<Character, Long> groupings = this.cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Long jokerCount = groupings.get('J');
        if (useJokers && jokerCount != null && jokerCount < 5L) {
            // Long jokerCount = groupings.get('J');
            groupings.remove('J');
            List<Long> counts = groupings.values().stream().sorted().collect(Collectors.toList());
            int lastElementIndex = counts.size() - 1;
            counts.set(lastElementIndex, counts.get(lastElementIndex) + jokerCount);
            return counts;
        } else {
            return groupings.values().stream().sorted().toList();
        }

    }

    public List<Card> getCards() {
        return cards;
    }

    public long getBidValue() {
        return bidValue;
    }

    public HandType getHandType() {
        return handType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cards == null) ? 0 : cards.hashCode());
        result = prime * result + (int) (bidValue ^ (bidValue >>> 32));
        result = prime * result + ((cardCounts == null) ? 0 : cardCounts.hashCode());
        result = prime * result + ((handType == null) ? 0 : handType.hashCode());
        result = prime * result + (useJokers ? 1231 : 1237);
        return result;
    }

    @Override
    public int compareTo(PokerHand other) {
        Integer difference = this.handType.getRanking() - other.getHandType().getRanking();
        if (difference == 0) {
            for (int i = 0; i < this.cards.size(); i++) {
                int cardComparison = this.cards.get(i).compareTo(other.getCards().get(i));
                if (cardComparison != 0) {
                    return cardComparison;
                }
            }
        } else {
            return difference;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PokerHand other = (PokerHand) obj;
        if (cards == null) {
            if (other.cards != null)
                return false;
        } else if (!cards.equals(other.cards))
            return false;
        if (bidValue != other.bidValue)
            return false;
        if (cardCounts == null) {
            if (other.cardCounts != null)
                return false;
        } else if (!cardCounts.equals(other.cardCounts))
            return false;
        if (handType != other.handType)
            return false;
        if (useJokers != other.useJokers)
            return false;
        return true;
    }

    public List<Long> getCardCounts() {
        return cardCounts;
    }

    public boolean isUseJokers() {
        return useJokers;
    }

}
