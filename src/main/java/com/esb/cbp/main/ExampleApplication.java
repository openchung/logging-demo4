/**
* 程式資訊摘要：
* 類別名稱：ExampleApplication.java
* 程式內容說明：
* 版本資訊：
* 程式設計人員姓名：Sam,Chen
* 程式修改記錄：2018年4月23日
* 版權宣告：
*/
package com.esb.cbp.main;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.esb.common.logger.EsunLogger;

/**
 * @author sam.xps
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackages = { "com.esb.common", "com.esb.cbp.controller" })
@ImportResource(locations = { "classpath:applicationContext.xml" })
public class ExampleApplication extends SpringBootServletInitializer {

    private final static Logger log = EsunLogger.create(ExampleApplication.class);

    @PreDestroy
    private static void destory() {
        log.info("--- End of onDestory ExampleApplication ---");
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new  SimpleClientHttpRequestFactory();
//        requestFactory.setConnectTimeout(1000);
//        requestFactory.setReadTimeout(1000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
