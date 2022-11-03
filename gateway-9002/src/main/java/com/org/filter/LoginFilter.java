package com.org.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class LoginFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {

    public static final List<String> ALLOW_URL = Arrays.asList("/user/login");
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //针对请求的过滤，拿到请求header url等参数
        //HttpServerRequest是web里的
        //ServerHttpRequest是webFlux里面的响应式里的
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getURI().getPath();
        //如果是登录则放行
        if(ALLOW_URL.contains(path)) {
            return chain.filter(exchange);
        }
        //不然先从请求头查有没有token，跟redis里比对并且辨别有没有失效
        HttpHeaders headers = request.getHeaders();
        List<String> authorization = headers.get("Authorization");
        if(!CollectionUtils.isEmpty(authorization)) {
            String token = authorization.get(0);        //可能会有多个但通常token放第一个
            if(StringUtils.hasText(token)) {            //如果token不为空
                String realToken = token.replaceFirst("bearer ", "");   //将约定好的前缀去掉，如果有的话
                if(redisTemplate.hasKey(realToken)) {       //如果redis里有
                    return chain.filter(exchange);
                }
            }
        }

        //若不符合以上条件则返回相应相关数据
        ServerHttpResponse response = exchange.getResponse();
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytes = new byte[0];
            try {
                bytes = objectMapper.writeValueAsBytes(
                        new Result().setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("未授权")
                );
            } catch (JsonProcessingException e) {}
            // 通过buffer工厂将字节数组包装成一个数据包
            DataBuffer wrap = response.bufferFactory().wrap(bytes);

        return response.writeWith(Mono.just(wrap));
    }

    @Override
    public int getOrder() {             //过滤器执行顺序
        return 0;
    }
}
