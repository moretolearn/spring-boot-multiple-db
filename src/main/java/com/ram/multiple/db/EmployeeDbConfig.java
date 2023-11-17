package com.ram.multiple.db;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "eemf", transactionManagerRef = "etm", basePackages = "com.ram.multiple.db.reposotory.employee")
public class EmployeeDbConfig {

	@Primary
	@Bean("eds")
	@ConfigurationProperties(prefix = "spring.employee.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean("eemf")
	public LocalContainerEntityManagerFactoryBean getLCEMFB(EntityManagerFactoryBuilder builder,
			@Qualifier("eds") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.ram.multiple.db.model.employee")
				.persistenceUnit("employee").build();
	}
	
	@Primary
	@Bean("etm")
	public PlatformTransactionManager getPTM(@Qualifier("eemf") EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
