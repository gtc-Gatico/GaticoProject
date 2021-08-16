package cn.com.gatico;

import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static void main(String[] args) {
        JNIDemo j = new JNIDemo();
        j.testHello();
        System.out.println(j.add(1,2));
        System.out.println(j.plus(3,2));

        new HashMap();

        new LinkedBlockingQueue<>();

        Stack<Integer> stack = new Stack<>();
        stack.push(6);
        Integer six = stack.pop();
    }
}
