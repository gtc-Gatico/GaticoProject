package cn.com.gatico.smailRest;

import java.net.InetSocketAddress;
import java.util.Map;

@Restful(url = "/main")
public class MainRestful extends AbsRestful {

    public Object getInfo(String name) {
        InetSocketAddress remoteAddress = getHttpExchange().getRemoteAddress();
        System.out.print(Thread.currentThread().getName()+"\t");
        System.out.println(remoteAddress.toString());
        Map<String, Object> query = getQuery();
        System.out.println(query.toString());
        if (name.equals("zs")) {
            return "张三";
        }
        return null;
    }

}
