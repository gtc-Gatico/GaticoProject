import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        byteBuffer.putInt(10);
        byteBuffer.putLong(10);
        byteBuffer.putDouble(10.01);
        byteBuffer.flip();
        System.out.println(byteBuffer.limit());
        int a1 = byteBuffer.getInt();
        long b1 = byteBuffer.getLong();
        double c1 = byteBuffer.getDouble();
        System.out.println(a1);
        System.out.println(b1);
        System.out.println(c1);






        exit();

        System.out.println(Integer.MAX_VALUE / Integer.SIZE + 1);


        String str = "switch.if.OperStatus/ifName=Ethernet1/17";
        String[] strs = str.substring(str.indexOf("/") + 1).split(",");
        str = strs[0].substring("ifName=".length());
        System.out.println(str);
        TestThread2 testThread2 = new TestThread2();

        testThread2.start();

        System.out.println("A");

        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():start\"); }");
        m.insertAfter("{ System.out.println(\"Hello.say():end\"); }");
        CtMethod m2 = cc.getDeclaredMethod("main");
        m2.insertAfter("{ System.out.println(\"main start\"); }");
        Class c = cc.toClass();
        Hello h = (Hello) c.newInstance();
        h.say();

        h.main(args);


    }

    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> integerList = new LinkedList<Integer>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

            }
        }


        return new ArrayList<Integer>(1);
    }


    @Test
    public void TestStr() {
        String s1 = new String("a");
        String s2 = new String("a");
        System.out.println("====:" + (s1 == s2));
        System.out.println("equals:" + s1.equals(s2));
        System.out.println();
        Long l1 = new Long(100);
        Long l2 = new Long(100);
        System.out.println("l1==l2:" + (l1 == l2));
        System.out.println("l1.longvalue==l2.longvale:" + (l1.longValue() == l2.longValue()));
        System.out.println("l1.equals(l2):" + (l1.equals(l2)));
    }

    @Test
    public void TestThread() {
        Thread t1 = new Thread(new Runnable() {
            public void start() {
                System.out.println("A");
            }

            @Override
            public void run() {
                System.out.println("B");
            }
        });
        t1.start();
        System.out.println("C");
    }

    @Test
    public void download() throws Exception {
        String urlStr = "https://abcdowndomail.baiyuanshop.cn/data/attachment/o_1es78lmt31b3t6bqlp386arha9.apk";
//        urlStr="https://www.baidu.com";
        URL url = new URL(urlStr);
        URLConnection conn = url.openConnection();
        InputStream inStream = conn.getInputStream();
        System.out.println(inStream.available());
    }

    @Test
    public void createCode() throws Exception {
        Random random = new Random();
        int code = random.nextInt(10000);
        int width = 400, height = 400;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setFont(new Font("Colonna MT", Font.PLAIN, 20));
        String codeValue = String.valueOf(code);
        System.out.println(codeValue);
        //AffineTransform affineTransform = new AffineTransform();
        //affineTransform.rotate(Math.toRadians(45));
        // affineTransform.scale(2,2);
        //g2d.setTransform(affineTransform);
        g2d.scale(2, 2);
        g2d.translate(100, 20);
        g2d.rotate(Math.toRadians(45));
        for (int i = 0; i < codeValue.length(); i++) {
            g2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g2d.drawString(String.valueOf(codeValue.charAt(i)), i * 10, 0);
        }
        g2d.finalize();
        bufferedImage.flush();
        ImageIO.write(bufferedImage, "png", new File("D:\\code.png"));


    }

    @Test
    public void test() {
        int a = Integer.MAX_VALUE;
        System.out.println(a);
        a++;
        System.out.println(a);
        System.out.println(Integer.MIN_VALUE);
    }

    public static void main1(String[] args) throws Exception {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        pipedOutputStream.connect(pipedInputStream);
        new Thread(() -> {
            try {
                String str;
                FileWriter fileWriter = new FileWriter("D:\\Notepad++\\piptest.txt");
                byte[] arr = new byte[1024];
                int i = -1;
                while ((i = pipedInputStream.read(arr)) > 0) {
                    fileWriter.append(new String(arr, 0, i));
                    fileWriter.flush();
                }
                fileWriter.close();
                pipedInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
//            while (true) {
            try {
                String str = "";
                //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(pipedOutputStream));
                Document doc = Jsoup.connect("http://top.baidu.com/buzz.php?p=top10").get();
                Elements titles = doc.select(".list-title");
                titles.forEach(element -> {
                    if (element.text() != null && element.text().length() > 0) {
                        byte arr[] = (element.text() + "\n").getBytes();
                        try {
                            pipedOutputStream.write(arr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                pipedOutputStream.write("\n".getBytes());
                pipedOutputStream.flush();
                pipedOutputStream.close();
                Thread.sleep(1000L * 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            }
        }).start();

    }

    public static void main3(String[] args) throws Exception {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        pipedOutputStream.connect(pipedInputStream);
        new Thread(() -> {
            try {
                String str;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pipedInputStream));
                while ((str = bufferedReader.readLine()) != null) {
                    System.out.println("管道读取的内容：" + str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                String str;
                Scanner scanner = new Scanner(System.in);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(pipedOutputStream));
                System.out.println("请输入内容：");
                while (scanner.hasNext()) {
                    str = scanner.next();
                    if (str.equals("q")) {
                        System.exit(1);
                    }
                    char arr[] = (str + System.lineSeparator()).toCharArray();
                    bufferedWriter.write(arr);
                    bufferedWriter.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Test
    public void getHtml() throws Exception {
        String keyWorks = URLDecoder.decode("海阔天空");
        Document document = Jsoup.connect("https://complexsearch.kugou.com/v2/search/song?callback=callback123&keyword=" + keyWorks + "&page=1&pagesize=30&bitrate=0&isfuzzy=0&tag=em&inputtype=0&platform=WebFilter&userid=-1&clientver=2000&iscorrection=1&privilege_filter=0&srcappid=2919&clienttime=1611547095586&mid=1611547095586&uuid=1611547095586&dfid=-&signature=80E9F497C47C7B88628EAEB54027639B").get();
        Elements songList = document.getElementsByClass("song_list");
        songList.forEach(element -> {
            if (element.text() != null && element.text().length() > 0) {
                System.out.println(element.text());
            }
        });
    }

    @Test
    public void proxy() throws Exception {
        SocketAddress proxyAddress = new InetSocketAddress("127.0.0.1", 8080);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddress);
        Socket s = new Socket(proxy);
        SocketAddress remote = new InetSocketAddress("gatico.com.cn", 80);
        s.connect(remote);
    }


    @Test
    public String getAddress(String lng, String lat) {
        lng = "122.063306";
        lat = "37.207071";
        String urlString = "http://api.map.baidu.com/geocoder/v2/?ak=pWNVQZQIhhhtdXhgxdBKtoMxhMFNhWPC&callback=renderReverse&location=" + lat + "," + lng;
        String res = "";
        BufferedReader in = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static void exit(){
        System.exit(1);
    }
}
