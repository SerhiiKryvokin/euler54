package euler54;

import euler54.card.Card;
import euler54.card.CardCache;
import euler54.combination.Classifier;
import euler54.combination.Combination;
import lombok.SneakyThrows;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Runner {
    private static final String INPUT_FILE_NAME = "p054_poker.txt";
    private final CardCache cardCache = CardCache.getInstance();
    private final Classifier classifier = Classifier.getInstance();

    private int countPlayer1Wins() {
        Scanner scanner = new Scanner(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/" + INPUT_FILE_NAME))));
        int wins = 0;
        while (scanner.hasNextLine()) {
            List<Card> cards = Arrays.stream(scanner.nextLine().split(" "))
                    .map(cardCache::getByStringRepresentation)
                    .toList();

            Card[] hand1 = cards.subList(0, 5).toArray(Card[]::new);
            Card[] hand2 = cards.subList(5, cards.size()).toArray(Card[]::new);

            Combination combination1 = classifier.classify(hand1);
            Combination combination2 = classifier.classify(hand2);

            System.out.println(Arrays.toString(hand1) + " classified as " + combination1.getGrade());
            System.out.println(Arrays.toString(hand2) + " classified as " + combination2.getGrade());

            System.out.println("Comparison result: " + combination1.compareTo(combination2));
            if (combination1.compareTo(combination2) > 0) {
                wins++;
            }
            System.out.println();
        }

        return wins;
    }

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Total wins: " + new Runner().countPlayer1Wins());
    }
}
