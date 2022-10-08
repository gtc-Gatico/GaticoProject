package cn.com.gatico.回溯算法;

public class Test2 {
    // 1 2 3 4 5 6 7 8
    // 1 2 3 4 5 6 7 8
    // 1 2 3 4 5 6 7 8
    // 1 2 3 4 5 6 7 8
    // 1 2 3 4 5 6 7 8
    // 1 2 3 4 5 6 7 8
    // 1 2 3 4 5 6 7 8
    // 1 2 3 4 5 6 7 8
    static int[][] queen = new int[8][8];
    static int count = 0;

    public static void main(String[] args) {
        find(0);
    }

    public static void find(int row) {
        for (int i = 0; i < queen[row].length; i++) {
            if (check(row, i)) {
                queen[row][i] = 1;
                if (row == queen[row].length - 1) {
                    show();
                } else {
                    find(row + 1);
                }
                queen[row][i] = 0;
            }
        }
    }

    public static void show() {
        System.out.println("-------------------" + ++count + "-------------------");
        for (int i = 0; i < queen.length; i++) {
            for (int j = 0; j < queen[i].length; j++) {
                System.out.print(queen[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean check(int r, int c) {
        // 判断当前位置的上面是否有皇后
        for (int i = r - 1; i >= 0; i--) {
            if (queen[i][c] == 1) return false;
        }

        //判断左上是否有皇后
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (queen[i][j] == 1) return false;
        }

        //判断右上是否有皇后
        for (int i = r - 1, j = c + 1; i >= 0 && j < queen[r].length; i--, j++) {
            if (queen[i][j] == 1) return false;
        }
        return true;
    }

}
