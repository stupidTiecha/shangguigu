package com.tiehca.apitest.heshang.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 让jackson 日期返回时间毫秒数
 * @author chen9
 */
@Configuration
public class JacksonDateParseConfig implements WebMvcConfigurer {

    @Bean
    ObjectMapper objectMapper (Jackson2ObjectMapperBuilder builder) {
        ObjectMapper mapper = builder.createXmlMapper(false).build();
        ObjectMapper objectMapper = mapper.setDateFormat(new SimpleDateFormat() {
            @Override
            public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
                return toAppendTo.append(date.getTime());
            }

        });


        return objectMapper;

    }
}
