package cn.com.gatico.窗体;

import com.sun.imageio.plugins.png.PNGImageWriter;
import com.sun.imageio.plugins.png.PNGImageWriterSpi;
import com.sun.jndi.ldap.BerEncoder;
import com.sun.management.GcInfo;
import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.util.Class2HTML;
import com.sun.org.apache.bcel.internal.util.SecuritySupport;
import javafx.application.Application;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.BCodec;
import org.apache.commons.codec.net.QCodec;
import sun.management.LazyCompositeData;
import sun.management.MemoryUsageCompositeData;

import javax.imageio.IIOImage;
import javax.imageio.spi.ImageWriterSpi;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestJfx extends Application {

    public static void main(String[] args) throws Exception {
       launch(args);
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("你好");
        primaryStage.show();
    }
}
