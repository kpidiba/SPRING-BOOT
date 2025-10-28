# Spring Boot SSE Integration

A Spring Boot application demonstrating Server-Sent Events (SSE) for real-time progress tracking of long-running processes.

## Overview

This project implements Server-Sent Events (SSE) to provide real-time progress updates from long-running backend processes to clients. The application allows clients to subscribe to progress updates and receive streaming data as processes execute.

## Features

- **Real-time Progress Tracking**: Subscribe to progress updates for specific processes

- **SSE Endpoints**: HTTP endpoints for establishing SSE connections and triggering processes

- **Concurrent Processing**: Support for multiple simultaneous processes with unique IDs

- **Automatic Cleanup**: Emitter lifecycle management with completion, timeout, and error handling

## Technology Stack

- **Spring Boot 3.x**

- **Server-Sent Events (SSE)**

- **Java ConcurrentHashMap** for thread-safe emitter management

- **RESTful Web Services**



## Spring Boot Implementation

### 1. ProgressService.java

```java
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProgressService {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter createEmitter(String processId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        this.emitters.put(processId, emitter);

        emitter.onCompletion(() -> emitters.remove(processId));
        emitter.onTimeout(() -> emitters.remove(processId));
        emitter.onError((e) -> emitters.remove(processId));

        return emitter;
    }

    public void sendProgress(String processId, ProcessingProgress progress) {
        SseEmitter emitter = emitters.get(processId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("progress")
                        .data(progress));
            } catch (IOException e) {
                emitters.remove(processId);
            }
        }
    }

    public void complete(String processId) {
        SseEmitter emitter = emitters.remove(processId);
        if (emitter != null) {
            emitter.complete();
        }
    }
}
```

### 2. ProcessingProgress.java

```java
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessingProgress {
    private int progress;
    private String currentStep;
    private String message;

    // Default constructor for JSON serialization
    public ProcessingProgress() {}

    public ProcessingProgress(int progress, String currentStep, String message) {
        this.progress = progress;
        this.currentStep = currentStep;
        this.message = message;
    }

    // Lombok @Getter and @Setter automatically generate getters and setters
    // But you can add custom ones if needed
}
```

### 3. Controller (ProgressController.java)

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api")
public class ProgressController {
    
    private final ProgressService progressService;

    // Constructor injection
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/progress/{processId}")
    public SseEmitter getProgress(@PathVariable String processId) {
        return progressService.createEmitter(processId);
    }

    @PostMapping("/start-process/{processId}")
    public ResponseEntity<String> startProcess(@PathVariable String processId) {
        // Simulate a long-running process
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i += 10) {
                    Thread.sleep(1000); // Simulate work

                    ProcessingProgress progress = new ProcessingProgress(
                            i,
                            "Step " + (i / 10),
                            "Processing item " + i);

                    progressService.sendProgress(processId, progress);
                }

                progressService.complete(processId);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        return ResponseEntity.ok("Process started with ID: " + processId);
    }
}
```

### 4. Required Dependencies (pom.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.example</groupId>
    <artifactId>sse-demo</artifactId>
    <version>1.0.0</version>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <!-- Lombok for getters/setters -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

## Testing with Insomnia

### Step 1: Subscribe to Progress Updates

1. **Create a new GET request** in Insomnia

2. **URL**: `http://localhost:8080/progress/{processId}`
   
   - Replace `{processId}` with a unique identifier (e.g., `process-123`)

3. **Set Headers**: ```xml

```xml
Accept: text/event-stream
Cache-Control: no-cache
```

4. **Send the request** - Insomnia will maintain the connection and display incoming events

### Step 2: Start the Process

1. **Create a new POST request** in Insomnia

2. **URL**: `http://localhost:8080/start-process/{processId}`
   
   - Use the same `processId` from step 1

3. **Send the request** - You'll receive immediate confirmation

### Step 3: Observe Progress Updates

Back in the GET request window, you'll see real-time progress events every second:

```xml
event: progress
data: {"progress":0,"currentStep":"Step 0","message":"Processing item 0"}

event: progress
data: {"progress":10,"currentStep":"Step 1","message":"Processing item 10"}

event: progress
data: {"progress":20,"currentStep":"Step 2","message":"Processing item 20"}

... continues until 100% ...
```












