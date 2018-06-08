package com.ztes.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 如果使用war包则要继承SpringBootServletInitializer并且重写configure方法，见注释
 */
@SpringBootApplication
@EnableTransactionManagement
public class SellApplication {
//public class SellApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SellApplication.class, args);
	}

	/*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SellApplication.class);
    }*/
}
