package euler54.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class Card implements Comparable<Card> {
    private final CardType type;
    private final CardSuit suit;

    @Override
    public int compareTo(Card anotherCard) {
        return Integer.compare(type.ordinal(), anotherCard.type.ordinal());
    }

    @Override
    public String toString() {
        return type.getStringRepresentation() + suit.getStringRepresentation();
    }
}
