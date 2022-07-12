package org.adamd;

import org.adamd.challanges.AllPrimes;
import org.adamd.challanges.kSort;
import org.adamd.datastruct.BasicTree;
import org.adamd.datastruct.MaxHeapTree;
import org.adamd.datastruct.RBTree;
import org.adamd.search.*;
import org.adamd.sort.BubbleSort;
import org.adamd.sort.CountingSort;
import org.adamd.sort.MS;
import org.adamd.sort.QS;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class Main {

    public static void main(String[] args) {
        primes();
    }

    public static void primes() {
        var primes = AllPrimes.primesUpTo(1000);

        System.out.println("All primes %s".formatted(Arrays.toString(primes)));
    }

    public static void motherVertexSearch() {
        // int arr[][] = {{1, 2}, {3}, {}, {1}, {2, 6}, {0, 6}, {0, 4}};
        int arr[][] = {{2}, {0}, {1}, {1,4}, {}, {6}, {}};
        var mother = MotherVertex.dfsCyclic(arr);

        System.out.println("Mother vertex is %d".formatted(mother));
    }

    public static void kSortTest() {
        int arr[] = {6, 5, 3, 2, 8, 10, 9};
        arr = kSort.insertSort(arr, 3);

        System.out.println("Sorted array %s".formatted(Arrays.toString(arr)));
    }

    public static void basicTree() {
        BasicTree<Integer> root = new BasicTree<>(10);
        var subTree = root.addLeaf(12);
        subTree.addLeaf(45);
        subTree = root.addLeaf(20);

        subTree.addLeaf(122);
        subTree.addLeaf(65);

        root.getCorners(root);
    }

    public static void maxHeapTest() {
        int arr[] = {12, 11, 13, 5, 6, 7};
        final MaxHeapTree mht = new MaxHeapTree();

        mht.sort(arr);
        System.out.println("Sorted array %s".formatted(Arrays.toString(arr)));
    }


    public static void binarysearch() {
        int arr[] = {-1, 0, 3, 5, 9, 12};

        var idx = BinarySearch.search(arr, 9);

        System.out.println("Found at index: %d".formatted(idx));
    }

    public static void graphSearches() {
        int arr[][] = {{1, 2}, {0, 2, 4}, {0, 1, 3}, {2}, {5}};

        boolean isCyclic = GraphSearch.dfs_cyclic(arr, 0);
        System.out.println("Is cyclic: %b".formatted(isCyclic));

    }

    public static void countingSort() {
        var arr = RandomGenerator.getDefault().ints(30, 0, 10).toArray();

        System.out.println("Unsorted: %s".formatted(Arrays.toString(arr)));
        arr = CountingSort.sort(arr);
        for (var idx = 1; idx < arr.length; ++idx) {
            assert arr[idx - 1] < arr[idx];
        }
        System.out.println("Sorted: %s".formatted(Arrays.toString(arr)));
        System.out.println("Sorted in: %d runs vs %d [0(n+k)]"
                .formatted(CountingSort.OPERATIONS, (arr.length + Arrays.stream(arr).max().getAsInt())));
    }

    public static void mergeSort() {
        var ms = new MS(new int[]{12, 11, 13, 5, 6, 7});

        System.out.println(Arrays.toString(ms.arr));
        System.out.println(Arrays.toString(ms.sort()));
    }

    public static void bubbleSort() {
        var arr = RandomGenerator.getDefault().ints(30).toArray();

        System.out.println("Unsorted: %s".formatted(Arrays.toString(arr)));
        arr = BubbleSort.sort(arr);
        for (var idx = 1; idx < arr.length; ++idx) {
            assert arr[idx - 1] < arr[idx];
        }
        System.out.println("Sorted: %s".formatted(Arrays.toString(arr)));
        System.out.println("Sorted in: %d runs vs %d to %d [0(n) to n(n^2)]"
                .formatted(BubbleSort.OPERATIONS, arr.length, (int) Math.pow(arr.length, 2)));
    }

    public static void qs() {
        var qs = new QS(RandomGenerator.getDefault().ints(30).toArray());

        System.out.println(Arrays.toString(qs.arr));
        System.out.println(Arrays.toString(qs.sort()));
    }


    public static void bstSearch() {
        //create a BST object
        BST<Integer> bst = new BST<>();
        /* BST tree example
              45
           /     \
          10      90
         /  \    /
        7   12  50   */
        //insert data into BST
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);
        //print the BST
        System.out.println("The BST Created with input data(Left-root-right):");
        bst.inorder();

        //delete leaf node
        System.out.println("\nThe BST after Delete 12(leaf node):");
        bst.delete(12);
        bst.inorder();
        //delete the node with one child
        System.out.println("\nThe BST after Delete 90 (node with 1 child):");
        bst.delete(90);
        bst.inorder();

        //delete node with two children
        System.out.println("\nThe BST after Delete 45 (Node with two children):");
        bst.delete(45);
        bst.inorder();
        //search a key in the BST
        boolean ret_val = bst.search(50);
        System.out.println("\nKey 50 found in BST:" + ret_val);
        ret_val = bst.search(12);
        System.out.println("\nKey 12 found in BST:" + ret_val);
    }

    public static void skipSearch() {
        SkipList<Integer> sl = new SkipList<>();
        int[] data = {4, 2, 7, 0, 9, 1, 3, 7, 3, 4, 5, 6, 0, 2, 8};
        for (int i : data) {
            sl.insert(i);
        }

        sl.print();
        sl.search(4);

        sl.delete(4);

        System.out.println("Inserting 10");
        sl.insert(10);
        sl.print();
        sl.search(10);
    }

    public static void testHashAndComp() {
        final var text = "ABCDrgdfgdfgdfgdfgdgdfdfhfdEF";
        System.out.println("Starting program with '%s'".formatted(text));
        var finalhash = text.chars().parallel().reduce(0, (hash, c) -> 2 * ((hash << 5) - c + hash) + 5 % 8 % 5);
        System.out.println("Final hash is %d".formatted(finalhash));
    }
}
