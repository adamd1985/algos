package org.adamd.utilities;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Here I want to show how to navigate through an array of heterogenous types - usually an effect of a generic or
 * templated list.
 * Using parallel streams and spliterators I will count the total of an array of integers, floats (will floor these)
 * and strings.
 *
 * @author adamd1985
 */
public class StreamsMain {

    public static void main(String[] args) {
        Object[] arrayObjs = {1, "2", 3, "4", "5", 6};

        var result = Stream.of(arrayObjs).parallel().flatMap(
                        obj -> (obj instanceof String) ?
                                Stream.of(Integer.valueOf((String)obj)) :
                                Stream.of((Integer)obj)
                ).reduce(0, (a, b) -> a + b);

        System.out.println(result);
    }
}
