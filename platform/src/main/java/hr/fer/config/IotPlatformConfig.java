package hr.fer.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;


//@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "hr.fer")
@PropertySource(value = {"classpath:application.properties"})
@EnableJpaRepositories(basePackages = "hr.fer.repository")
@EnableTransactionManagement
public class IotPlatformConfig {

    //constants in src/main/resources/application.properties
    private final Environment env;

    @Autowired
    public IotPlatformConfig(Environment env) {
        this.env = env;
    }

    @Bean //connection with db
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean //object <==> table  // mapping
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        Properties hibernateProps = new Properties();
        hibernateProps.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        hibernateProps.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        hibernateProps.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        entityManagerFactoryBean.setJpaProperties(hibernateProps);

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("hr.fer.model");
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());

        return entityManagerFactoryBean;
    }

    @Bean //transactions
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean //validation messages
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        return resource;
    }

}