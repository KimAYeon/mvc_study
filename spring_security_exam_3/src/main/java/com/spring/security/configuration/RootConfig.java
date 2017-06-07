package com.spring.security.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(
		basePackages = "com.spring"
		, excludeFilters = { @Filter(Controller.class), @Filter(Configuration.class)}
)
@EnableTransactionManagement
public class RootConfig {

	private String jdbcDriverClassName = "com.mysql.jdbc.Driver";
	private String jdbcUrl = "jdbc:mysql://52.79.124.158:3306/test";
	private String jdbcUsername = "testuser";
	private String jdbcPassword = "testuser01!!";
	
	//private static final String APP_CONFIG_FILE_PATH = "application.xml";
	
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		//ppc.setLocations(new Resource[] { new ClassPathResource(APP_CONFIG_FILE_PATH) });
		return ppc;
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.jdbcDriverClassName);
		dataSource.setUrl(this.jdbcUrl);
		dataSource.setUsername(this.jdbcUsername);
		dataSource.setPassword(this.jdbcPassword);
		
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, ApplicationContext applicationContext) throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
	
		return factoryBean;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}
