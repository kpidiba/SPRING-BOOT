## SWAGGER API DOCUMENTATION

Swagger documentation with Spring Boot streamlines API development by automatically generating interactive API documentation from code annotations. This dynamic documentation enables developers to explore endpoints, request/response payloads, and even test API calls directly within the Swagger UI. By integrating Swagger with Spring Boot, teams can accelerate development, improve collaboration, and enhance overall developer experience.

### RESOURCES

- [Official Documentation](https://springdoc.org/)
- [Spring boot 3 - OpenApi Documentation | Swagger UI - YouTube](https://www.youtube.com/watch?v=2o_3hjUPAfQ&t=1s) 
- https://swagger.io/docs 

### SWAGGER ENDPOINTS

Swagger UI Endpoint:

```http
http://server:port/context-path/swagger-ui.html
```

Define Custom Route

```json
springdoc.api-docs.path=/api-docs
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

### SECURITY ADD TO WHITE_LIST

```json
"/swagger-ui.html","/swagger-ui/**","/swagger-resources/**",
"/swagger-resources","/v2/api-docs", "/v3/api-docs","/v3/api-docs/**"
```

### OPEN API CONFIGURATION

To enable OpenAPI documentation for your Spring Boot application, add the following `OpenApiConfig` class:

```java
@OpenAPIDefinition(info = @Info(title = "FILE MANAGEMENT", version = "1.0.0", license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), description = "File Management Project With Spring boot and Angular", contact = @Contact(name = "KBrightCoder", email = "kpidibadavid1@gmail.com", url = "https://davidkbright.com")), servers = {
        @Server(description = "Local Server", url = "http://localhost:8080"),
        @Server(description = "Production Server", url = "https://davidkbright.com")
}, security = @SecurityRequirement(name = "bearerAuth"))
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

### Customizing Endpoint Documentation

#### `@Operation`

The `@Operation` annotation is part of the OpenAPI specification and is used to provide additional metadata for documenting API operations. It allows developers to describe the purpose and behavior of an API endpoint. Within the annotation, you can include details such as a description, summary, and responses.

Example: 

```java
@Operation(
    description = "Get endpoint manager",
    summary = "This is a summary for management get endpoint",
    responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized / Invalid Token",
                responseCode = "403"
            )
    }
)
```

#### `@Tag(name="management")`

The `@Tag` annotation can be applied at class or method level and is used to group the APIs in a meaningful way.

Assigns the specified name to the tag, allowing for the categorization and grouping of API endpoints under the designated category in the documentation.

For instance, let’s add this annotation to our GET methods:

```java
@Tag(name = "get", description = "GET methods of Employee APIs")
@GetMapping("/employees")
public List<Employee> findAllEmployees() {
   return repository.findAll();
}

@Tag(name = "get", description = "GET methods of Employee APIs")
@GetMapping("/employees/{employeeId}")
public Employee getEmployee(@PathVariable int employeeId) {
   Employee employee = repository.findById(employeeId)
           .orElseThrow(() -> new RuntimeException("Employee id not found - " + employeeId));
   return employee;
}
```

#### `@Hidden`

Hides the annotated endpoint from the generated documentation. Useful for concealing endpoints that are still in development, deprecated, or intended solely for internal use. Ensures that the endpoint remains accessible within the application while being excluded from the public documentation

```java
@Hidden
```

### @Operation annotation

The `@Operation` annotation enables the developers to provide additional information about a method, such as summary and description.

Let’s update our `updateEmployee()` method:

```java
@Operation(summary = "Update an employee",
       description = "Update an existing employee. The response is updated Employee object with id, first name, and last name.")
@PutMapping("/employees")
public Employee updateEmployee(@RequestBody Employee employee) {
   Employee theEmployee = repository.save(employee);
   return theEmployee;
}
```



### @Parameter annotation

The `@Parameter` annotation can be used on a method parameter to define parameters for the operation. For example,

```java
public Employee getEmployee(@Parameter(
       description = "ID of employee to be retrieved",
       required = true)
       @PathVariable int employeeId) {
   Employee employee = repository.findById(employeeId)
           .orElseThrow(() -> new RuntimeException("Employee id not found - " + employeeId));
   return employee;
}
```
