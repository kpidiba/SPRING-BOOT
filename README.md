# SPRING-BOOT

Spring boot is   module of spring framework which we speed up the development,It' s provides an easier and faster way to set up, configure and run both simple and web-based  applications.

- Scan the class path and find  the dependency it will  automatically configure the things.
- define **controller folder in** the Main class folder or main package

### RESSOURCES

- [chillotech - YouTube](https://www.youtube.com/@chillotech) (AFRICA TOP tuto)

- 

### ARCHITECTURE

```scss
src
├── main
│   ├── java
│   │   └── com
│   │       └── yourcompany
│   │           └── yourproject
│   │               ├── config         (Application configuration classes)
│   │               ├── controller     (REST API controllers)
│   │               ├── exception      (Custom exception classes)
│   │               ├── model          (Data model classes)
│   │               ├── repository     (Database repositories)
│   │               ├── service        (Business logic services)
│   │               ├── util           (Utility classes)
│   │               ├── Application.java (Main application class)
│   │               └── ...
│   ├── resources
│   │   ├── static       (Static resources like CSS, JS, images)
│   │   ├── templates    (HTML templates, if using server-side rendering)
│   │   ├── application.properties (Application-wide properties)
│   │   ├── application.yml        (YAML configuration, if preferred)
│   │   └── ...
└── test
    ├── java
    │   └── com
    │       └── yourcompany
    │           └── yourproject
    │               ├── controller    (Controller test classes)
    │               ├── service       (Service test classes)
    │               ├── util          (Utility test classes)
    │               ├── ApplicationTests.java (Main test class)
    │               └── ...
    └── resources
        ├── application.properties (Test-specific properties)
        └── ...
```

**Explanation of Key Folders:**

- **config:** Contains configuration classes, such as beans, security configurations, and other application-wide settings.

- **controller:** Houses your REST API controller classes, which handle incoming HTTP requests.

- **exception:** Contains custom exception classes, which you can use to handle application-specific errors.

- **model:** Stores data model classes, representing your application's entities.(entity,dto)

- **repository:** Holds database repository interfaces or classes if you're using a database.

- **service:** Houses business logic services that orchestrate interactions between controllers and repositories.

- **util:** Contains utility classes that are used across the application.

- **static:** This folder holds static resources like CSS, JavaScript, and images, typically used for web applications.

- **templates:** If you're using server-side rendering with templating engines like Thymeleaf, this is where your HTML templates would reside.

- **application.properties/application.yml:** Configuration files for properties that configure your application. They include database settings, logging levels, etc.

- **test:** This folder mirrors the `main` folder's structure but is dedicated to test classes and resources.

## TABLE OF CONTENT

### I- [SPRING FRAMEWORK](https://github.com/kpidiba/SPRING-FRAMEWORK)

### II- [SERVLET](https://github.com/kpidiba/JSP---Servlet)

### III- [SPRING SECURITY]()

### IV- [REST API]()

### V- [TRICKS]()

### VI- [BUGS]()

### RUN SPRING BOOT APPLICATION AS A WINDOWS SERVICE

- add this to pom.xml

```xml
<build>
    <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <executable>true</executable>
                </configuration>
            </plugin>
        </plugins>
</build>
```

- maven clean

- maven install

- you will find jar file in target folder

- Download in github winsw ,winsw repo the **sample-minimal.xml** and **WinSWNet4.exe** 

- copy this three file in one same folder

- change **sample-minimal.xml** put in <id></id> project name

- put in <name></name> project name

- replace <executable></executable> with

```xml
<!-- Path to the executable, which should be started -->
  <executable>java</executable>
  <arguments>-jar application_name.jar</arguments>
```

- change **sample-minimal** name with **WinSWNet4**

- launch

```bash
WinSWNet4.exe install //to install
WinSWNet4.exe uninstall //to uninstall
```

- search in service to find him

### APPLICATION PROPERTIES

Application Properties support us to work in different environments. In this chapter, you are going to learn how to configure and specify the properties to a Spring Boot application.

```java
we can keep separate properties file for each profile as shown below −

application.properties

server.port = 8080
spring.application.name = demoservice
application-dev.properties

server.port = 9090
spring.application.name = demoservice
application-prod.properties

server.port = 4431
spring.application.name = demoservice
```

### application.yml/application.yaml file

The application.properties file is not that readable. So most of the time developers choose application.yml file over application.properties file. YAML is a superset of JSON, and as such is a very convenient format for specifying hierarchical configuration data. YAML is more readable and it is good for the developers to read/write configuration files. For example, let’s pick some of the properties files that we have explained above, and let’s write them in YAML format.

## CRUD

[`JpaRepository`](http://static.springsource.org/spring-data/data-jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html) extends [`PagingAndSortingRepository`](http://static.springsource.org/spring-data/data-commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html) which in turn extends [`CrudRepository`](http://static.springsource.org/spring-data/data-commons/docs/current/api/org/springframework/data/repository/CrudRepository.html).

Their main functions are:

- [`CrudRepository`](http://static.springsource.org/spring-data/data-commons/docs/current/api/org/springframework/data/repository/CrudRepository.html) mainly provides CRUD functions.
- [`PagingAndSortingRepository`](http://static.springsource.org/spring-data/data-commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html) provides methods to do pagination and sorting records.
- [`JpaRepository`](http://static.springsource.org/spring-data/data-jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html) provides some JPA-related methods such as flushing the persistence context and deleting records in a batch.

Because of the inheritance mentioned above, `JpaRepository` will have all the functions of `CrudRepository` and `PagingAndSortingRepository`. So if you don't need the repository to have the functions provided by `JpaRepository` and `PagingAndSortingRepository` , use `CrudRepository`.

- https://docs.spring.io/

- | [`spring.data.rest.base-path`](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.data.spring.data.rest.base-path) | Base path to be used by Spring Data REST to expose repository resources. |
  | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------ |

### ADD CSS AND JS

- create **css and js** folder in static

- link it with

```html
<link th:href="@{/css/bootstrap.min.css}" rel="stylesheet" />
```
