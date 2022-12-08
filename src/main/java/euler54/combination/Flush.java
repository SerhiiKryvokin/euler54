package euler54.combination;

import euler54.card.Card;

import java.util.Comparator;

public class Flush extends CombinationBase<Flush> {
    public Flush(Card[] cards) {
        super(cards);

        if (!isFlush()) {
            throwConstructionException();
        }
    }

    @Override
    public CombinationGrade getGrade() {
        return CombinationGrade.FLUSH;
    }

    @Override
    protected int tieBreak(Flush another) {
        return Comparator.comparing(Flush::getHighestCard)
                .compare(this, another);
    }
}
