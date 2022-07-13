package com.rupesh.app.config.db;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*
 * POSTGRE DATABASE
 * */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgreEntityManagerFactory",
        transactionManagerRef = "postgreTransactionManager",
        basePackages = "com.rupesh.app.product.repositories"
)
public class PostgreConfiguration {

    //DataSource
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "postgres.datasource")
    public DataSource postgreDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    //EntityManagerFactory
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean postgreEntityManagerFactory(
            final EntityManagerFactoryBuilder factoryBuilder) {

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");

        return factoryBuilder
                .dataSource(postgreDataSource())
                .packages("com.rupesh.app.product.entities")
                .properties(properties)
                .build();
    }

    //TransactionManager
    @Bean
    @Primary
    public PlatformTransactionManager postgreTransactionManager(
            @Qualifier("postgreEntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
