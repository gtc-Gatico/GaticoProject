package cn.com.gatico.controller;

import cn.com.gatico.service.TestService;
import com.d2rabbit.exception.annotation.CheckExceptionx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private Environment environment;

    @Autowired
    private TestService service;

    @Value("${test.port}")
    private int testPort;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> Test(@RequestHeader("Access-Key") String accessKey,
                                       @RequestHeader("Request-ID") String requestId,
                                       @RequestHeader("Sign-Method") String signMethod,
                                       @RequestHeader("Signature") String signature,
                                       @RequestHeader("Timestamp") Long timestamp,
                                       @RequestHeader("Version") String Version,
                                       HttpServletRequest httpRequest,
                                       @RequestParam("file") MultipartFile multipartFile) {
        System.out.println(accessKey);
        System.out.println(requestId);
        System.out.println(signMethod);
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(Version);
        System.out.println(multipartFile.getOriginalFilename());
        List<Map<String, String>> listMap = new LinkedList<>();
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            Map<String, String> map = new HashMap<>();
            String element = headerNames.nextElement();
            System.out.print(element + ":");
            String header = httpRequest.getHeader(element);
            System.out.println(header);
            map.put(element, header);
            listMap.add(map);
        }
        return ResponseEntity.ok(listMap);

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> listAlarmRecords(Integer interval) {
        System.out.println(testPort);
        System.out.println(System.getProperty("testIp"));
        System.out.println(service.getRes());
        return ResponseEntity.ok(environment.getProperty("test.ip"));
    }

}
