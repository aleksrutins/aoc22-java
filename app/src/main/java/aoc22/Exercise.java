package aoc22;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public interface Exercise<I> {
    default void resolveDI() {
        var fields = this.getClass().getDeclaredFields();

        for(var field : fields) {
            if(field.isAnnotationPresent(Inject.class)) {
                var t = field.getType();
                try {
                    var ctor = t.getConstructor();
                    field.set(this, ctor.newInstance());
                } catch(NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                    System.out.println("Invalid dependency: " + t.getName());
                    System.exit(1);
                }
            }
        }
    }

    void run(Supplier<I> input);

    I parseInput(String input);
}