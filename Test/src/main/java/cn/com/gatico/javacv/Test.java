package cn.com.gatico.javacv;


import org.bytedeco.javacv.OpenCVFrameGrabber;

public class Test {

    public static void main(String[] args) {
        //新建opencv抓取器，一般的电脑和移动端设备中摄像头默认序号是0，不排除其他情况
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//新建opencv抓取器，一般的电脑和移动端设备中摄像头默认序号是0，不排除其他情况
        Test.class.getClassLoader();
    }


}

