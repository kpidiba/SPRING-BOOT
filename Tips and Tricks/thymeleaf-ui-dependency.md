# Setting up WebJars Dependencies for Bootstrap and jQuery

This guide provides instructions on how to add WebJars dependencies for Bootstrap and jQuery to your Maven project and integrate them into your web pages.

## Step 1: Add Maven Dependencies

First, add the following Maven dependencies to your `pom.xml` file:

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.6.3</version>
</dependency>

<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>5.2.3</version>
</dependency>
```

Ensure you replace the version numbers with the versions you have or want to use.

## Step 2: Include Dependencies in Your Web Page

Next, add the following lines to the HTML head section of your web page to include Bootstrap and jQuery:

```html
<link rel="stylesheet" th:href="@{~/webjars/bootstrap/5.2.3/css/bootstrap.css}">
<script type="text/javascript" th:src="@{~/webjars/jquery/3.6.3/jquery.min.js}"></script>
<script type="text/javascript" th:src="@{~/webjars/bootstrap/5.2.3/js/bootstrap.min.js}"></script>
```

These lines will load the Bootstrap CSS file and the jQuery library from the WebJars dependencies.

## Conclusion

By following these steps, you can easily add Bootstrap and jQuery to your Maven project using WebJars. This allows you to leverage the power of these libraries in your web application without manually downloading and managing them.
