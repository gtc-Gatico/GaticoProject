package cn.com.gatico;

import org.bytedeco.opencv.opencv_core.Mat;
import static org.bytedeco.opencv.global.opencv_highgui.*;//包含了所有图形接口函数
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*; //COLOR_RGB2GRAY

//得到灰度图像
public class Demo1 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\7x-networks\\Pictures\\cxk.png";
        Mat image = imread(filePath); // 加载图像
        imshow("1", image);// 原始图像
        Mat gray = new Mat();
        cvtColor(image, gray, COLOR_BGRA2GRAY); // 彩色图像转为灰度图像COLOR_RGB2GRAY
        imshow("2", gray);// 灰度图像
        Mat bin = new Mat();
        threshold(gray, bin, 120, 255, THRESH_TOZERO); // 图像二值化
        imshow("3", bin);// 二值图像
        waitKey(0);
    }
}