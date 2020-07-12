package com.yfs.es.train.estrain.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Map<String, Object> index() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("code", 200);
        return result;
    }
}
