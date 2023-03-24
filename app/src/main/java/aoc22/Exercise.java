package aoc22;

import java.util.function.Supplier;

public interface Exercise<I> {
    void run(Supplier<I> input);

    I parseInput(String input);
}