package cn.com.gatico.smailRest;

import com.sun.net.httpserver.HttpExchange;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

@Restful(url = "/main")
public class MainRestful extends AbsRestful {

    public String getInfo(String name) {
        System.out.println(MainRestful.class.getSimpleName());
        HttpExchange httpExchange = getHttpExchange();
        InetSocketAddress remoteAddress = httpExchange.getRemoteAddress();
        System.out.print(Thread.currentThread().getName() + "\t");
        System.out.println(remoteAddress.toString());
        Map<String, Object> query = getQuery();
        System.out.println(query.toString());
        if (name.equals("zs")) {
            return "{name:'张三'}";
        }
        return null;
    }

    public byte[] getImg(String name) throws IOException {
        System.out.print(Thread.currentThread().getName() + "\t");
        getHttpExchange().getResponseHeaders().set("Content-Type", "image/png");
        return Files.readAllBytes(Paths.get("F:\\" + name + ".png"));
    }
}
