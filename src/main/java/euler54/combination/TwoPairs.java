package euler54.combination;

import euler54.card.Card;
import euler54.card.CardType;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@Getter
public class TwoPairs extends CombinationBase<TwoPairs> {

    private final CardType[] pairTypes = new CardType[2];
    private CardType kicker;

    public TwoPairs(Card[] cards) {
        super(cards);
        init();
    }

    private void init() {
        int pairTypeIdx = 0;
        int kickerIdx = cards.length - 1;
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getType() == cards[i + 1].getType()) {
                pairTypes[pairTypeIdx++] = cards[i].getType();

                if (pairTypeIdx == 2) {
                    break;
                }

                i++;
            } else {
                kickerIdx = i;
            }
        }

        if (pairTypes[1] == null) {
            throwConstructionException();
        }

        kicker = cards[kickerIdx].getType();
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.TWO_PAIRS;
    }

    @Override
    protected int tieBreak(TwoPairs another) {
        return Comparator.comparing(TwoPairs::getPairTypes, Arrays::compare)
                .thenComparing(TwoPairs::getKicker)
                .compare(this, another);
    }
}
