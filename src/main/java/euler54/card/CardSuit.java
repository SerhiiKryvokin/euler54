package euler54.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CardSuit {
    SPADES("S"),
    HEARTS("H"),
    DIAMONDS("D"),
    CLUBS("C");

    private final String stringRepresentation;
}
