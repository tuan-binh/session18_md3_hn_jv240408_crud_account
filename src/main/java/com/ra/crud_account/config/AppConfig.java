package com.ra.crud_account.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ra.crud_account"})
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware
{
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}
	
	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine springTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(springResourceTemplateResolver());
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setTemplateEngine(springTemplateEngine());
		return viewResolver;
	}
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName("com.mysql.cj.jdbc.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/crud_account?createDatabaseIfNotExist=true");
		source.setUsername("root");
		source.setPassword("123456");
		return source;
	}
	
	@Bean
	public SessionFactory sessionFactory()
	{
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.ra.crud_account.model");
		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		factoryBean.setHibernateProperties(props);
		try
		{
			factoryBean.afterPropertiesSet();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return factoryBean.getObject();
	}
}
