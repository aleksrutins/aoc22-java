package aoc22.day3;

import javax.annotation.Nullable;

public record Knapsack(String compartment1, String compartment2) {
    public @Nullable Byte shared() {
        for (byte c : compartment1.getBytes()) {
            if (compartment2.contains(new String(new byte[]{c}))) return c;
        }
        return null;
    }
}