package com.sarkhan.dtssystem.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.sarkhan.dtssystem.repository.company",
        entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstTransactionManager"
)

public class CompanyDbConfig {

    @Value("${spring.datasource.first.url}")
    private String firstDbUrl;

    @Value("${spring.datasource.first.username}")
    private String firstDbUsername;

    @Value("${spring.datasource.first.password}")
    private String firstDbPassword;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String firstDbDdlAuto;


    @Bean(name = "firstDataSource")
    public DataSource firstDataSource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url(firstDbUrl);
        builder.username(firstDbUsername);
        builder.password(firstDbPassword);
        return builder.build();
    }


    @Bean(name = "firstEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sfirstEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(firstDataSource())
                .packages("com.sarkhan.dtssystem.model.company")
                .persistenceUnit("first")
                .properties(hibernateProperties())
                .build();
    }

    @Bean(name = "firstTransactionManager")
    public PlatformTransactionManager firstdTransactionManager(
            @Qualifier("firstEntityManagerFactory") EntityManagerFactory firstEntityManagerFactory) {
        return new JpaTransactionManager(firstEntityManagerFactory);
    }

    private Map<String, Object> hibernateProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", firstDbDdlAuto);
        return properties;
    }
}