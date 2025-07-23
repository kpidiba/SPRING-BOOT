## 📦 Standardized API Response in Spring Boot

To ensure consistency and clarity in your API responses, we define a reusable `ApiResponse<T>` class and return it from all endpoints. This approach improves maintainability and integrates well with frontend clients.

## ✅ Goals

- Standardize all **success** responses

- Keep a uniform format for **all endpoints**

- Improve **API documentation** and **developer experience**



## 📘 Example Response Format

Instead of returning raw data:

```json
[{ "id": 1, "name": "Alice" }, { "id": 2, "name": "Bob" }]
```

Return a structured response:

```json
{
  "status": 200,
  "message": "Success",
  "data": [
    { "id": 1, "name": "Alice" },
    { "id": 2, "name": "Bob" }
  ],
  "timestamp": "2025-07-23T15:40:00"
}
```

## ✅ 1. ApiResponse Class

```java
// File: payload/ApiResponse.java

public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
}
```

## ✅ 2. Utility Class (Optional)

```java
// File: util/ResponseUtil.java

public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.OK.value(), "Success", data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        ApiResponse<T> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Created", data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse<Void>> successMessage(String message) {
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), message, null);
        return ResponseEntity.ok(response);
    }
}

```

---

## ✅ 3. Usage in Controller

```java
@GetMapping("/users")
public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
    List<UserDTO> users = userService.findAll();
    return ResponseUtil.success(users);
}
```

```java
@DeleteMapping("/users/{id}")
public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseUtil.successMessage("User deleted successfully.");
}
```

## 📂 Recommended Folder Structure

```css
src/main/java/com/yourcompany/yourapp
├── controller
│   └── UserController.java
├── service
│   └── UserService.java
├── dto
│   └── UserDTO.java
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── payload
│   ├── ApiResponse.java       ← ✅ Place here
│   └── ErrorResponse.java     ← ✅ Place here
├── util
│   └── ResponseUtil.java      ← ✅ Optional helper

```


