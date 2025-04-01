package com.trollMarket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class    MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("").setViewName("forward:/dashboard/index");
        registry.addViewController("/dashboard").setViewName("forward:/dashboard/index");
        registry.addViewController("/profile").setViewName("forward:/profile/index");
        registry.addViewController("/shop").setViewName("forward:/shop/index");
        registry.addViewController("/merchandise").setViewName("forward:/merchandise/index");
        registry.addViewController("/shipper").setViewName("forward:/shipper/index");
        registry.addViewController("/cart").setViewName("forward:/cart/index");
        registry.addViewController("/admin").setViewName("forward:/admin/index");
        registry.addViewController("/history").setViewName("forward:/history/index");
    }
}
