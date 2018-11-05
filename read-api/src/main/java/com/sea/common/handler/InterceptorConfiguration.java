package com.sea.common.handler;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lvhaizhen on 2018/7/25.
 */
@SpringBootConfiguration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";


    @Autowired
    private BackendLoginHandler backendLoginHandler;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(backendLoginHandler);
        super.addInterceptors(registry);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Bean
    public HttpMessageConverter fastJsonConfigure(){
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //日期格式化
        //fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(fastJsonConfig);
        return converter;
    }

    @Bean
    public Converter<String,Date> stringToDateConvert(){
        return new Converter<String, Date>() {
            @Override
            public Date convert(String value) {
                if(StringUtils.isEmpty(value)) {
                    return null;
                }

                value = value.trim();

                try {

                    if(NumberUtils.isCreatable(value)){
                        return new Date(NumberUtils.toLong(value));
                    }
                    if(value.contains("-")) {
                        SimpleDateFormat formatter;
                        if(value.contains(":")) {
                            formatter = new SimpleDateFormat(dateFormat);
                        }else {
                            formatter = new SimpleDateFormat(shortDateFormat);
                        }

                        Date dtDate = formatter.parse(value);
                        return dtDate;
                    }else if(value.matches("^\\d+$")) {
                        Long lDate = new Long(value);
                        return new Date(lDate);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(String.format("parser %s to Date fail", value));
                }
                throw new RuntimeException(String.format("parser %s to Date fail", value));
            }
        };
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
        converters.add(fastJsonConfigure());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToDateConvert());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
