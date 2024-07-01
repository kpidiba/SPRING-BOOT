# MULTI DATABASE CONNECTION WITH

### SOURCES

-  [Configure Multiple DataSources in Spring Boot | Spring Boot + Multiple Datasources + JPA - YouTube](https://www.youtube.com/watch?v=RTyJ-agrTGc) 

- [GitHub - daisy-world/springboot-with-multiple-datasources](https://github.com/daisy-world/springboot-with-multiple-datasources) 

## RULES

- Repository and Entities should have different name

### CONFIGURATION EXAMPLE

```json
spring.jpa.hibernate.ddl-auto=validate

spring.datasource.host.jdbc-url=jdbc:sqlserver://IP:1439;databaseName=db;encrypt=true;trustServerCertificate=true
spring.datasource.host.username=testing
spring.datasource.host.password=testing
spring.datasource.host.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.hibernate.naming.physical-strategy=com.kbright.faisceaux.config.UpperCasePhysicalNamingStrategy
spring.jpa.database-platform=org.hibernate.dialect.SQLServer2012Dialect

# second connection
spring.datasource.local.jdbc-url=jdbc:sqlserver://IP:1433;databaseName=Test=;encrypt=true;trustServerCertificate=true
spring.datasource.local.username=TG
spring.datasource.local.password=]DEV2014
spring.datasource.local.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

# CONFIGURATION CLASSES

- MAIN DATABASE

```java
package com.kbright.faisceaux.config;

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

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "localEntityManagerFactory", transactionManagerRef = "localTransactionManager", basePackages = {
        "com.kbright.faisceaux.repository.local" })
public class LocalDatabaseConfig {
    @Bean(name = "localDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.local")
    public DataSource localDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "localEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("localDataSource") DataSource localDataSource) {
        return builder
                .dataSource(localDataSource)
                .packages("com.kbright.faisceaux.models.entity.local")
                .build();
    }

    @Bean(name = "localTransactionManager")
    public PlatformTransactionManager localTransactionManager(
            @Qualifier("localEntityManagerFactory") EntityManagerFactory localEntityManagerFactory) {
        return new JpaTransactionManager(localEntityManagerFactory);
    }
}
```

- SECOND DATABASE

```java
package com.kbright.faisceaux.config;

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

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "localEntityManagerFactory", transactionManagerRef = "localTransactionManager", basePackages = {
        "com.kbright.faisceaux.repository.local" })
public class LocalDatabaseConfig {
    @Bean(name = "localDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.local")
    public DataSource localDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "localEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("localDataSource") DataSource localDataSource) {
        return builder
                .dataSource(localDataSource)
                .packages("com.kbright.faisceaux.models.entity.local")
                .build();
    }

    @Bean(name = "localTransactionManager")
    public PlatformTransactionManager localTransactionManager(
            @Qualifier("localEntityManagerFactory") EntityManagerFactory localEntityManagerFactory) {
        return new JpaTransactionManager(localEntityManagerFactory);
    }
}
```

### Explanation

- **Configuration Properties**: Each database configuration is separated and clearly defined under different property prefixes (`spring.datasource.main` and `spring.datasource.secondary`).
- **Entity Management**: Each `EntityManagerFactory` and `TransactionManager` is configured separately for the main and secondary databases, ensuring clear separation of concerns.
- **Repository Configuration**: Repositories (`com.kbright.faisceaux.repository.main` and `com.kbright.faisceaux.repository.secondary`) are specified to connect to their respective databases.

This setup allows you to manage multiple databases with distinct configurations and repositories in a Spring Boot application effectively. Adjust package names (`com.kbright.faisceaux.models.entity.main` and `com.kbright.faisceaux.models.entity.secondary`) according to your actual entity locations.
