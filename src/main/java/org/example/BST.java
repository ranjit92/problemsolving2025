package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

    private Node root;

    public static class Node {
        int val;
        int height;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public int height(Node node){
        if(node == null){
            return -1;
        }
        return node.height;
    }

    public void insert(int val){

        root = insert(val, root);
    }

    private Node insert(int val, Node node){

        if(node == null){
            Node newNode = new Node(val);
            return newNode;
        }

        if(val < node.val){
            node.left = insert(val, node.left);
        }
        if(val > node.val){
            node.right = insert(val, node.right);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public boolean balanced(){
        return balanced(root);
    }

    private boolean balanced(Node node){
        if(node == null){
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    public void display(){
        display(this.root, "This is root node");
    }

    private void display(Node node, String msg){
        if(node == null){
            return;
        }
        System.out.println(msg + ": "+ node.val + " and height is: " + node.height);
        display( node.left, "Left child of" + ": " + node.val + " is ");
        display( node.right, "Right child of" + ": " + node.val + " is ");
    }


    public void populate(int[] arr){
        for (int j : arr) {
            insert(j);
        }
    }

    public void setRoot(int val){
        this.root = new Node(val);
    }

    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }


    public void levelOrder(){
        System.out.println("Starting level order traversal");
        levelOrder(root);
    }

    private void levelOrder(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){

            Node qn = queue.poll();
            System.out.println(qn.val);
            if(qn.left != null){
                queue.add(qn.left);
            }
            if(qn.right != null){
                queue.add(qn.right);
            }
        }
    }
}
