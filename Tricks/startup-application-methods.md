# How to Run Code on Spring Boot Application Startup: A Beginner’s Guides

APRIL 1, 2023

## Introduction

Spring Boot is a popular framework for building web applications in Java. It simplifies the development process by providing a range of features and functionalities out of the box. One such feature is the ability to run code on application startup. This can be useful for initializing resources, loading data, and performing other tasks that need to be executed once the application has started.

In this beginner’s guide, we will explore different ways to run code on Spring Boot application startup. We will also explain the logic behind each approach.

## Implementing ApplicationRunner or CommandLineRunner Interface

One way to run code on Spring Boot application startup is to implement the `ApplicationRunner` or `CommandLineRunner` interface. These interfaces provide a `run` method that gets executed after all the beans have been loaded and before the execution of the main method.

```
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Your code here
        System.out.println("Application started!");
    }
}
```

MyApplicationRunner.java

```
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Your code here
        System.out.println("Application started!");
    }
}
```

MyCommandLineRunner.java

## Using PostConstruct Annotation

Another way to run code on Spring Boot application startup is to use the `@PostConstruct` annotation. This annotation is used to indicate that a method should be called after the bean has been initialized by Spring. This method will be called once the bean has been created, which usually happens during application startup.

```
@Component
public class MyComponent {

    @PostConstruct
    public void init() {
        // Your code here
        System.out.println("Component initialized!");
    }
}
```

MyComponent.java

## Implementing InitializingBean Interface

You can also implement the `InitializingBean` interface and override the `afterPropertiesSet` method. This method will be called by Spring after all the bean properties have been set and before the bean is used.

```
@Component
public class MyInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // Your code here
        System.out.println("Bean initialized!");
    }
}
```

MyInitializingBean.java

## Using EventListener Annotation

The `@EventListener` annotation can be used to mark a method to be executed when an event is fired. We can listen to the `ContextRefreshedEvent` event to execute code after the Spring application context is refreshed.

```
@Component
public class MyEventListener {

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        System.out.println("handleContextRefresh method called");
    }
}
```

MyEventListener.java

## Using SmartInitializingSingleton Interface

The `SmartInitializingSingleton` interface is another way to run code on Spring Boot application startup. This interface provides a `afterSingletonsInstantiated` method that gets executed after all the singleton beans have been instantiated and before the execution of the main method.

```
@Component
public class MySmartInitializingSingleton implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        // Your code here
        System.out.println("Singletons instantiated!");
    }
}
```

MySmartInitializingSingleton.java

## Code Execution Sequence and Output

Let’s execute one simple code and see the order of the execution of different methods.

```
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class HelloWorldApplication implements CommandLineRunner, InitializingBean, ApplicationRunner {

    @Autowired
    private MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
        System.out.println("Application main method called");

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct method called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean method called");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner method called");
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        System.out.println("@EventListener method called");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner method called");
    }

    @Component
    public static class MySmartInitializingSingleton implements SmartInitializingSingleton {
        @Override
        public void afterSingletonsInstantiated() {
            System.out.println("SmartInitializingSingleton method called");
        }
    }
}
```

HelloWorldApplication .java

```
import jakarta.annotation.PostConstruct;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @PostConstruct
    public void postConstruct() {
        System.out.println("MyService @PostConstruct method called");
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        System.out.println("MyService @EventListener method called");
    }

}
```

MyService.java

When we run this application, we will see the following output:

```
MyService @PostConstruct method called
@PostConstruct method called
InitializingBean method called
SmartInitializingSingleton method called
@EventListener method called
MyService @EventListener method called
ApplicationRunner method called
CommandLineRunner method called
Application main method called
```

CMD

## FAQs

Can we use multiple methods to execute code on application startup?

Yes, we can use multiple methods to execute code on application startup, such as `@PostConstruct`, `InitializingBean`, `SmartInitializingSingleton`, `ApplicationRunner`, and `CommandLineRunner`. However, we should use them judiciously and consider the order of execution of these methods.

Is there any difference between using ApplicationRunner and CommandLineRunner interfaces to execute code on application startup?

No, both `ApplicationRunner` and `CommandLineRunner` interfaces are used to execute code on application startup, and they both provide a single `run()` method to be implemented. The only difference is with respect to their method signature.

Is it a good practice to use all methods to execute code on application startup?

No, it is not a good practice to use all methods to execute code on application startup. We should use them judiciously and consider the order of execution of these methods.

What is the advantage of using SmartInitializingSingleton over other methods?

The `SmartInitializingSingleton` interface provides a way to run code after all the singleton beans have been instantiated. This can be useful if your code depends on other beans being instantiated before it runs.
