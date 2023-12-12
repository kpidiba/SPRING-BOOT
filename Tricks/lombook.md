### LOMBOOK

source: https://www.javaguides.net/2019/05/project-lombok-annotations-examples.html 

# 1. @Getter and @Setter

Instead of manually writing getters and setters for each field in your class, you can use Lombok's *@Getter* and *@Setter* annotations. 

**Example without Lombok:** 

```
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

**With Lombok:**

```
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
}
```

**Benefits:** 

- Reduces lines of code. 
- Auto-generated code ensures fewer human errors. 
- Makes the class more readable.

# 2. @NoArgsConstructor

Generates a no-arguments constructor. 

**Example with Lombok:**  

```
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class User {
    private String name;
}
```

This will generate a default constructor like:  

```
public User {
}
```

# 3. @AllArgsConstructor

Generates a constructor with arguments for all fields in your class. 

**Example with Lombok:**  

```
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {
    private String name;
}
```

This will generate a constructor like: 

```
public User(String name) {
    this.name = name;
}
```

# 4. @RequiredArgsConstructor

Generates a constructor with arguments for all fields that are marked as final, or are marked with [@NonNull](https://www.javaguides.net/2019/03/project-lombok-nonnull-annotation-example.html), and not initialized where they are declared. 

**Example with Lombok:**  

```
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {
    private final String name;
    private int age;
}
```

This will generate a constructor like: 

```
public User(String name) {
    this.name = name;
}
```

**Benefits of Using Constructor Annotations:** 

- Eliminates the need to manually create constructors. 
- Ensures consistency as fields are added or removed. 
- Enhances code readability by reducing boilerplate.

# 5. @Data

@Data
 annotation is a shortcut for @ToString, @EqualsAndHashCode, @Getter on 
all fields, and @Setter on all non-final fields, and 
@RequiredArgsConstructor! 

**With Lombok:**

```
import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
}
```

Benefits: 

- Complete boilerplate code reduction for standard class methods.
- A one-stop annotation for common requirements.

# 6. @Slf4j

This annotation creates and injects a logger into your class. 

**With Lombok:**

```
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerExample {
    public void logSomething() {
        log.info("This is an info message!");
    }
}
```

**Benefits:** 

- No need to manually define the logger.
- Simplifies logging code.

# 7. @Builder

Implement the Builder pattern without coding the boilerplate. 

**With Lombok:**

```
import lombok.Builder;

@Builder
public class Vehicle {
    private String type;
    private String color;
    private int wheels;
}
```

Usage:

```
Vehicle car = Vehicle.builder()
                .type("Car")
                .color("Blue")
                .wheels(4)
                .build();
```

Benefits:  

- Fluent API style initialization.
- Reduces complexity in classes with many parameters.
