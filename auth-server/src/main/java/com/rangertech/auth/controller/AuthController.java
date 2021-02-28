package com.rangertech.auth.controller;

import com.rangertech.common.vo.ResultVO;
import org.springframework.http.MediaType;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @GetMapping(value = "init", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVO<JSONObject> init() {
        JSONObject data = new JSONObject();
        data.put("service", "auth-server");
        data.put("name", "认证服务");
        data.put("date", new Date());
        return ResultVO.ok(data);
    }

}
