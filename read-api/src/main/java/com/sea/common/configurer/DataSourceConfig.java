package com.sea.common.configurer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

/**
 * Created by lvhaizhen on 2018/8/17.
 */
@Configuration
@Order(1)
public class DataSourceConfig {

    @Bean(name = "readDataSource")
    @Qualifier("readDataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource getMyDataSource(){
        return DataSourceBuilder.create().build();
    }
}
