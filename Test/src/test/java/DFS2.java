import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DFS2 {
    public static int[][] arr = new int[][]{
            new int[]{1, 1, 1, 1, 1, 1},
            new int[]{0, 0, 0, 0, 0, 1},
            new int[]{1, 1, 1, 0, 1, 1},
            new int[]{0, 0, 0, 1, 1, 0},
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

        RoadNode node = new RoadNode();
        Point start = new Point(0, 0);
        Point end = new Point(5, 0);
        node.current = start;
        node.fromDirection = 0;
        find(node, end);

        System.out.println(JSONArray.toJSON(node));

//        System.out.println("一共" + linkedList.size() + "步");
//        for (int i = 0; i < linkedList.size(); i++) {
//            Point o = (Point) linkedList.get(i);
//            if (i % 5 == 0) {
//                System.out.println();
//            }
//            System.out.print("第" + (i + 1) + "步 x[" + o.x + "],y[" + o.y + "]\t\t");
//        }
//        if (map.size() > 1) {
//            System.out.println("未找到完整路径");
//        }
    }

    public static void find(RoadNode roadNode, Point end) {
        if (isEnd(roadNode.current, end)) {
            return;
        }
        System.out.println("开始坐标x[" + roadNode.current.x + "],y[" + roadNode.current.y + "]");
        System.out.println("结束坐标x[" + end.x + "],y[" + end.y + "]");
        //1上，2下，3左，4右
        getTmp(roadNode);
        if (roadNode.top == 1) {
            RoadNode roadNode1 = new RoadNode(roadNode.current);
            roadNode1.current.x--;
            roadNode1.fromDirection = 2;
            roadNode.roadNodeList.add(roadNode1);
        }
        if (roadNode.bottom == 1) {
            RoadNode roadNode1 = new RoadNode(roadNode.current);
            roadNode1.current.x++;
            roadNode1.fromDirection = 1;
            roadNode.roadNodeList.add(roadNode1);
        }
        if (roadNode.left == 1) {
            RoadNode roadNode1 = new RoadNode(roadNode.current);
            roadNode1.current.y--;
            roadNode1.fromDirection = 4;
            roadNode.roadNodeList.add(roadNode1);
        }
        if (roadNode.right == 1) {
            RoadNode roadNode1 = new RoadNode(roadNode.current);
            roadNode1.current.y++;
            roadNode1.fromDirection = 3;
            roadNode.roadNodeList.add(roadNode1);
        }
        for (int i = 0; i < roadNode.roadNodeList.size(); i++) {
            find((RoadNode) roadNode.roadNodeList.get(i), end);
        }
    }

    public static void getTmp(RoadNode roadNode) {
//        int[] tmp = new int[]{
//                arr[current.x - 1][current.y], //上
//                arr[current.x + 1][current.y], //下
//                arr[current.x][current.y - 1], //左
//                arr[current.x][current.y + 1], //右
//        };
        if (roadNode.current.x - 1 < 0 || roadNode.fromDirection == 1) {
            roadNode.top = 0;
        } else {
            roadNode.top = arr[roadNode.current.x - 1][roadNode.current.y];
        }
        if (roadNode.current.x + 1 >= arr.length || roadNode.fromDirection == 2) {
            roadNode.bottom = 0;
        } else {
            roadNode.bottom = arr[roadNode.current.x + 1][roadNode.current.y];
        }
        if (roadNode.current.y - 1 < 0 || roadNode.fromDirection == 3) {
            roadNode.left = 0;
        } else {
            roadNode.left = arr[roadNode.current.x][roadNode.current.y - 1];
        }
        if (roadNode.current.y + 1 >= arr[roadNode.current.x].length || roadNode.fromDirection == 4) {
            roadNode.right = 0;
        } else {
            roadNode.right = arr[roadNode.current.x][roadNode.current.y + 1];
        }
    }

    public static boolean isEnd(Point current, Point end) {
        if (current.getX() == end.getX() && current.getY() == end.getY()) {
            return true;
        }
        return false;
    }
}
