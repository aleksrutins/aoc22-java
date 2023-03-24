package aoc22;

import java.util.function.Supplier;
import java.util.stream.Stream;

import aoc22.day2.Round;


public class Day2 implements Exercise<Stream<Round>> {

    public Stream<Round> parseInput(String input) {
        return input.lines().filter(line -> !line.isEmpty()).map(line -> new Round(
            line.split(" ")[0].charAt(0) - 'A',
            line.split(" ")[1].charAt(0) - 'X'
        ));
    }

    private int pointsFor(int play) {
        return play + 1;
    }

    private int pointsFor(Round round) {
        if (round.opp() == round.me()) return pointsFor(round.me()) + 3;
        else if (round.opp() == 0 && round.me() == 1) return pointsFor(1) + 6;
        else if (round.opp() == 1 && round.me() == 2) return pointsFor(2) + 6;
        else if (round.opp() == 2 && round.me() == 0) return pointsFor(0) + 6;
        else return pointsFor(round.me());
    }

    private int pointsForPt2(Round round) {
        int me = switch (round.me()) {
            case 0 -> (round.opp() + 2) % 3; // loss
            case 1 -> round.opp(); // draw
            case 2 -> (round.opp() + 4) % 3; // win
            default -> 0;
        };
        return pointsFor(me) + (round.me() * 3);
    }

    public void run(Supplier<Stream<Round>> input) {
        System.out.println("Score (Part 1): " + input.get().map(this::pointsFor).reduce(Integer::sum));
        System.out.println("Score (Part 2): " + input.get().map(this::pointsForPt2).reduce(Integer::sum));
    }
}