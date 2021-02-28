package com.rangertech.order.feign;

import com.rangertech.common.vo.ResultListVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-server", path = "/user", fallbackFactory = UserFeignServiceImpl.class)
public interface UserFeignService {

    @GetMapping(value = {"/list"})
    ResultListVO<Object> list();

}