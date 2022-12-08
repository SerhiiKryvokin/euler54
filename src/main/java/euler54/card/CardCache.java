package euler54.card;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardCache {
    private static final CardCache INSTANCE = new CardCache();
    private final Map<String, Card> stringToCard;

    private CardCache() {
        stringToCard = Arrays.stream(CardType.values())
                .map(this::allCardsOfType)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableMap(Card::toString, Function.identity()));
    }

    public static CardCache getInstance() {
        return INSTANCE;
    }

    public Card getByStringRepresentation(String stringRepresentation) {
        return stringToCard.get(stringRepresentation);
    }

    private List<Card> allCardsOfType(CardType cardType) {
        return Arrays.stream(CardSuit.values())
                .map(suit -> new Card(cardType, suit))
                .toList();
    }
}
