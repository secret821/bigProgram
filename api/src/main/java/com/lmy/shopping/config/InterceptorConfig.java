package com.lmy.shopping.config;

import com.lmy.shopping.interceptor.CheckTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 关于拦截器注册
 *
 * @Author LMY
 * @Date 2022/3/1 19:55
 * @Version V1.0
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkTokenInterceptor)
                .addPathPatterns("/shoppingCart/**")
                .addPathPatterns("/useraddr/**")
                .addPathPatterns("/order/**")
                .addPathPatterns("/user/userTokenCheck")
                .excludePathPatterns("/order/listOrder")
                .excludePathPatterns("/order/confirmOrderStatus")
                .excludePathPatterns("/order/listOrderItem");
    }
}
