package hr.fer.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


//@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "hr.fer")
@PropertySource(value = {"classpath:application.properties"})
@EnableJpaRepositories(basePackages = "hr.fer.repository")
@EnableTransactionManagement
public class PlatformConfig {

    private final Environment env;

    @Autowired
    public PlatformConfig(Environment env) {
        //constants in src/main/resources/application.properties
        this.env = env;
    }

    @Bean //connection with database via jdbc driver manager
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        ds.setUrl(env.getRequiredProperty("jdbc.url"));
        ds.setUsername(env.getRequiredProperty("jdbc.username"));
        ds.setPassword(env.getRequiredProperty("jdbc.password"));
        return ds;
    }

    /////////////// JPA Interface ////////////////////////

    @Bean //mapping: object <==> table
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setJpaProperties(this.hibernateProperties()); // Where are the mappings
        emfb.setPersistenceProvider(this.persistenceProvider()); // who will map
        emfb.setDataSource(this.dataSource()); // Where are the tables
        emfb.setPackagesToScan("hr.fer.model"); // Where are the POJOs
        return emfb;
    }

    @Bean //transactions (needed for commiting in db)
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(this.entityManagerFactory().getObject());
    }

    /////////////// Hibernate Implementation /////////////

    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        props.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        props.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        return props;
    }

    private HibernatePersistenceProvider persistenceProvider() {
        return new HibernatePersistenceProvider();
    }

    /////////////// REST Additional Configuration ////////

    @Bean //validation messages
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        return resource;
    }

}