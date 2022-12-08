package euler54.combination;

public interface Combination extends Comparable<Combination> {
    int SIZE = 5;

    CombinationGrade getGrade();
}
