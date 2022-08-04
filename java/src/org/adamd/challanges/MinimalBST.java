package org.adamd.challanges;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MinimalBST {
    static public class Node{
        Node l;
        Node r;
        int v;
        public Node(Node l, Node r, int v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "l=" + (l != null ? Integer.toString(l.v) : "") +
                    ", r=" + (r != null ? Integer.toString(r.v) : "") +
                    ", v=" + v +
                    '}';
        }
    }
    public static void main(String[] args) {
        int[] array = {-2, 3, 4, 6, 7, 8, 12, 23, 90};
        var tree = MinimalBST.bstify(array, 0, array.length - 1);

        MinimalBST.bfsPrint(tree);

        var allnodes = MinimalBST.fetchAllLevels(tree);
        allnodes.stream().forEach(System.out::println);
    }

    public static List<List<Node>> fetchAllLevels(Node root) {
        // each list holds a level
        List<List<Node>> allLevels = new ArrayList<>();

        // first level (containing only the root)
        Queue<Node> currentLevelOfNodes = new ArrayDeque<>();
        currentLevelOfNodes.add(root);
        while (!currentLevelOfNodes.isEmpty()) {
            // store the current level as the previous level
            Queue<Node> previousLevelOfNodes = currentLevelOfNodes;
            // add level to the final list
            allLevels.add(currentLevelOfNodes.stream().toList());
            // go to the next level as the current level
            currentLevelOfNodes = new ArrayDeque<>();
            // traverse all nodes on current level
            for (Node parent : previousLevelOfNodes) {
                if (parent.l != null) {
                    currentLevelOfNodes.add(parent.l);
                }
                if (parent.r != null) {
                    currentLevelOfNodes.add(parent.r);
                }
            }
        }
        return allLevels;
    }

    public static void dfsPreOrderPrint(Node parent) {
        if (parent != null){
            System.out.print(parent.v + " ");
            dfsPreOrderPrint(parent.l);
            dfsPreOrderPrint(parent.r);
        }
    }

    public static void bfsPrint(Node parent) {
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.offer(parent);
        while (!nodes.isEmpty()){
            var node =  nodes.poll();
            System.out.println(node.v);
            if (node.l != null) nodes.offer(node.l);
            if (node.r != null) nodes.offer(node.r);
        }
    }

    public static Node bstify(int[] array, int startIdx, int endIdx){
        if (startIdx <= endIdx) {
            var midIdx = (endIdx + startIdx) / 2;
            System.out.println(String.format("l(%d::%d) p(%d::%d) r(%d::%d)",
                    startIdx, array[startIdx], midIdx, array[midIdx], endIdx, array[endIdx]));
            var node = new Node(
                    bstify(array, startIdx, midIdx - 1),
                    bstify(array, midIdx + 1, endIdx),
                    array[midIdx]
            );

            return node;
        }

        return null;
    }
}
