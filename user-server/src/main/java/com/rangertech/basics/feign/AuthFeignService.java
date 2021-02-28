package com.rangertech.basics.feign;

import com.alibaba.fastjson.JSONObject;
import com.rangertech.common.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "auth-server", path = "/auth", fallbackFactory = AuthFeignServiceImpl.class)
public interface AuthFeignService {

    @GetMapping(value = {"/init"})
    ResultVO<JSONObject> init();

}