package aoc22;

import aoc22.day3.Knapsack;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Day3 implements Exercise<Stream<Knapsack>> {

    @Override
    public Stream<Knapsack> parseInput(String input) {
        return input.lines().map(line -> new Knapsack(line.substring(0, line.length() / 2), line.substring(line.length() / 2)));
    }

    public int priority(Byte item) {
        if (item >= 'a' && item <= 'z') {
            return (item - 'a') + 1;
        } else if (item >= 'A' && item <= 'Z') {
            return (item - 'A') + 27;
        } else return 0;
    }

    public <T> T printAndReturn(T thing) {
        System.out.println(thing);
        return thing;
    }

    @Override
    public void run(Supplier<Stream<Knapsack>> input) {
        System.out.println(input.get().map(Knapsack::shared).filter(Objects::nonNull).map(this::priority).reduce(Integer::sum));
    }
}