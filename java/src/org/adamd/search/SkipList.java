package org.adamd.search;

import java.util.Arrays;
import java.util.random.RandomGenerator;


public class SkipList<T extends Comparable<? super T>> {
    final int LEVELS = 4;

    /**
     * Node artefact having the item to search and the next lists, unless its the bottom list.
     * @param <T>
     */
    public class SkipNode<T extends Comparable<? super T>>{

        public SkipNode<T>[] next = new SkipNode[LEVELS];
        public T data;

        public SkipNode(T data){
            this.data = data;
            Arrays.fill(this.next, 0, LEVELS,  null);
        }

        public SkipNode<T> getNext(int level) {
            return this.next[level];
        }

        void setNext(SkipNode<T> next, int level) {
            this.next[level]=next;
        }

        void refreshAfterDelete(int level) {
            SkipNode<T> current = this;
            while (current != null && current.getNext(level) != null) {
                if (current.getNext(level).data == null) {
                    SkipNode<T> successor = current.getNext(level).getNext(level);
                    current.setNext(successor, level);
                    return;
                }

                current = current.getNext(level);
            }
        }

        public SkipNode<T> search(T data, int level){
            System.out.print("Searching for: " + data + " at ");
            print(level);

            SkipNode<T> result = null;
            SkipNode<T> current = this.getNext(level);
            while (current != null && current.data.compareTo(data) < 1) {
                if (current.data.equals(data)) {
                    result = current;
                    break;
                }

                current = current.getNext(level);
            }

            return result;
        }

        public void insert(SkipNode<T> skipNode, int level) {
            SkipNode<T> current = this.getNext(level);
            if (current == null) {
                this.setNext(skipNode, level);
                return;
            }
            if (skipNode.data.compareTo(current.data) < 1) {
                this.setNext(skipNode, level);
                skipNode.setNext(current, level);
                return;
            }
            while (current.getNext(level) != null && current.data.compareTo(skipNode.data) < 1 &&
                    current.getNext(level).data.compareTo(skipNode.data) < 1) {
                current = current.getNext(level);
            }

            SkipNode<T> successor = current.getNext(level);
            current.setNext(skipNode, level);
            skipNode.setNext(successor, level);
        }

        void print(int level) {
            System.out.print("level " + level + ": [ ");
            int length = 0;
            SkipNode<T> current = this.getNext(level);
            while (current != null) {
                length++;
                System.out.print(current.data + " ");
                current = current.getNext(level);
            }

            System.out.println("], length: " + length);
        }
    }


    private final SkipNode<T> head = new SkipNode<>(null);
    private final RandomGenerator rand = RandomGenerator.getDefault();

    public void insert(T data) {
        SkipNode<T> skipNode = new SkipNode<>(data);
        for (int i = 0; i < LEVELS; i++) {
            if (rand.nextInt((int) Math.pow(2, i)) == 0) {
                insert(skipNode, i);
            }
        }
    }

    public boolean delete(T target) {
        System.out.println("Deleting " + target);
        SkipNode<T> victim = search(target);
        if (victim == null) return false;
        victim.data = null;

        for (int i = 0; i < LEVELS; i++) {
            head.refreshAfterDelete(i);
        }

        System.out.println("deleted...");
        return true;
    }

    private void insert(SkipNode<T> SkipNode, int level) {
        head.insert(SkipNode, level);
    }

    public SkipNode<T> search(T data) {
        SkipNode<T> result = null;
        for (int i = LEVELS-1; i >= 0; i--) {
            if ((result = head.search(data, i)) != null) {
                System.out.println("Found " + data.toString() + " at level " + i + ", so stopped" );
                System.out.println();
                break;
            }
        }

        return result;
    }

    public void print() {
        for (int i = LEVELS-1; i >= 0 ; i--) {
            head.print(i);
        }
        System.out.println();
    }
}
