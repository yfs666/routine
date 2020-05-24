package com.yfs.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//@Controller
//@RequestMapping("/my")
public class MyController {
    @ResponseBody
    @RequestMapping(value = "/yfs2", method = RequestMethod.GET)
    public Object yfs2() {
        Map<String,String> json = new HashMap<>(2);
        json.put("aaa","aaa");
        json.put("bbb","bbb");
        return json;
    }
}
