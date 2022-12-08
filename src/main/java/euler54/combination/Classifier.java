package euler54.combination;

import euler54.card.Card;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Classifier {

    private static final Classifier INSTANCE = new Classifier();

    private Classifier() {
    }

    private static final List<Function<Card[], Combination>> COMBINATION_CONSTRUCTORS_ORDERED = List.of(
            StraightFlush::new,
            FourOfAKind::new,
            FullHouse::new,
            Flush::new,
            Straight::new,
            ThreeOfAKind::new,
            TwoPairs::new,
            OnePair::new,
            HighCard::new
    );

    public static Classifier getInstance() {
        return INSTANCE;
    }

    public Combination classify(Card[] cards) {
        for (Function<Card[], Combination> combinationConstructor : COMBINATION_CONSTRUCTORS_ORDERED) {
            try {
                return combinationConstructor.apply(cards);
            } catch (CombinationConstructionException e) {
            }
        }

        throw new RuntimeException("Can't classify combination: " + Arrays.toString(cards));
    }
}
