package aoc22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

public class Util {
    public static String getInput(String day) throws IOException {
        var url = Util.class.getResource("/input/" + day + ".dat");

        if (url == null) {
            return "";
        }

        try (var stream = url.openStream()) {
            return new String(stream.readAllBytes());
        }
    }

    public static <I> void runDay(Exercise<I> day) throws IOException {
        var data = getInput(day.getClass().getSimpleName().toLowerCase());
        System.out.println("--- " + day.getClass().getSimpleName() + " ---");
        day.resolveDI();
        day.run(() -> day.parseInput(data));
    }
}