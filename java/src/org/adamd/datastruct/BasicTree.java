package org.adamd.datastruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicTree<T> {
    public T value;

    private final ArrayList<BasicTree<T>> leaves = new ArrayList<>();
    private BasicTree<T> parent = null;
    private HashMap<T, BasicTree<T>> locate = new HashMap<>();

    public BasicTree(T head) {
        this.value = head;
        locate.put(head, this);
    }

    public void addLeaf(T root, T leaf) {
        if (locate.containsKey(root)) {
            var rootNode = locate.get(root);
            rootNode.addLeaf(leaf);
        } else {
            addLeaf(root).addLeaf(leaf);
        }
    }

    public BasicTree<T> addLeaf(T leaf) {
        BasicTree<T> t = new BasicTree<T>(leaf);
        leaves.add(t);
        t.parent = this;
        t.locate = this.locate;
        locate.put(leaf, t);
        return t;
    }

    public BasicTree<T> setAsParent(T parentRoot) {
        BasicTree<T> t = new BasicTree<T>(parentRoot);
        t.leaves.add(this);
        this.parent = t;
        t.locate = this.locate;
        t.locate.put(value, this);
        t.locate.put(parentRoot, t);
        return t;
    }

    public T getValue() {
        return value;
    }

    public BasicTree<T> getTree(T element) {
        return locate.get(element);
    }

    public BasicTree<T> getParent() {
        return parent;
    }

    public Collection<T> getSuccessors(T root) {
        Collection<T> successors = new ArrayList<T>();
        BasicTree<T> tree = getTree(root);
        if (null != tree) {
            for (BasicTree<T> leaf : tree.leaves) {
                successors.add(leaf.value);
            }
        }
        return successors;
    }

    public Collection<BasicTree<T>> getSubTrees() {
        return leaves;
    }

    public void printAll(){
        var level = 0;
        Stack<BasicTree<T>> stack = new Stack<>();
        stack.push(this);
        while (!stack.empty()) {
            var node = stack.pop();
            var tabs = Stream.generate(() -> "\t")
                    .limit(node.getHeight(this))
                    .collect(Collectors.joining());

            System.out.println("%s%d".formatted(tabs, node.value));
            if (node.leaves != null) {
                stack.addAll(node.leaves);
            }
        }
    }

    public int getHeight(BasicTree<T> parent){
        int height = 0;
        BasicTree<T> curRootNode = this;

        do {
            if (curRootNode == parent){
                break;
            }
            ++height;
            curRootNode = curRootNode.parent;
        } while (curRootNode != null);

        return  height;
    }

    public class Corners<T> {
        BasicTree<T> left;
        BasicTree<T> right;

        public Corners(BasicTree<T> left, BasicTree<T> right) {
            this.left = left;
            this.right = right;
        }
    }

    public Corners<T> getCorners(BasicTree<T> root) {
        var dist = 0;
        var left = root;
        var right = root;

        Deque<BasicTree<T> > q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            if (n > dist){
                dist =  n;
                left = q.peekFirst();
                right = q.peekLast();
            }
            for(int i = 0 ; i < n ; i++){
                BasicTree<T>  temp = q.poll();
                if (temp.leaves != null) {
                    q.addAll(temp.leaves);
                }
            }
        }

        return new Corners<T>(left, right);
    }

}
