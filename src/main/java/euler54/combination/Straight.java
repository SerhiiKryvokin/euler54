package euler54.combination;

import euler54.card.Card;
import euler54.card.CardType;

import java.util.Comparator;

public class Straight extends CombinationBase<Straight> {
    public Straight(Card[] cards) {
        super(cards);

        if (!isStraight()) {
            throwConstructionException();
        }
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.STRAIGHT;
    }

    @Override
    protected int tieBreak(Straight another) {
        return Comparator.comparing(Straight::getHighestCard)
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
