package com.tiehca.apitest.heshang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author chen9
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ShangGuiGuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShangGuiGuApplication.class, args);

    }

}
