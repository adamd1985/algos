package org.adamd.datastruct;

import javax.swing.text.html.Option;
import java.util.*;

public class DFSTree {
    public static void main(String[] args) {
        Node tree = new Node(null, null, 32);
        tree.left = new Node(null, null, 12);
        tree.right = new Node(null, null, 65);
        tree.right.left = new Node(null, null, 36);
        tree.right.right = new Node(null, null, 345);
        // tree.right.right.left = new Node(null, null, 2);

        //DFSTree.printDepthInOrder(tree);

        System.out.println(DFSTree.isBST(tree, Optional.empty(), Optional.empty()));
        DFSTree.kthLargest(tree, 2, 0);
    }

    static public class Node{
        Node left;
        Node right;
        int element;
        public Node(Node left, Node right, int element){
            this.left = left;
            this.right = right;
            this.element = element;
        }
    }

    public static void printLevelOrder(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            // Step 1
            Node current = queue.poll();
            // Step 2
            System.out.print(" " + current.element);
            // Step 3
            if (current.left != null) {
                queue.add(current.left);
            }
            // Step 4
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    public static void printDepthInOrderRecursive(Node node) {
        if (node != null) {
            printDepthInOrderRecursive(node.left);
            System.out.print(" " + node.element);
            printDepthInOrderRecursive(node.right);
        }
    }
    public static void printDepthPreOrder(Node node) {
        Stack<Node> s = new Stack<>();
        s.add(node);
        while (s.isEmpty() == false) {
            Node x = s.pop();
            if(x.right!=null) s.add(x.right);
            if(x.left!=null) s.add(x.left);
            System.out.print(" " + x.element);
        }
    }

    public static void printDepthInOrder(Node node) {
        Stack<Node> s = new Stack<>();
        Node curr = node;

        // traverse the tree
        while (curr != null || s.size() > 0) {
            /* Reach the left most Node of the
            curr Node */
            while (curr !=  null) {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                s.push(curr);
                curr = curr.left;
            }

            /* Current must be NULL at this point */
            curr = s.pop();
            System.out.print(curr.element + " ");
            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
            curr = curr.right;
        }
    }


    public static boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        }
        return (Math.abs(height(root.left) - height(root.right)) <= 1);
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static boolean isBST(Node root, Optional<Integer> min, Optional<Integer> max) {
        if (root == null) {
            return true;
        }
        if ((max.isPresent() && root.element > max.get() )
                || (min.isPresent() && root.element <= min.get())){
            return false;
        }
        return (root.left == null || isBST(root.left, min, Optional.of(root.element)))
                && (root.right == null || isBST(root.right, Optional.of(root.element), max));
    }

    public static int kthLargest(Node root, int k, int c) {
        if (root == null || c >= k) {
            return c;
        }
        c = kthLargest(root.right, k, c);
        c++;
        // we found the kth largest value
        if (c == k) {
            System.out.println(root.element);
        }
        return kthLargest(root.left, k, c);
    }
}
