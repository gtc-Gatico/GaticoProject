package cn.com.gatico;

import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestMiGong {
    static int maze[][] = new int[][]{
            new int[]{0, 1, 0, 0, 0},
            new int[]{0, 1, 0, 1, 0},
            new int[]{0, 0, 0, 0, 0},
            new int[]{0, 1, 1, 1, 0},
            new int[]{0, 0, 0, 1, 0},
    };
    static Point direction[] = new Point[]{
            new Point(0, -1),
            new Point(0, 1),
//            new Point(-1, 0),
//            new Point(1, 0),
    };
    static Map map = new HashMap();
    static Point oldStart = null;

    public static void main(String[] args) {

        Point start = new Point(0, 0);
        Point end = new Point(5, 5);

        ArrayList<Object> objects = new ArrayList<>();
        startRun(objects,start, end);
        System.out.println(objects.toString());
    }

    public static void startRun(ArrayList list,Point start, Point end) {
        if (start.x >= end.x && start.y >= end.y) {
            list.add("end");
        }
        list.add(start);
        if (checkPointIsRun(start)) {
            for (int i = 0; i < direction.length; i++) {
                start.x += direction[i].x;
                start.y += direction[i].y;
                startRun(list, start, end);
                System.out.println(new JSONObject().put(start.toString(),list));
            }
        }

    }

    public static boolean checkPointIsRun(Point p) {
        if (p.x > 5 || p.y > 5 || p.y < 0 || p.x < 0) {
            return false;
        }
        return maze[p.x][p.y] == 0;
    }
}
