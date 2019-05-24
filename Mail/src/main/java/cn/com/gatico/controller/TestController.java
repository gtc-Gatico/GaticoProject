package cn.com.gatico.controller;


import cn.com.gatico.TEST.DataEntity;
import cn.com.gatico.TEST.TestRRD;
import cn.com.gatico.entity.PerformanceComputeEntity;
import cn.com.gatico.entity.PerformanceLinkQualityEntity;
import cn.com.gatico.service.MsUserinfoService;
import cn.com.gatico.service.PerformanceComputeService;
import cn.com.gatico.service.PerformanceLinkQualityService;
import cn.com.gatico.service.PostgresTestService;
import com.google.gson.Gson;
import org.rrd4j.core.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    Gson gson;

    @Autowired
    public MsUserinfoService msUserinfoService;

    @Autowired
    public PerformanceComputeService performanceComputeService;

    @Autowired
    public PerformanceLinkQualityService performanceLinkQualityService;

    @Autowired
    public PostgresTestService postgresTestService;

    @RequestMapping(value = "test/index.html", method = RequestMethod.GET)
    public ResponseEntity<Object> test(String startstr, String endstr) throws Exception {
        Timestamp start = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(startstr).getTime());
        Timestamp end = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(endstr).getTime());
        List<PerformanceComputeEntity> data = performanceComputeService.listBySamplingTimeBetween(start, end);

        return ResponseEntity.ok(data);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody Object obj) {
        System.out.println(obj);
        Map map = new HashMap<>();
        map.put("code", "0000");
        map.put("msg", "登录成功");

        return ResponseEntity.ok(gson.toJson(map));
    }

    @RequestMapping(value = "/postgresTest", method = RequestMethod.GET)
    public ResponseEntity<Object> postgresTest() {

        return ResponseEntity.ok(gson.toJson(postgresTestService.findAll()));
    }

    @RequestMapping(value = "testrrdData", method = RequestMethod.GET)
    public ResponseEntity<Object> testRRdData(String start, String end) {
        Timestamp startt = new Timestamp(0);
        Timestamp endt = new Timestamp(0);
        try {
            startt = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(start).getTime());
            endt = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(end).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long START = startt.getTime();
        long END = endt.getTime();

        List<PerformanceLinkQualityEntity> linkQualityEntityList = performanceLinkQualityService.findByLinkIdAndSamplingTimeBetween(17, startt, endt);
        return ResponseEntity.ok(linkQualityEntityList);
    }

    @RequestMapping(value = "testrrd", method = RequestMethod.GET)
    public ResponseEntity<Object> testRRd(long linkid, String start, String end) {
        Timestamp startt = new Timestamp(0);
        Timestamp endt = new Timestamp(0);
        try {
            startt = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(start).getTime());
            endt = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(end).getTime());
            System.out.println(startt.getTime());
            System.out.println(endt.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long START = startt.getTime() / 1000;
        long END = endt.getTime() / 1000;
        System.out.println(START);
        System.out.println(END);
        String file_path = Util.getRrd4jDemoPath("RRDTEST" + (START + "" + END) + ".rrd");

        List<PerformanceLinkQualityEntity> linkQualityEntityList = performanceLinkQualityService.findByLinkIdAndSamplingTimeBetween(linkid, startt, endt);

        TestRRD testRRD = new TestRRD();
        DataEntity dataEntity = new DataEntity();
        dataEntity.setTime(START);
        dataEntity.setDataSources(new String[]{"Latency", "LossRate"});
        testRRD.file_path = file_path;
        testRRD.dataEntity = dataEntity;
        testRRD.createRrd(dataEntity,END);
        for (int i = 0; i < linkQualityEntityList.size(); i++) {
            PerformanceLinkQualityEntity performanceLinkQualityEntity = linkQualityEntityList.get(i);
            DataEntity dataEntity1 = new DataEntity();
            dataEntity1.setTime(performanceLinkQualityEntity.getSamplingTime().getTime() / 1000);
            int latency =performanceLinkQualityEntity.getLatency();
            dataEntity1.setValues(new double[]{latency, performanceLinkQualityEntity.getLossRate()});
            testRRD.addOrUpdateRrdResource(dataEntity1);
        }
        System.out.println(linkQualityEntityList.size());
        String imgpath = file_path.replace(".rrd", (START + "_" + END) + ".png");

        testRRD.showRrdGraph("Latency(ms)/LossRate(%)",imgpath, START, END);
        HttpHeaders headers = new HttpHeaders();
        byte[] content = null;
        try {
            File imgfile = new File(imgpath);
            FileInputStream fis = new FileInputStream(imgfile);
            content = new byte[(int) imgfile.length()];
            try {
                fis.read(content);
                headers.setContentDispositionFormData("attachment", imgfile.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            headers.setContentType(MediaType.IMAGE_PNG);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(content, headers, HttpStatus.CREATED);
    }

}
