package com.yang.controller;

import com.yang.util.ApiService;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.MDC;
//import org.slf4j.LoggerFactory;

//test github
//test github
//test github

@Controller
@RequestMapping
public class IndexController {


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Logger log = Logger.getLogger(this.getClass().getName());
    // private Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    ApiService apiService;

    @RequestMapping("/index")
    public String list(HttpServletRequest request) {
        return "index";
    }


    @RequestMapping("/test")
    @ResponseBody
    public Map<String, String> test(String hostPort) {
        if (hostPort != null && hostPort.length() != 0) {
            if (hostPort.split(":")[0].split("\\.").length == 4) return test1(hostPort);
        }
        return test2();
    }

    private Map<String, String> test1(String hostPort) {
        log.info("继续请求远程应用：" + hostPort);
        try {
            String res = apiService.doGet("http://" + hostPort + "/web/test");
            log.info("继续请求结果：" + res);
            log.info("PtxId: " + MDC.get("PtxId"));
            log.info("PspanId: " + MDC.get("PspanId"));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("state", "success " + sdf.format(new Date()));
        dataMap.put("msg", (String) MDC.get("PtxId"));
        return dataMap;
    }

    public Map<String, String> test2() {
        log.info("直接处理请求！");
        log.info("PtxId: " + MDC.get("PtxId"));
        log.info("PspanId: " + MDC.get("PspanId"));
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("state", "success " + sdf.format(new Date()));
        dataMap.put("msg", (String) MDC.get("PtxId"));
        return dataMap;
    }

}
