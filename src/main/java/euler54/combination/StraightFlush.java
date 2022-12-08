package euler54.combination;

import euler54.card.Card;
import euler54.card.CardType;

import java.util.Comparator;

public class StraightFlush extends CombinationBase<StraightFlush> {
    public StraightFlush(Card[] cards) {
        super(cards);

        if (!isStraight() || !isFlush()) {
            throwConstructionException();
        }
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.STRAIGHT_FLUSH;
    }

    @Override
    protected int tieBreak(StraightFlush another) {
        return Comparator.comparing(StraightFlush::getHighestCard)
                .compare(this, another);
    }

    @Override
    protected Card getHighestCard() {
        if (cards[0].getType() == CardType.ACE && cards[1].getType() == CardType.FIVE) {
            return cards[1];
        }
        return super.getHighestCard();
    }
}
