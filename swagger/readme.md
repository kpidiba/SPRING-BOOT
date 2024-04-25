## SWAGGER API DOCUMENTATION

Swagger documentation with Spring Boot streamlines API development by automatically generating interactive API documentation from code annotations. This dynamic documentation enables developers to explore endpoints, request/response payloads, and even test API calls directly within the Swagger UI. By integrating Swagger with Spring Boot, teams can accelerate development, improve collaboration, and enhance overall developer experience.

### RESOURCES

- [Official Documentation](https://springdoc.org/)
- [Spring boot 3 - OpenApi Documentation | Swagger UI - YouTube](https://www.youtube.com/watch?v=2o_3hjUPAfQ&t=1s) 

### SWAGGER ENDPOINTS

Swagger UI Endpoint:

```http
http://server:port/context-path/swagger-ui.html
```

Swagger OpenAPI Dependency:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

Note: Ensure to exclude the Swagger route from Spring Security configurations.

### OPEN API CONFIGURATION

To enable OpenAPI documentation for your Spring Boot application, add the following `OpenApiConfig` class:

```java
@OpenAPIDefinition(info = @Info(title = "FILE MANAGEMENT", version = "1.0.0", license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), description = "File Management Project With Spring boot and Angular", contact = @Contact(name = "KBrightCoder", email = "kpidibadavid1@gmail.com", url = "https://davidkbright.com")), servers = {
        @Server(description = "Local Server", url = "http://localhost:8080"),
        @Server(description = "Production Server", url = "https://davidkbright.com")
})
@SecurityScheme(
    name="bearerAuth",
    description = "JWT auth in the form of Bearer token",
    scheme = "bearer",
    bearerFormat = "JWT",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
```

This class configures OpenAPI metadata such as title, version, description, contact information, servers, and security schemes for your API documentation.

**NB**: To ensure that token-based authentication works properly, add the `@SecurityRequirement` annotation to your controller classes with the name of the `SecurityScheme` annotation. For example:

```java
@SecurityRequirement(name="bearerAuth")
@RestController
public class YourController {
    // Controller methods
}
```

By adding this annotation, you specify that the controller requires the `bearerAuth` security scheme, enabling token-based authentication for the endpoints defined in that controller.


