package aoc22.day3;

import javax.annotation.Nullable;

public record Group(Knapsack[] sacks) {
    public @Nullable Byte shared() {
        for (byte c : sacks[0].allTheStuff().getBytes()) {
            if (sacks[1].allTheStuff().contains(new String(new byte[]{c})) && sacks[2].allTheStuff().contains(new String(new byte[]{c})))
                return c;
        }
        return null;
    }
}