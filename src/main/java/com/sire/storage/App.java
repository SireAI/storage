package com.sire.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/8/3
 * Author:Sire
 * Description:
 * ==================================================
 */
@SpringBootApplication()
@EnableWebMvc
@EnableTransactionManagement
@MapperScan({"com.sire.storage.FileStorageMofule.Dao"
        })
@RestController
@EnableScheduling
@EnableCaching
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @RequestMapping("/")
    public String test() {
        return "hello sire! ";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
}
