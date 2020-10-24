
package com.digitalinka.restpreventa.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataConfig {

    private static final Logger log = LoggerFactory.getLogger(DataConfig.class);

    @Autowired
    private SessionFactory sessionFactory ;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Configuration
    @Import(PropertyPlaceholderConfig.class)
    static class Production {

        @Value("${hibernate.show_sql}")
        protected String hibernateShowSql;
        @Value("${hibernate.dialect}")
        protected String hibernateDialect;
        @Value("${hibernate.hbm2ddl.auto}")
        protected String hibernateHbm2DDL;
//        @Value("${hibernate.cache.use_second_level_cache}")
//        protected String hibernateSecondLevelCache;
//        @Value("${hibernate.cache.provider_class}")
//        protected String hibernateCacheClass;
//        @Value("${hibernate.default_schema}")
//        protected String hibernateSchema;
        @Value("${jdbc.driverClassName}")
        protected String jdbcDriver;
        @Value("${jdbc.username}")
        protected String jdbcUsername;
        @Value("${jdbc.password}")
        protected String jdbcPassword;
        @Value("${jdbc.url}")
        protected String jdbcUrl;
//        @Value("${hibernate.cache.region.factory_class}")
//        protected  String hibernateCacheRegionFactoryClass;

        @Bean
        public SessionFactory sessionFactory() {

            LocalSessionFactoryBean factoryBean;
            try {
                factoryBean = new LocalSessionFactoryBean();
                Properties pp = new Properties();
                pp.setProperty("hibernate.show_sql", hibernateShowSql);
                pp.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2DDL);
               // pp.setProperty("hibernate.cache.use_second_level_cache", hibernateSecondLevelCache);
                //pp.setProperty("hibernate.cache.provider_class", hibernateCacheClass);
               // pp.setProperty("hibernate.default_schema", hibernateSchema);
                pp.setProperty("hibernate.dialect", hibernateDialect);
                //pp.setProperty("hibernate.cache.region.factory_class",hibernateCacheRegionFactoryClass);
                factoryBean.setDataSource(dataSource());
                factoryBean.setPackagesToScan("com.legadofact.modelo");
                factoryBean.setHibernateProperties(pp);
                factoryBean.afterPropertiesSet();
                return factoryBean.getObject();
            } catch (Exception e) {
                log.error("Couldn't configure the sessionFactory bean", e);
            }
            throw new RuntimeException("Couldn't configure the sessionFactory bean");
        }

        @Bean
        public DataSource dataSource() {

            String jdbcUrlEnv = System.getenv("JDBC_DATABASE_URL");
            String jdbcUsernameEnv = System.getenv("JDBC_DATABASE_USERNAME");
            String jdbcPasswordEnv = System.getenv("JDBC_DATABASE_PASSWORD");
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(jdbcDriver);
            if(jdbcUrlEnv==null){

                ds.setUsername(jdbcUsername);
                ds.setPassword(jdbcPassword);
                ds.setUrl(jdbcUrl);
            }else{

                ds.setUsername(jdbcUsernameEnv);
                ds.setPassword(jdbcPasswordEnv);
                ds.setUrl(jdbcUrlEnv);
            }




            ds.setInitialSize(5);
//            ds.setMaxActive(10);
//            ds.setRemoveAbandoned(true);
            ds.setLogAbandoned(true);
             return ds;
        }
    }


}
