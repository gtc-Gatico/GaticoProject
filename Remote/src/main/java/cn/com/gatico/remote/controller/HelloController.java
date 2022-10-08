package cn.com.gatico.remote.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api")
public class HelloController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<Object> hello() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("");
        return ResponseEntity.ok(stringBuffer);
    }
}
