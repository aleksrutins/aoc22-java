package aoc22;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Supplier;

public class Day6 implements Exercise<char[]> {
    @Override
    public char[] parseInput(String input) {
        return input.toCharArray();
    }

    @Override
    public void run(Supplier<char[]> input) {
        var fullBuf = input.get();

        var buf = new char[4];

search: for(int i = 0; i < fullBuf.length; i++) {
            buf = Arrays.copyOfRange(buf, 1, 5);
            buf[3] = fullBuf[i];
            if(buf[0] == 0) continue;
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    if(buf[j] == buf[k] && j != k) {
                        continue search;
                    }
                }
            }
            System.out.println("Part 1: " + (i + 1));
            break;
        }

        buf = new char[14];
search: for(int i = 0; i < fullBuf.length; i++) {
            buf = Arrays.copyOfRange(buf, 1, 15);
            buf[13] = fullBuf[i];
            if(buf[0] == 0) continue;
            for(int j = 0; j < 14; j++) {
                for(int k = 0; k < 14; k++) {
                    if(buf[j] == buf[k] && j != k) {
                        continue search;
                    }
                }
            }
            System.out.println("Part 2: " + (i + 1));
            break;
        }
    }
}
