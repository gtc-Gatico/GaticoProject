package cn.com.gatico;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        while(!stack.empty()){
            Object pop = stack.pop();
            System.out.println(pop);
        }

    }
}
