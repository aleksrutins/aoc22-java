package aoc22.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class StackInstructions {
    public ArrayList<String> stacks = new ArrayList<>();
    public ArrayList<MoveInstruction> instructions = new ArrayList<>();
    public boolean isPart2 = false;

    public static record MoveInstruction(int num, int from, int to) {
        public static MoveInstruction parse(String input) {
            var pattern = Pattern.compile("move (\\d+?) from (\\d+?) to (\\d+?)");
            var matcher = pattern.matcher(input);
            matcher.find();
            return new MoveInstruction(parseInt(matcher.group(1)), parseInt(matcher.group(2)), parseInt(matcher.group(3)));
        }
    }

    public void evaluateInstruction(MoveInstruction instruction) {
        var fromStack = stacks.get(instruction.from - 1);
        var toStack = stacks.get(instruction.to - 1);

        var value = fromStack.substring(fromStack.length() - instruction.num);
        if(!isPart2) value = new StringBuilder(value).reverse().toString();

        stacks.set(instruction.from - 1, fromStack.substring(0, fromStack.length() - instruction.num));
        stacks.set(instruction.to - 1, toStack + value);
    }

    public void evaluateAll() {
        for (var instruction : instructions)
            evaluateInstruction(instruction);
    }

    public static StackInstructions parseInput(String input) {
        var sections = input.split("\n\n"); // blank line separating stacks from instructions
        var result = new StackInstructions();
        
        // Add all the instructions (the easy part)
        for(var instruction : sections[1].split("\n")) {
            result.instructions.add(MoveInstruction.parse(instruction));
        }

        // And now, parse the stacks
        var stacksSection = sections[0].split("\n");
        var columns = stacksSection[stacksSection.length - 1];
        var stacks = Arrays.copyOf(stacksSection, stacksSection.length-1);
        int maxColumn = (int) Arrays.stream(columns.split(" ")).filter(col -> col != "" && col != " ").count();

        var initStacks = new String[maxColumn];
        Arrays.fill(initStacks, "");
        
        for(var line : stacks) {
            int spaces = 0;
            int currentColumn = 0;
            for(int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if(c == ' ') spaces++;
                else {
                    currentColumn += (spaces / 4) + (spaces % 4);
                    spaces = 0;
                    if(c == '[' || c == ']') continue;
                    initStacks[currentColumn] = c + initStacks[currentColumn];
                }
            }
        }

        result.stacks.addAll(Arrays.asList(initStacks));

        return result;
    }
}
