package otus.hibernate;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import otus.config.IocConfig;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@Import(IocConfig.class)
public class DbConfig {

    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String[] PACKAGES_TO_SCAN = {"otus"};

    @Value("${jdbc.driver}")
    private String driver;
    @Value("{jdbc.url}")
    private String url;
    @Value("{jdbc.username}")
    private String username;
    @Value("{jdbc.password}")
    private String password;
    @Value("{hibernate.dialect}")
    private String dialect;
    @Value("{hibernate.show_sql}")
    private String showSql;
    @Value("{hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driver);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);

        return driverManagerDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
        localContainerEntityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROP_HIBERNATE_DIALECT, dialect);
        properties.put(PROP_HIBERNATE_SHOW_SQL, showSql);
        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, hbm2ddl);
        return properties;
    }

}
