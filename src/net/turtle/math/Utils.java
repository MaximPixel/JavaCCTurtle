package net.turtle.math;

import java.util.Collection;
import java.util.HashMap;

public final class Utils {

    private Utils() {
    }

    public static <T> HashMap<T, Integer> countValues(Collection<T> values) {
        HashMap<T, Integer> counts = new HashMap();

        values.forEach(value -> {
            int count = 1;
            if (counts.containsKey(value)) {
                count = counts.get(value) + 1;
            }
            counts.put(value, count);
        });

        return counts;
    }
}
