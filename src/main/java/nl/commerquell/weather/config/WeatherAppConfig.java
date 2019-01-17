package nl.commerquell.weather.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("nl.commerquell.weather")
@PropertySource("classpath:weather.properties")
public class WeatherAppConfig implements WebMvcConfigurer {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private Environment env;

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		logger.info("View resolver registered");
		
		return viewResolver;
	}
	
	@Bean
	public DataSource dataSource() {
		// create connection pool
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();

		// set the jdbc driver
		try {
			myDataSource.setDriverClass(env.getProperty("jdbc.driver"));		
		}
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// set database connection props
		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		myDataSource.setUser(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize", 3));
		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize", 2));
		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize", 10));		
		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime", 30));

		logger.info("URL for database: " + myDataSource.getJdbcUrl());
		logger.info("Database user...: " + myDataSource.getUser());

		return myDataSource;
	}
	
	private int getIntProperty(String propName, int defValue) {
		if (!env.containsProperty(propName)) {
			return defValue;
		}

		int retval;
		String value = env.getProperty(propName);
		try {
			Integer i = Integer.valueOf(value);
			retval = i.intValue();
		} catch (Exception x) {
			retval = defValue;
		}
		
		return retval;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return properties;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean theSessionFactory = new LocalSessionFactoryBean();
		theSessionFactory.setDataSource(dataSource());
		theSessionFactory.setHibernateProperties(hibernateProperties());
		theSessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		
		logger.info("Session factory instantiated");

		return theSessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		logger.info("Transaction manager instantiated");
		
		return transactionManager;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
}
