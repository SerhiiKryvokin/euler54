package euler54.combination;

import euler54.card.Card;
import euler54.card.CardCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CombinationTest {
    private final Classifier classifier = Classifier.getInstance();
    private final CardCache cardCache = CardCache.getInstance();

    @Test
    void straightFlushWinsFourOfAKind() {
        String straightFlushHandString = "QC KC TC JC AC";
        String fourOfAKindHandString = "7S 7D 7H 7C KS";
        Card[] straightFlushHand = toCards(straightFlushHandString);
        Card[] fourOfAKindHand = toCards(fourOfAKindHandString);

        Combination straightFlushCombination = classifier.classify(straightFlushHand);
        Combination fourOfAKindCombination = classifier.classify(fourOfAKindHand);

        Assertions.assertEquals(CombinationGrade.STRAIGHT_FLUSH, straightFlushCombination.getGrade());
        Assertions.assertEquals(CombinationGrade.FOUR_OF_A_KIND, fourOfAKindCombination.getGrade());

        Assertions.assertTrue(straightFlushCombination.compareTo(fourOfAKindCombination) > 0);
    }

    @Test
    void FourOfAKindWinsFullHouse() {
        String fullHouseHandString = "AD AS JS JC AC";
        String fourOfAKindHandString = "7S 7D 7H 7C KS";
        Card[] fullHouseHand = toCards(fullHouseHandString);
        Card[] fourOfAKindHand = toCards(fourOfAKindHandString);

        Combination fullHouseCombination = classifier.classify(fullHouseHand);
        Combination fourOfAKindCombination = classifier.classify(fourOfAKindHand);

        Assertions.assertEquals(CombinationGrade.FULL_HOUSE, fullHouseCombination.getGrade());
        Assertions.assertEquals(CombinationGrade.FOUR_OF_A_KIND, fourOfAKindCombination.getGrade());

        Assertions.assertTrue(fullHouseCombination.compareTo(fourOfAKindCombination) < 0);
    }

    private Card[] toCards(String hand) {
        return Arrays.stream(hand.split(" "))
                .map(cardCache::getByStringRepresentation)
                .toArray(Card[]::new);
    }
}
