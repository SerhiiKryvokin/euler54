package euler54.combination;

import euler54.card.Card;
import euler54.card.CardType;
import lombok.Getter;

import java.util.Comparator;

@Getter
public class FullHouse extends CombinationBase<FullHouse> {

    private CardType threeType;
    private CardType twoType;

    public FullHouse(Card[] cards) {
        super(cards);

        init();
    }

    private void init() {
        int threeOfAKindIdx = -1;
        for (int i = 0; i < cards.length - 2; i++) {
            if (cards[i].getType() == cards[i + 1].getType()
                    && cards[i].getType() == cards[i + 2].getType()) {

                threeType = cards[i].getType();
                threeOfAKindIdx = i;
                break;
            }
        }

        if (threeType == null) {
            throwConstructionException();
        }

        for (int i = 0; i < cards.length - 1; i++) {
            if (!inThree(i, threeOfAKindIdx) && cards[i].getType() == cards[i + 1].getType()) {
                twoType = cards[i].getType();
            }
        }

        if (twoType == null) {
            throwConstructionException();
        }
    }

    private boolean inThree(int idx, int threeOfAKindIdx) {
        return idx == threeOfAKindIdx || idx == threeOfAKindIdx + 1 || idx == threeOfAKindIdx + 2;
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.FULL_HOUSE;
    }

    @Override
    protected int tieBreak(FullHouse another) {
        return Comparator.comparing(FullHouse::getThreeType)
                .thenComparing(FullHouse::getTwoType)
                .compare(this, another);
    }
}
