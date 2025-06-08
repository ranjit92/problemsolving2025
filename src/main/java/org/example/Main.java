package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args){

        //int[] arr = new int[]{1,4,6,8,9,22,34,45};
        //int target = 8;
        //System.out.println(search(arr, target, 0, arr.length - 1));

/*        BinaryTree bt = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        bt.takeRoot(scanner);*/

/*        BST bst = new BST();
        int[] arr = new int[]{6,7,2,10,19,5,67,25,20};
        bst.setRoot(18);
        bst.populate(arr);
        bst.display();
        System.out.println(bst.balanced());
        bst.levelOrder();*/

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add(0, i);
        }
        list.forEach(s -> System.out.println(s));

    }

    public static int search(int[] arr, int target, int start, int end){

        if(start > end){
            return -1;
        }
        int mid = (start + end)/2;
        if(arr[mid] == target){
            return mid;
        }
        if(target < arr[mid]){
            return search(arr, target, start, mid - 1);
        }
        return search(arr, target, mid + 1, end);
    }
}

