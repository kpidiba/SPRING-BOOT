# SQL SERVER PROCEDURE CALL IN SPRING BOOT

### Description

In Spring Boot, calling a stored procedure from a database is a common requirement, particularly when dealing with complex queries or operations that are best executed within the database for performance and encapsulation reasons. This guide provides a comprehensive overview of how to call stored procedures using Spring Boot, covering setup, implementation, and handling various cases.

1. **Dependencies**: Add the following dependencies to your `pom.xml` file if they are not already present(sql server case)

```xml
        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>mssql-jdbc</artifactId>
            <scope>runtime</scope>
        </dependency>
```

- MULTI DATABASE DEFINE BEAN

```java
@Bean(name = "hostJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("hostDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
```

- CALL PROCEDURES WITHOUT PARAMETERS

```java
private final JdbcTemplate jdbcTemplate;
 List<Map<String, Object>> result = jdbcTemplate.queryForList(
                "{call usp_GetAllPAYS()}");
// Access the generated ID
System.out.println("============================"+result+"============================");
```

- CALL PROCEDURES WITH PARAMETERS

```java

```

- CALL PROCEDURE INSERT

the sql server save request

```SQL
 ALTER PROCEDURE [dbo].[usp_InsertPAYS] @CODPAYS int out,  @CODEABG varchar(5) , @LIBPAYS varchar(300) AS  BEGIN  SET NOCOUNT ON;  INSERT INTO [dbCENTIF].[dbo].[PAYS] ( CODEABG,  LIBPAYS ) VALUES ( @CODEABG,  @LIBPAYS  ) SET @CODPAYS = SCOPE_IDENTITY() END
```

call in spring boot

```java
CallableStatementCreator callableStatementCreator = new CallableStatementCreator() {
       @Override
       public CallableStatement createCallableStatement(Connection con) throws SQLException {
           CallableStatement cs = con.prepareCall("{call dbo.usp_InsertPAYS(?,?,?)}");
           cs.registerOutParameter(1, Types.INTEGER);
           cs.setString(2, "CODEABG");
           cs.setString(3, "PAYS1");
           return cs;
        }
};
 SqlParameter[] parametersArray = {
         new SqlOutParameter("CODPAYS", Types.INTEGER),
         new SqlParameter("CODEABG", Types.VARCHAR),
         new SqlParameter("LIBPAYS", Types.VARCHAR)
 };
 List<SqlParameter> parameters = Arrays.asList(parametersArray);
 Map<String, Object> result = jdbcTemplate.call(callableStatementCreator, parameters);
 System.out.println("==================="+result);
```
