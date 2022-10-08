package cn.com.gatico;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_videoio.CvCapture;
import sun.net.www.http.HttpClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;

public class Test {
    public static void main(String[] args) throws Exception, InterruptedException{


//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(1);//新建opencv抓取器，一般的电脑和移动端设备中摄像头默认序号是0，不排除其他情况
//        grabber.start();//开始获取摄像头数据
        String path = "C:\\Users\\7x-networks\\Documents\\WeChat Files\\wxid_3kar8fnl4s3722\\FileStorage\\Video\\2021-12\\";
        String filePath = path + "edfd1025382439d5e1e0d4558243fc7c.mp4";
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://192.168.1.102:8081/live.flv")
//                .method("GET", null)
//                .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
//                .build();
//        Response response = client.newCall(request).execute();
        FFmpegFrameGrabber ff =new FFmpegFrameGrabber(filePath);
        CanvasFrame canvas = new CanvasFrame("摄像头预览");//新建一个预览窗口
        canvas.setSize(300,300);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        ff.setFormat("flv");
        ff.start();
        //窗口是否关闭
        int i= 0;
        System.out.println(System.currentTimeMillis());
        while(canvas.isDisplayable()){
            /*获取摄像头图像并在窗口中显示,这里Frame frame=grabber.grab()得到是解码后的视频图像*/
            Frame frame = ff.grab();
            if(frame!=null){
                canvas.showImage(frame);
            }else {
                System.out.println(System.currentTimeMillis());
                break;
            }
        }
        ff.close();//停止抓取

    }
}
