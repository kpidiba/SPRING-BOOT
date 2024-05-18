# Setting up Database Initialization in Spring Boot

This guide outlines the steps to set up database initialization in a Spring Boot application using `data.sql`.

## Step 1: Add `data.sql` to Resources Folder

First, ensure you have a `data.sql` file containing SQL statements to initialize your database. Place this file in the `src/main/resources` folder of your Spring Boot project.

## Step 2: Update `application.properties`

Next, add the following lines to your `application.properties` file:

```properties
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

These properties instruct Spring Boot to defer datasource initialization until after the schema has been created and to always run the SQL initialization scripts.

## Step 3: Add SQL Statements to `data.sql`

In your `data.sql` file, include the SQL statements necessary to initialize your database. For example:

```sql
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

INSERT INTO users (username, password) VALUES ('john_doe', 'password123');
INSERT INTO users (username, password) VALUES ('jane_smith', 'securepwd456');
```

Replace the example SQL statements with the appropriate schema and data initialization commands for your application.

## Conclusion

By following these steps, you can set up database initialization in your Spring Boot application using `data.sql`. This ensures that your database is initialized with the required schema and data when your application starts up.
