package com.rangertech.order.feign;

import com.rangertech.common.vo.ResultListVO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignServiceImpl implements FallbackFactory<UserFeignService> {

    @Override
    public UserFeignService create(Throwable throwable) {
        return new UserFeignService() {

            @Override
            public ResultListVO<Object> list() {
                return null;
            }
        };
    }
}
