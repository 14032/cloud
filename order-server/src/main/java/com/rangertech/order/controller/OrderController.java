package com.rangertech.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.rangertech.common.vo.ResultVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @GetMapping(value = "init", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVO<JSONObject> init() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service", "order-server");
        jsonObject.put("name", "订单服务");
        jsonObject.put("date", new Date());
        return ResultVO.ok(jsonObject);
    }

}
