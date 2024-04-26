# LOCAL DEPLOY ON TOMCAT SERVER

- **Install Tomcat Server**: Follow the installation instructions for your operating system. 🛠️

- **Configuration in pom.xml**: Update the scope of `spring-boot-starter-tomcat` to `provided`.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

- ```xml
  <tomcat-users>
    <user username="username" password="password" roles="role"/>
  </tomcat-users>
  ```

- **Generate WAR File**: Use Maven or your preferred build tool to generate the WAR file:
  
  ```xml
  mvn clean package
  ```

- **Access Application Manager**: Go to the Application Manager in your browser.

- **Deploy Application**: Select "Deploy" and upload the generated WAR file.

- **Get Application URL**: Once deployed, you'll receive a URL to access the application. 🚀
