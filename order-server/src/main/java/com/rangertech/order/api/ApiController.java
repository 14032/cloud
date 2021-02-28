package com.rangertech.order.api;

import com.rangertech.common.vo.ResultListVO;
import com.rangertech.order.feign.UserFeignService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/order")
public class ApiController {

    @Resource
    private UserFeignService userFeignService;

    @GetMapping(value = "/user/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultListVO<Object> userList() {
        return userFeignService.list();
    }

}
