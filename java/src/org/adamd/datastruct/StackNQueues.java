package org.adamd.datastruct;

import java.util.*;
import java.util.stream.IntStream;


public class StackNQueues {
    public static void main(String[] args) {
        StackNQueues.testQueue();
    }

    /**
     * LIFO.
     */
    public static void testStack(){
        System.out.println("\n Stack:: ");
        Stack<Integer> stack = new Stack<>();
        final Integer[] ints = {123,343,656,234,3,3244,5,456,786,6,65};

        Arrays.stream(ints).forEach(x -> stack.push(x));
        stack.stream().forEach(System.out::println);
        for (var i : IntStream.rangeClosed(0, 3).toArray()){
            stack.pop();
        }

        System.out.println("\n NEXT:: ");
        stack.stream().forEach(System.out::println);
    }

    /**
     * FIFO.
     */
    public static void testQueue(){
        System.out.println("\n Queue:: ");
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        final Integer[] ints = {123,343,656,234,3,3244,5,456,786,6,65};

        Arrays.stream(ints).forEach(x -> q.add(x));
        q.stream().forEach(System.out::println);
        System.out.println("\n NEXT:: ");
        System.out.println(q);
        for (var i : IntStream.rangeClosed(0, q.size() - 1).toArray()){
            System.out.println(q.poll());
        }
    }
}
