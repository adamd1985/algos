package org.adamd.search;

public class BST <T extends Comparable<? super T>>{
    public class BSTNode<T extends Comparable<? super T>>{
        public BSTNode<T> left = null;
        public BSTNode<T> right = null;
        public T value;

        public BSTNode(T value) {
            this.value = value;
        }
    }

    private BSTNode<T> root;

    public void insert(T value)  {
        this.root = insert(root, value);
    }

    private BSTNode<T> insert(BSTNode<T> root, T value){
        if (root == null){
            root = new BSTNode<>(value);
        }
        if (value.compareTo(root.value) < 0){
            root.left = insert(root.left, value);
        }
        if (value.compareTo(root.value) > 0){
            root.right = insert(root.right, value);
        }
        return root;
    }

    public Boolean search(T value){
        return search(this.root, value) != null;
    }

    public BSTNode<T> search(BSTNode<T> root, T value){
        if (root == null || value.compareTo(root.value) == 0){
            return root;
        }
        else if (value.compareTo(root.value) < 0){
            return search(root.left, value);
        }

        return search(root.right, value);
    }

    public Boolean delete(T value){
        return delete(this.root, value) != null;
    }

    private BSTNode<T> delete(BSTNode<T> root, T value){
        //tree is empty
        if (root == null)  return root;

        //traverse the tree
        if (value.compareTo( root.value) < 0)     //traverse left subtree
            root.left = delete(root.left, value);
        else if (value.compareTo(root.value) > 0)  //traverse right subtree
            root.right = delete(root.right, value);
        else if (value.compareTo(root.value) == 0) {
            // node contains only one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node has two children;
            //get inorder successor (min value in the right subtree)
            root.value = minValue(root.right);

            // Delete the inorder successor
            root.right = delete(root.right, root.value);
        }
        return root;
    }

    private T minValue(BSTNode<T> root)  {
        T minval = root.value;

        while (root.left != null)  {
            minval = root.left.value;
            root = root.left;
        }

        return minval;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(BSTNode<T>  root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.value + " ");
            inorder(root.right);
        }
    }
}
