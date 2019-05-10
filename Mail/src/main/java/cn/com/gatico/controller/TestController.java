package cn.com.gatico.controller;


import cn.com.gatico.service.MsUserinfoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    Gson gson;

    @Autowired
    public MsUserinfoService msUserinfoService;

    @RequestMapping(value = "test/index.html", method = RequestMethod.GET)
    public ResponseEntity<Object> test(long id) {
        return ResponseEntity.ok(msUserinfoService.findMsUserinfoEntityById(id));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody Object obj) {
        System.out.println(obj);
        Map map = new HashMap<>();
        map.put("code", "0000");
        map.put("msg", "登录成功");
        return ResponseEntity.ok(gson.toJson(map));
    }
}
