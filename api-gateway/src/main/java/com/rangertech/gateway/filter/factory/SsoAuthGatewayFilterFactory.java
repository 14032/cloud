package com.rangertech.gateway.filter.factory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rangertech.common.enums.ServerErrorCode;
import com.rangertech.common.utils.JwtTokenUtil;
import com.rangertech.gateway.exception.BizException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import java.util.*;

@Slf4j
public class SsoAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<SsoAuthGatewayFilterFactory.Config> {

    public static final String ENABLED_KEY = "enabled";

    public SsoAuthGatewayFilterFactory() {
        super(Config.class);
        log.info("Loaded SsoAuthGatewayFilterFactory [SsoAuth]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(ENABLED_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            String token = exchange.getRequest().getHeaders().getFirst("token");
            if (StringUtils.isEmpty(token)) {
                throw new BizException(ServerErrorCode.AUTHENTICATE_FAILED, "token为空");
            }

            Claims claims = JwtTokenUtil.getClaimFromToken(token);
            if (Objects.requireNonNull(claims).getExpiration().before(new Date())) {
                throw new BizException(ServerErrorCode.AUTHENTICATE_FAILED, "登录信息过期，请重新登陆");
            }
            try {
                Object user = claims.get("user");
                Map<String, String> map = JSONObject.parseObject(JSONObject.toJSONString(user), new TypeReference<Map<String, String>>() {
                });
                String userId = map.get("userId");
                if (userId == null) {
                    throw new BizException(ServerErrorCode.AUTHENTICATE_FAILED, "登录信息过期，请重新登陆");
                }
                return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().build()).build());
            } catch (BizException e) {
                throw new BizException(e.getCode(), e.getMessage());
            } catch (Exception e) {
                log.info("SsoAuth error, message is {}", e.getMessage());
                throw new BizException(ServerErrorCode.AUTHENTICATE_FAILED);
            }
        };
    }

    public static class Config {

        private boolean enabled;

        public Config() {
        }

        boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
