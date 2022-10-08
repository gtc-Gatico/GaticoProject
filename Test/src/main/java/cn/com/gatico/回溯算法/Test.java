package cn.com.gatico.回溯算法;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
    /*
    * func(input) {
            helper(input, target, 0);
      }

    helper(input, target, pos){
        isInvalid();      // 是否无效
        isEnd();          // 是否终止条件
        for (...) {       // 开始循环遍历
           add();         // 做个正标记（入栈、加列表、正flag）
           helper(input, target', pos');   // 递归下一个
           remove();      // 做个反标记（出栈、移除列表、反flag）
        }
    }
    * */

    public static void main(String[] args) {
        char[][] board = new char[][]{
                new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},     //["5","3","4","6","7","8","9","1","2"],
                new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},     //["6","7","2","1","9","5","3","4","8"],
                new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},     //["1","9","8","3","4","2","5","6","7"],
                new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},     //["8","5","9","7","6","1","4","2","3"],
                new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},     //["4","2","6","8","5","3","7","9","1"],
                new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},     //["7","1","3","9","2","4","8","5","6"],
                new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},     //["9","6","1","5","3","7","2","8","4"],
                new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},     //["2","8","7","4","1","9","6","3","5"],
                new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}};    //["3","4","5","2","8","6","1","7","9"]
        int x = 0, y = 0;
        start(board, x, y);
        System.out.println(map);
    }

    static Map<Point, Integer> map = new HashMap<>();

    public static void start(char[][] board, int x, int y) {
        map.put(new Point(x, y), 1);
        board[x][y] = ("" + 1).charAt(0);
        if (!isEnd(board)) {
            map.entrySet().stream().iterator().forEachRemaining(point -> {
                for (int i = 1; i <= 9; i++) {
                    if (check(board, point.getKey().x, point.getKey().y)) {
                        start(board, point.getKey().x, point.getKey().y);
                    } else {
                        board[point.getKey().x][point.getKey().y] = '.';
                        map.remove(point.getKey());
                    }
                }
            });
        }
    }

    public static boolean isEnd(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean check(char[][] board, int x, int y) {
        if (checkRow(board, x)) {
            if (checkColumn(board, y)) {
                return true;
//                if (checkSquare(board, x, y)) {
//                    return true;
//                }
            }
        }

        return false;
    }

    public static boolean checkRow(char[][] board, int x) {
        Set<Character> set = new HashSet<>();
        int c = 0;
        for (int j = 0; j < board[x].length; j++) {
            if (board[x][j] != '.') {
                set.add(board[x][j]);
                c++;
            }
        }
        if (set.size() != c) {
            return false;
        }
        return true;
    }

    public static boolean checkColumn(char[][] board, int y) {
        Set<Character> set = new HashSet<>();
        int c = 0;
        for (int j = 0; j < board[y].length; j++) {
            if (board[j][y] != '.') {
                set.add(board[j][y]);
                c++;
            }
        }
        if (set.size() != c) {
            return false;
        }
        return true;
    }

    public static boolean checkSquare(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] != '.') {
                    set.add(board[j][i]);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
