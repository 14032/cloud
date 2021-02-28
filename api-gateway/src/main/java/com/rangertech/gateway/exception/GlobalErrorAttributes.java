package com.rangertech.gateway.exception;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.rangertech.common.enums.ErrorAttributeKey;
import com.rangertech.common.enums.ResponseEnum;
import com.rangertech.common.enums.ResultEnum;
import com.rangertech.common.enums.ServerErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    public GlobalErrorAttributes() {
        super(false);
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        Map<String, Object> map = new HashMap<>(10);

        map.put(ResponseEnum.url.name(), request.uri().getPath());
        Throwable error = super.getError(request);
        log.error(error.getMessage() == null ? "网关异常" : error.getMessage(), error);

        if (error instanceof BizException) {
            BizException serverException = (BizException) error;
            errorAttributes.put(ErrorAttributeKey.retcode.name(), serverException.getCode());
            errorAttributes.put(ErrorAttributeKey.msg.name(), serverException.getMessage());
            if (serverException.getCode().equals(ServerErrorCode.AUTHENTICATE_FAILED.getCode())) {
                errorAttributes.put(ErrorAttributeKey.timestamp.name(), System.currentTimeMillis());
            }
        } else if (error instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) error;
            errorAttributes.put(ErrorAttributeKey.retcode.name(), responseStatusException.getStatus().value());
            errorAttributes.put(ErrorAttributeKey.msg.name(), responseStatusException.getMessage());
        } else if (error instanceof TimeoutException) {
            errorAttributes.put(ErrorAttributeKey.retcode.name(), ServerErrorCode.REQUEST_FUSE_ERROR.getCode());
            errorAttributes.put(ErrorAttributeKey.msg.name(), ServerErrorCode.REQUEST_FUSE_ERROR.getName());
        } else if (error instanceof IllegalStateException) {
            throw new BizException(ResultEnum.SUCCESS);
        } else if (error instanceof HystrixRuntimeException) {
            errorAttributes.put(ErrorAttributeKey.retcode.name(), ServerErrorCode.SERVER_CALL_FAILS.getCode());
            errorAttributes.put(ErrorAttributeKey.msg.name(), ServerErrorCode.SERVER_CALL_FAILS.getName());
            map.put("error", error.getMessage());
        } else {
            errorAttributes.put(ErrorAttributeKey.retcode.name(), ServerErrorCode.INTERNAL_GATEWAY_ERROR.getCode());
            errorAttributes.put(ErrorAttributeKey.msg.name(), ServerErrorCode.INTERNAL_GATEWAY_ERROR.getName());
        }
        errorAttributes.put(ErrorAttributeKey.data.name(), new JSONObject(map));
        return errorAttributes;
    }
}
