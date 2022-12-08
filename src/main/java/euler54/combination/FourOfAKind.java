package euler54.combination;

import euler54.card.Card;
import euler54.card.CardType;
import lombok.Getter;

import java.util.Comparator;

@Getter
public class FourOfAKind extends CombinationBase<FourOfAKind> {
    private CardType kicker;
    private CardType fourOfAKindType;

    public FourOfAKind(Card[] cards) {
        super(cards);

        init();
    }

    private void init() {
        int kickerIdx = cards.length - 1;
        for (int i = 0; i < cards.length - 3; i++) {
            if (cards[i].getType() == cards[i + 1].getType()
                    && cards[i].getType() == cards[i + 2].getType()
                    && cards[i].getType() == cards[i + 3].getType()) {

                fourOfAKindType = cards[i].getType();
                break;
            } else {
                kickerIdx = i;
            }
        }

        if (fourOfAKindType == null) {
            throwConstructionException();
        }

        kicker = cards[kickerIdx].getType();
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.FOUR_OF_A_KIND;
    }

    @Override
    protected int tieBreak(FourOfAKind another) {
        return Comparator.comparing(FourOfAKind::getFourOfAKindType)
                .thenComparing(FourOfAKind::getKicker)
                .compare(this, another);
    }
}
