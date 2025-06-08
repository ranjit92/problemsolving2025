package org.example;


import java.util.Scanner;

public class BinaryTree {

    private Node root;

    public void takeRoot(Scanner scanner){
        System.out.println("Enter the root: ");
        root = new Node(scanner.nextInt());

        populateNodes(scanner, root);
        display();
    }

    public void populateNodes(Scanner scanner, Node node){

        System.out.println("Do you want to enter left of: " + node.val);
        boolean isLeft = scanner.nextBoolean();

        if(isLeft){
            System.out.println("Please Insert the Value left of : " + node.val);
            int val = scanner.nextInt();
            node.left = new Node(val);
            populateNodes(scanner, node.left);
        }

        System.out.println("Do you want to enter right of: " + node.val);
        boolean isRight = scanner.nextBoolean();

        if(isRight){
            System.out.println("Please Insert the Value right of : " + node.val);
            int val = scanner.nextInt();
            node.right = new Node(val);
            populateNodes(scanner, node.right);
        }
    }

    public void display(){
        display(this.root, "");
    }

    private void display(Node node, String intendation){

        if(node == null){
            return;
        }

        System.out.println(intendation + node.val);
        display(node.left, intendation + "  ");
        display(node.right, intendation + "  ");
    }

    public static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
