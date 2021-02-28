package com.rangertech.basics.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rangertech.basics.feign.AuthFeignService;
import com.rangertech.common.vo.ResultListVO;
import com.rangertech.common.vo.ResultVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private AuthFeignService authFeignService;

    @GetMapping(value = "init", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVO<JSONObject> init() {
        JSONObject data = new JSONObject();
        data.put("service", "user-server");
        data.put("name", "用户服务");
        data.put("date", new Date());
        return ResultVO.ok(data);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultListVO<Object> userList() {
        JSONArray data = new JSONArray();
        JSONObject u1 = new JSONObject();
        u1.put("name", "Istio");
        JSONArray istio = new JSONArray();
        istio.add("Connect");
        istio.add("Secure");
        istio.add("Control");
        istio.add("Obeserve");

        u1.put("description",istio);

        JSONObject u2 = new JSONObject();
        u2.put("name", "Kubernetes");

        JSONArray k8s = new JSONArray();
        k8s.add("Automated rollouts and rollbacks");
        k8s.add("Service discovery and load balancing");
        k8s.add("Horizontal scaling");
        k8s.add("Self-healing");

        u2.put("description", k8s);
        JSONObject u3 = new JSONObject();

        ResultVO<JSONObject> resultVO = authFeignService.init();
        u3.put("name", "Feign");
        u3.put("description", resultVO.getData());

        data.add(u1);
        data.add(u2);
        data.add(u3);
        return ResultListVO.ok(data);
    }

}
