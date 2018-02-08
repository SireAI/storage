package com.sire.storage.ModuleEnvironment.MVCConfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * ==================================================
 * All Right Reserved
 * Date:2018/1/3
 * Author:Sire
 * Description:
 * ==================================================
 */
@Configuration
public class FileConfig {
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);// resolveLazily属性启用是为了推迟文件解析
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(2 * 1024 * 1024);// 上传文件大小 2M 50*1024*1024
        return resolver;
    }
}
