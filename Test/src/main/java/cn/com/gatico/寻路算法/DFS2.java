package cn.com.gatico.寻路算法;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class DFS2 extends JFrame {
    //0是路，1是墙
    public static int[][] arr = new int[][]{
            //x       0  1  2  3  4  5  //y
            new int[]{3, 0, 0, 0, 0, 0},//0
            new int[]{0, 0, 0, 0, 0, 0},//1
            new int[]{0, 0, 0, 0, 0, 0},//2
            new int[]{0, 0, 0, 0, 0, 0},//3
            new int[]{0, 0, 0, 0, 0, 0},//4
            new int[]{0, 0, 0, 0, 3, 0},//5
    };
    public static LinkedList<RoadNode> openList = new LinkedList<>();
    public static LinkedList<RoadNode> closeList = new LinkedList<>();
    JLabel imageIcon;
    static RoadNode current;
    static boolean endFlag = false;
    static JFrame jFrame;
    static ImageIcon image = new ImageIcon();
    int width = 360, height = 360;

    public DFS2() {
        jFrame = this;
        this.setTitle("A*");
        this.setLayout(null);
        this.setSize(width + 18, height + 40);
        this.setLocationRelativeTo(null);// 设置居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        File img = new File("D:\\mg.png");
//        try {
//            ImageIO.write(bufferedImage, "png", new File("D:\\mg.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        imageIcon = new JLabel(new ImageIcon(getImage(width, height)), JLabel.CENTER);
        imageIcon.setBounds(0, 0, width, height);
        this.add(imageIcon);
        this.setVisible(true);

    }


    public static void main(String[] args) {
        DFS2 dfs2 = new DFS2();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int i1 = random.nextInt(6);
            int j1 = random.nextInt(6);
            if (arr[i1][j1] != 3) {
                arr[i1][j1] = 1;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("x[" + i + "]y[" + j + "] " + arr[i][j] + "\t");
            }
            System.out.println();
        }

        RoadNode startNode = new RoadNode();
        startNode.point = new Point(0, 0);
        startNode.loss = 0;
        openList.add(startNode);
        RoadNode endNode = new RoadNode();
        endNode.point = new Point(5, 4);
        do {
            current = getNext(openList);
            if (current == null) {
                endFlag = true;
                break;
            }
            openList.remove(current);
            closeList.add(current);

            LinkedList<RoadNode> roadNodes = getOtherNode(current);
            Iterator<RoadNode> iterator1 = roadNodes.iterator();
            while (iterator1.hasNext()) {
                RoadNode roadNode = iterator1.next();
                if (arr[roadNode.point.x][roadNode.point.y] == 1) {
                    closeList.add(roadNode);
                }
                if (!isInList(closeList, roadNode)) {
                    if (!isInList(openList, roadNode)) {
                        roadNode.father = current;
                        roadNode.loss = getLoss(roadNode, endNode) + getLoss(roadNode, current);
                        openList.add(roadNode);
                    }
                }
            }
        } while (!isEnd(current, endNode));

        image.setImage(getImage(dfs2.width, dfs2.height));
        dfs2.imageIcon.setIcon(image);
        dfs2.myUpdateUI();
        if (endFlag) {
            System.out.println("未找到路径");
            //dfs2.imageIcon.imageUpdate(getImage(dfs2.width, dfs2.height),0, 0, 0, dfs2.width, dfs2.height);
            JOptionPane.showConfirmDialog(null, "未找到路径", "提示", JOptionPane.PLAIN_MESSAGE);
            dfs2.myUpdateUI();
        } else {
            while (current.father != null) {
                System.out.print("x[" + current.point.x + "],y[" + current.point.y + "],loss["+current.loss+"]\t\t");
                if(arr[current.point.x][current.point.y]!=3){
                    arr[current.point.x][current.point.y] = 2;
                }
                image.setImage(getImage(dfs2.width, dfs2.height));
                dfs2.imageIcon.setIcon(image);
                //dfs2.imageIcon.imageUpdate(getImage(dfs2.width, dfs2.height),0, 0, 0, dfs2.width, dfs2.height);
                dfs2.myUpdateUI();
                current = current.father;
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("x[" + i + "]y[" + j + "] " + arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int getLoss(RoadNode startNode, RoadNode endNode) {
        return Math.max(startNode.point.x, endNode.point.x) - Math.min(startNode.point.x, endNode.point.x) + Math.max(startNode.point.y, endNode.point.y) - Math.min(startNode.point.y, endNode.point.y);
    }

    public static boolean isInList(LinkedList<RoadNode> roadNodes, RoadNode roadNode) {
        Iterator<RoadNode> iterator = roadNodes.iterator();
        while (iterator.hasNext()) {
            RoadNode next = iterator.next();
            if (next.point.x == roadNode.point.x && next.point.y == roadNode.point.y) {
                return true;
            }
        }
        return false;
    }

    public static LinkedList<RoadNode> getOtherNode(RoadNode roadNode) {
        LinkedList<RoadNode> roadNodeList = new LinkedList<>();
        RoadNode top = new RoadNode();
        top.point = new Point(roadNode.point.x - 1, roadNode.point.y);
        roadNodeList.add(top);
        RoadNode right = new RoadNode();
        right.point = new Point(roadNode.point.x, roadNode.point.y + 1);
        roadNodeList.add(right);
        RoadNode bottom = new RoadNode();
        bottom.point = new Point(roadNode.point.x + 1, roadNode.point.y);
        roadNodeList.add(bottom);
        RoadNode left = new RoadNode();
        left.point = new Point(roadNode.point.x, roadNode.point.y - 1);
        roadNodeList.add(left);
        Iterator<RoadNode> iterator = roadNodeList.iterator();
        while (iterator.hasNext()) {
            RoadNode next = iterator.next();
            if (next.point.x < 0 || next.point.y < 0 || next.point.x >= arr.length || next.point.y >= arr[0].length) {
                iterator.remove();
            }
        }
        return roadNodeList;

    }

    public static RoadNode getNext(LinkedList<RoadNode> roadNodes) {
        if (roadNodes.isEmpty()) {
            return null;
        }
        return roadNodes.stream().min(Comparator.comparingInt(o -> o.loss)).get();
    }

    public static boolean isEnd(RoadNode currentNode, RoadNode endNode) {
        if (currentNode.point.getX() == endNode.point.getX() && currentNode.point.getY() == endNode.point.getY()) {
            return true;
        }
        return false;
    }

    public static BufferedImage getImage(int width, int height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.BLACK);
        for (int x = 0; x <= width; x += 60) {
            g2d.drawLine(0, x, height, x);
        }
        g2d.drawLine(0, height - 1, width - 1, height - 1);
        for (int y = 0; y <= height; y += 60) {
            g2d.drawLine(y, 0, y, width);
        }
        g2d.drawLine(width - 1, 0, width - 1, height - 1);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(j * 60, i * 60, 60, 60);
                }
                if (arr[i][j] == 2) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(j * 60, i * 60, 60, 60);
                }
                if (arr[i][j] == 3) {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(j * 60, i * 60, 60, 60);
                }
            }
        }
        bufferedImage.flush();
        return bufferedImage;
    }

    public void myUpdateUI() {
        SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后,更新窗口

    }
}

class RoadNode {

    public Point point;//坐标
    public RoadNode father;//上级
    public int loss;//损失数值

}



