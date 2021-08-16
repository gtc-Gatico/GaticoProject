package cn.com.gatico.寻路算法;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class DFS {
    public static int[][] arr = new int[][]{
            new int[]{1, 1, 1, 1, 1, 1},
            new int[]{0, 0, 1, 0, 0, 1},
            new int[]{1, 1, 1, 1, 1, 1},
            new int[]{1, 0, 0, 1, 0, 0},
            new int[]{1, 0, 0, 1, 0, 0},
            new int[]{1, 1, 1, 1, 0, 0},
    };
    public static int oldDirection = 0;

    public static void main(String[] args) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("x[" + i + "]y[" + j + "] " + arr[i][j] + "\t");
            }
            System.out.println();
        }
        Map map = new LinkedHashMap();

        LinkedList linkedList = new LinkedList();
        Point start = new Point(0, 0);
        System.out.println("开始坐标x[" + start.x + "],y[" + start.y + "]");
        Point end = new Point(5, 0);
        System.out.println("结束坐标x[" + end.x + "],y[" + end.y + "]");
        Point current = new Point(start);
        //0上，1下，2左，3右
        int nextDirection = -1;
        linkedList.add(new Point(current.x, current.y));
        boolean endFlag = false;
        while (!endFlag && (current.x != end.x || current.y != end.y)) {
            int[] tmp = getTmp(current);
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] == 1) {
                    nextDirection = i;
                }
            }
            if (nextDirection == 0) {
                current.x--;
                oldDirection = 1;
            }
            if (nextDirection == 1) {
                current.x++;
                oldDirection = 0;
            }
            if (nextDirection == 2) {
                current.y--;
                oldDirection = 3;
            }
            if (nextDirection == 3) {
                current.y++;
                oldDirection = 2;
            }
            if (nextDirection == -1) {
                endFlag = true;
            } else {
                linkedList.add(new Point(current.x, current.y));
            }
            nextDirection = -1;
        }
        System.out.println("一共" + linkedList.size() + "步");
        for (int i = 0; i < linkedList.size(); i++) {
            Point o = (Point) linkedList.get(i);
            if (i % 5 == 0) {
                System.out.println();
            }
            System.out.print("第" + (i + 1) + "步 x[" + o.x + "],y[" + o.y + "]\t\t");
        }
        if (endFlag) {
            System.out.println("未找到完整路径");
        }
    }

    public static int[] getTmp(Point current) {
//        int[] tmp = new int[]{
//                arr[current.x - 1][current.y], //上
//                arr[current.x + 1][current.y], //下
//                arr[current.x][current.y - 1], //左
//                arr[current.x][current.y + 1], //右
//        };
        int[] tmp = new int[4];
        if (current.x - 1 < 0 || oldDirection == 0) {
            tmp[0] = 0;
        } else {
            tmp[0] = arr[current.x - 1][current.y];
        }
        if (current.x + 1 >= arr.length || oldDirection == 1) {
            tmp[1] = 0;
        } else {
            tmp[1] = arr[current.x + 1][current.y];
        }
        if (current.y - 1 < 0 || oldDirection == 2) {
            tmp[2] = 0;
        } else {
            tmp[2] = arr[current.x][current.y - 1];
        }
        if (current.y + 1 >= arr[current.x].length || oldDirection == 3) {
            tmp[3] = 0;
        } else {
            tmp[3] = arr[current.x][current.y + 1];
        }
        return tmp;
    }
}
