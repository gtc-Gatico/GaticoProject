package cn.com.gatico;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.THRESH_TOZERO;
import static org.bytedeco.opencv.global.opencv_imgproc.threshold;

public class GetImage {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\7x-networks\\Documents\\WeChat Files\\wxid_3kar8fnl4s3722\\FileStorage\\Video\\2021-12\\";
        String filePath = path + "edfd1025382439d5e1e0d4558243fc7c.mp4";
        BufferedImage buffer = getBufferedImageByFrame(10, filePath);
        ImageIO.write(buffer, "jpg", new File(path + "\\" + System.currentTimeMillis() + ".jpg"));
    }

    /**
     * 获取指定帧数的封面图片
     *
     * @param frameNum 帧数
     * @param filePath 文件所在路径
     */
    public static BufferedImage getBufferedImageByFrame(int frameNum, String filePath) throws IOException {
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(filePath);
        return getBufferedImageByFrame(frameNum, grabber);
    }

    private static BufferedImage getBufferedImageByFrame(int frameNum, FFmpegFrameGrabber grabber)
            throws FrameGrabber.Exception {
        grabber.start();
        Frame frame;
        int i = 0;
        int fps = (int) grabber.getFrameRate();
        BufferedImage buffer = null;
        while (i < grabber.getLengthInFrames()) {
            frame = grabber.grabImage();
            System.out.println(i+":"+frame.keyFrame);
            if (i >= fps && i % (fps * frameNum) == 0) {
                Java2DFrameConverter converter = new Java2DFrameConverter();
                buffer = converter.getBufferedImage(frame);
                break;
            }
            i++;
        }
        grabber.stop();
        return buffer;
    }
}
