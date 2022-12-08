package euler54.combination;

import euler54.card.Card;
import euler54.card.CardType;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@Getter
public class ThreeOfAKind extends CombinationBase<ThreeOfAKind> {
    private final CardType[] kickers = new CardType[Combination.SIZE - 3];
    private CardType threeOfAKindType;

    public ThreeOfAKind(Card[] cards) {
        super(cards);
        init();
    }

    private void init() {
        int threeOfAKindIdx = -1;
        for (int i = 0; i < cards.length - 2; i++) {
            if (cards[i].getType() == cards[i + 1].getType()
                    && cards[i].getType() == cards[i + 2].getType()) {

                threeOfAKindType = cards[i].getType();
                threeOfAKindIdx = i;
                break;
            }
        }

        if (threeOfAKindType == null) {
            throwConstructionException();
        }

        int kickerIdx = 0;
        for (int i = 0; i < cards.length; i++) {
            if (i != threeOfAKindIdx && i != threeOfAKindIdx + 1 && i != threeOfAKindIdx + 2) {
                kickers[kickerIdx++] = cards[i].getType();
            }
        }
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.THREE_OF_A_KIND;
    }

    @Override
    protected int tieBreak(ThreeOfAKind another) {
        return Comparator.comparing(ThreeOfAKind::getThreeOfAKindType)
                .thenComparing(ThreeOfAKind::getKickers, Arrays::compare)
                .compare(this, another);
    }
}
