package cn.com.gatico;

import cn.com.gatico.controller.WebSocketController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.io.InputStream;

public class TailLogThread extends Thread {
    private static final Log logger = LogFactory.getLog(WebSocketController.class);
    private String command;
    private int width;
    private Session session;

    public TailLogThread(String cmd, int width, Session session) {
        this.command = cmd;
        this.width = width;
        this.session = session;

    }

    @Override
    public void run() {
        logger.info(command);
        try {
            Process exec = Runtime.getRuntime().exec(command, new String[]{""});
            InputStream is = exec.getInputStream();
            InputStream es = exec.getErrorStream();
            try {
                byte[] arr = new byte[width];
                while ((is.read(arr)) != -1) {
                    session.getBasicRemote().sendText(new String(arr) + "<br>");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
                if (es != null) {
                    is.close();
                }
            } catch (Exception ex) {
                logger.error(ex);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
