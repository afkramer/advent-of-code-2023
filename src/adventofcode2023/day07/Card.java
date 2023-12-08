package adventofcode2023.day07;

import java.util.Arrays;
import java.util.List;

public class Card implements Comparable<Card> {
    private Character value;
    private List<Character> cardRanking;

    public Card(Character value, boolean useJokers) {
        this.value = value;
        if (useJokers) {
            this.cardRanking = Arrays.asList('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J');
        } else {
            this.cardRanking = Arrays.asList('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2');
        }
    }

    public Character getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((cardRanking == null) ? 0 : cardRanking.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (cardRanking == null) {
            if (other.cardRanking != null)
                return false;
        } else if (!cardRanking.equals(other.cardRanking))
            return false;
        return true;
    }

    @Override
    public int compareTo(Card otherCard) {
        return cardRanking.indexOf(otherCard.value) - cardRanking.indexOf(this.value);
    }

}
