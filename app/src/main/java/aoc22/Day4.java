package aoc22;

import aoc22.day4.Pair;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4 implements Exercise<Stream<Pair>> {

    @Override
    public void run(Supplier<Stream<Pair>> input) {
        System.out.println("Part 1: " + input.get().filter(inp ->
                (inp.elf1().get(0) <= inp.elf2().get(0) && inp.elf1().get(inp.elf1().size() - 1) >= inp.elf2().get(inp.elf2().size() - 1)) ||
                    (inp.elf2().get(0) <= inp.elf1().get(0) && inp.elf2().get(inp.elf2().size() - 1) >= inp.elf1().get(inp.elf1().size() - 1))
            )
            .count());
    }

    @Override
    public Stream<Pair> parseInput(String input) {
        return input.lines()
            .map(line -> line.split(","))
            .map(pair ->
                Arrays.stream(pair)
                    .map(range ->
                        Arrays.stream(range.split("-"))
                            .map(Integer::parseInt)
                            .toList())
                    .map(range -> IntStream.rangeClosed(range.get(0), range.get(1)).boxed().toList())
                    .toList())
            .map(pair -> new Pair(pair.get(0), pair.get(1)));
    }
}