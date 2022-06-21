package org.adamd;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class Main {

    public static void main(String[] args) {
        ms();
    }

    public static void ms() {
        var ms =  new MS(new int[]{3,4,2,1,7,8});

        System.out.println(Arrays.toString(ms.arr));
        System.out.println(Arrays.toString(ms.sort()));
    }

    public static void qs() {
        var qs =  new QS(RandomGenerator.getDefault().ints(30).toArray());

        System.out.println(Arrays.toString(qs.arr));
        System.out.println(Arrays.toString(qs.sort()));
    }


    public static void bstSearch(){
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
        boolean ret_val = bst.search (50);
        System.out.println("\nKey 50 found in BST:" + ret_val );
        ret_val = bst.search (12);
        System.out.println("\nKey 12 found in BST:" + ret_val );
    }

    public static void skipSearch(){
        SkipList<Integer> sl = new SkipList<>();
        int[] data = {4,2,7,0,9,1,3,7,3,4,5,6,0,2,8};
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

    public static void testHashAndComp(){
        final var text = "ABCDrgdfgdfgdfgdfgdgdfdfhfdEF";
        System.out.println("Starting program with '%s'".formatted(text));
        var finalhash = text.chars().parallel().reduce(0, (hash, c) ->  2 * ((hash << 5) - c + hash) + 5 % 8 % 5);
        System.out.println("Final hash is %d".formatted(finalhash));
    }
}
