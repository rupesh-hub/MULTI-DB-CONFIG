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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*MYSQL DATABASE*/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mySqlEntityManagerFactory",
        transactionManagerRef = "mySqlTransactionManager",
        basePackages = {
                "com.rupesh.app.user.repositories",
                "com.rupesh.app.role.repositories"
        }
)
public class MySqlConfiguration {

    //DataSource4
    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    //EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(
            final EntityManagerFactoryBuilder factoryBuilder) {

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return factoryBuilder
                .dataSource(mySqlDataSource())
                .packages("com.rupesh.app.user.entities",
                        "com.rupesh.app.role.entities")
                .properties(properties)
                .build();
    }

    //TransactionManager
    @Bean
    public PlatformTransactionManager mySqlTransactionManager(
            @Qualifier("mySqlEntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
