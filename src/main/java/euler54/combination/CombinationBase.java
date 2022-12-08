package euler54.combination;

import euler54.card.Card;
import euler54.card.CardType;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@Getter
public abstract class CombinationBase<T extends Combination> implements Combination {
    protected final Card[] cards;

    public CombinationBase(Card[] cards) {
        validateSize(cards);
        this.cards = Arrays.stream(cards).sorted(Comparator.reverseOrder()).toArray(Card[]::new);
    }

    @Override
    public int compareTo(Combination anotherCombination) {
        int gradeComparison = getGrade().compareTo(anotherCombination.getGrade());
        if (gradeComparison != 0) {
            return gradeComparison;
        } else {
            return tieBreak((T) anotherCombination);
        }
    }

    protected boolean isFlush() {
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getSuit() != cards[i + 1].getSuit()) {
                return false;
            }
        }

        return true;
    }

    protected boolean isStraight() {
        if (cards[0].getType() == CardType.ACE && cards[1].getType() == CardType.FIVE) {
            int nextOrdinal = CardType.FIVE.ordinal();
            for (int i = 1; i < cards.length; i++) {
                if (cards[i].getType().ordinal() != nextOrdinal--) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < cards.length - 1; i++) {
                if (cards[i + 1].getType().ordinal() != cards[i].getType().ordinal() - 1) {
                    return false;
                }
            }
        }

        return true;
    }

    protected Card getHighestCard() {
        return Arrays.stream(cards).max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalStateException("Unexpected combination state"));
    }

    protected void throwConstructionException() {
        throw new CombinationConstructionException("Can't build " + getGrade() + " from cards " + Arrays.toString(cards));
    }

    protected abstract int tieBreak(T another);

    private static void validateSize(Card[] cards) {
        if (cards.length != Combination.SIZE) {
            throw new CombinationConstructionException("Combination must consist of " + Combination.SIZE + " cards, but was " + cards.length);
        }
    }
}
