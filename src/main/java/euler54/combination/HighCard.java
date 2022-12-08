package euler54.combination;

import euler54.card.Card;

import java.util.Arrays;

public class HighCard extends CombinationBase<HighCard> {

    public HighCard(Card[] cards) {
        super(cards);
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.HIGH_CARD;
    }

    @Override
    public int tieBreak(HighCard another) {
        return Arrays.compare(cards, another.cards);
    }
}
