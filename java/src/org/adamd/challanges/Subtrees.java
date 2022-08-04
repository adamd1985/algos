package org.adamd.challanges;

public class Subtrees {
    static public class Node{
        Node l;
        Node r;
        int v;

        public Node(int v) {
            this.v = v;
        }

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
        var root = new Node(40);
        root.l = new Node(30);
        root.l.l = new Node(20);
        root.l.r = new Node(90);
        root.l.l.l = new Node(6);
        root.r = new Node(10);
        root.r.l = new Node(70);
        root.r.r = new Node(80);

        var subtree = new Node(30);
        subtree.l = new Node(20);
        subtree.r = new Node(90);
        subtree.l.l = new Node(6);

       var is = Subtrees.isSubtree(root, subtree);

       System.out.println(is);
    }

    public static boolean isSubtree(Node p, Node q) {
        if (p == null) {
            return false;
        }
        // if the roots don't match
        if (!match(p, q)) {
            return (isSubtree(p.l, q) || isSubtree(p.r, q));
        }
        return true;
    }

    public static boolean match(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        System.out.println(String.format("Matching %d with %d", p.v, q.v));
        return (p.v == q.v
                && match(p.l, q.l)
                && match(p.r, q.r));
    }
}
