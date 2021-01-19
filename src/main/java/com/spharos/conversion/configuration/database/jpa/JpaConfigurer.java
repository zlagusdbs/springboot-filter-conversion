package com.spharos.conversion.configuration.database.jpa;

import com.spharos.conversion.configuration.database.DatabaseInfoVendor;
import com.spharos.conversion.configuration.database.DatabaseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(value = {"com.spharos.conversion.repository"})
@EnableTransactionManagement
public class JpaConfigurer {
    @Bean(name = "jpaDataSource")
    public DataSource jpaDataSource(@Autowired DatabaseInfoVendor.DataSourceProperties dataSourceProperties) {
        return DatabaseUtility.makeDataSource(dataSourceProperties);
    }

    @Bean(name = "jpaProperties")
    public Properties jpaProperties(@Autowired DatabaseInfoVendor.JpaProperties jpaProperties) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", jpaProperties.getProperties().getHibernate().getDialect());
        properties.setProperty("hibernate.hbm2ddl.auto", jpaProperties.getProperties().getHibernate().getHbm2ddl().getAuto());
        properties.setProperty("hibernate.show_sql", jpaProperties.getProperties().getHibernate().getShowSql());
        properties.setProperty("hibernate.hbm2ddl.format_sql", jpaProperties.getProperties().getHibernate().getFormatSql());

        return properties;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(value = "jpaDataSource") DataSource dataSource,
            @Qualifier(value = "jpaProperties") Properties properties
    ) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        localContainerEntityManagerFactoryBean.setPersistenceUnitName("conversion");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]{"com.spharos.conversion"});
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier(value = "entityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);

        return jpaTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
