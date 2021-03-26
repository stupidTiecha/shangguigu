package com.tiehca.apitest.heshang;

import com.tiehca.apitest.heshang.common.filter.FileValidateFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author chen9
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan(basePackageClasses = {FileValidateFilter.class})
public class ShangGuiGuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShangGuiGuApplication.class, args);

    }

}
