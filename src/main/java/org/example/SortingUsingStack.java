package org.example;

import java.util.Stack;

/*
* Sort a stack using a temporary stack
* */
public class SortingUsingStack {

    public static void main(String[] args) {
        SortingUsingStack ss = new SortingUsingStack();
        Stack<Integer> input = new Stack<Integer>();
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);
        print(ss.sortUsingStack(input));
    }

    private static void print(Stack<Integer> integers) {
        while(!integers.isEmpty()){
            System.out.println(integers.pop());
        }
    }

    public Stack<Integer> sortUsingStack(Stack<Integer> stack){
        Stack<Integer> tempStack = new Stack<>();

        while(!stack.isEmpty()){
            int temp = stack.pop();

            while(!tempStack.isEmpty() && temp > tempStack.peek()){
                stack.push(tempStack.pop());
            }
            tempStack.push(temp);
        }
        return tempStack;
    }
}
