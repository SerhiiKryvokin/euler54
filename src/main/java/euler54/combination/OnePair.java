package euler54.combination;

import euler54.card.Card;
import euler54.card.CardType;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@Getter
public class OnePair extends CombinationBase<OnePair> {
    private final CardType[] kickers = new CardType[Combination.SIZE - 2];
    private CardType pairType;

    public OnePair(Card[] cards) {
        super(cards);
        init();
    }

    private void init() {
        int pairIdx = 0;
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getType() == cards[i + 1].getType()) {
                pairType = cards[i].getType();
                pairIdx = i;
                break;
            }
        }

        if (pairType == null) {
            throwConstructionException();
        }

        int kickerIdx = 0;
        for (int i = 0; i < cards.length; i++) {
            if (i != pairIdx && i != pairIdx + 1) {
                kickers[kickerIdx++] = cards[i].getType();
            }
        }
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.ONE_PAIR;
    }

    @Override
    protected int tieBreak(OnePair another) {
        return Comparator.comparing(OnePair::getPairType)
                .thenComparing(OnePair::getKickers, Arrays::compare)
                .compare(this, another);
    }
}
