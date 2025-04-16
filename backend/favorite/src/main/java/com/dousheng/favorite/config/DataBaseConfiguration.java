package com.dousheng.favorite.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.dousheng.favorite.common.database.InsertBatchSqlInjector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库持久层配置类
 */
@Configuration(value = "dataBaseConfigurationByVideo")
public class DataBaseConfiguration {

    /**
     * 分页插件
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptorByAdmin() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 自定义批量插入 SQL 注入器
     */
    @Bean
    public InsertBatchSqlInjector insertBatchSqlInjector() {
        return new InsertBatchSqlInjector();
    }
}
