package cn.com.gatico.回溯算法;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
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


    }

    public boolean check(char[][] board) {

        return true;
    }
    public boolean checkRow(char[][] board){
        for (int i = 0; i < board.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j]!='.'&&set.contains(board[i][j])){
                    set.add(board[i][j]);
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkColumn(char[][] board){
        for (int i = 0; i < board.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                if(board[j][i]!='.'&&set.contains(board[j][i])){
                    set.add(board[j][i]);
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
