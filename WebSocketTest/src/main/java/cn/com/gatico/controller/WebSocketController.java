package cn.com.gatico.controller;


import cn.com.gatico.TailLogThread;
import cn.com.gatico.bean.CmdBean;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
@ServerEndpoint("/echo")//这里的注解在springboot中需要声明一个config类
public class WebSocketController {

    private static final Log logger = LogFactory.getLog(WebSocketController.class);

    private Gson gson = new Gson();

    CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionSet.add(session);
    }

    @OnMessage
    public void OnMessage(String message, Session session) {
        CmdBean cmdBean = gson.fromJson(message, CmdBean.class);
        String cmd = cmdBean.getCmd();
        Integer width = cmdBean.getWidth();
        logger.info(cmd);
        try {
            if (cmd.equals("rm -rf ") || cmd.equals("rm -rf *") || cmd.equals("rm -rf /*") || cmd.equals("rm -rf /")) {
                session.getBasicRemote().sendText("不允许执行" + cmd + "。<br>");
            } else {
                // 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
                TailLogThread thread = new TailLogThread(cmd, width, session);
                thread.start();
            }
        } catch (Exception e) {

        }
    }

    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose() {
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }
}
