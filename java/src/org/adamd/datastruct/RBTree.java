package org.adamd.datastruct;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class RBTree {
    public static void createTree() {
        Integer[] nums={2,4,1,6,3,7,9,5};
        SortedSet<Integer> tree=new TreeSet<>(Arrays.asList(nums));

        printAll(tree);
    }

    public static void printAll(SortedSet<Integer> tree){
        for(int s: tree){
            System.out.println(s);
        }
        System.out.println();
    }
}
