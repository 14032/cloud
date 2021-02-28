package com.rangertech.basics.feign;

import com.alibaba.fastjson.JSONObject;
import com.rangertech.common.vo.ResultVO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthFeignServiceImpl implements FallbackFactory<AuthFeignService> {

    @Override
    public AuthFeignService create(Throwable throwable) {
        return new AuthFeignService() {

            @Override
            public ResultVO<JSONObject> init() {
                return null;
            }
        };
    }
}
