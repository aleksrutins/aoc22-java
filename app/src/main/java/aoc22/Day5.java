package aoc22;

import java.util.function.Supplier;

import aoc22.day5.StackInstructions;

public class Day5 implements Exercise<StackInstructions> {

    @Override
    public void run(Supplier<StackInstructions> input) {
        var instructions = input.get();
        instructions.evaluateAll();
        System.out.println("Part 1: " + instructions.stacks.stream().map(str -> str.substring(str.length()-1)).reduce("", (prev, c) -> prev + c)
                                      + " (" + instructions.instructions.size() + " instructions)");
        
        instructions = input.get();
        instructions.isPart2 = true;
        instructions.evaluateAll();
        System.out.println("Part 2: " + instructions.stacks.stream().map(str -> str.substring(str.length()-1)).reduce("", (prev, c) -> prev + c)
                                    + " (" + instructions.instructions.size() + " instructions)");
    }

    @Override
    public StackInstructions parseInput(String input) {
        return StackInstructions.parseInput(input);
    }
}
