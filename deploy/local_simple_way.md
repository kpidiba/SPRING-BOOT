# DEPLOY LOCAL SIMPLE WAY

Deploying a Spring Boot application on a local server within your enterprise involves several steps. Here's a general guide to get you started:

1. **Prepare your Spring Boot application:**
   
   - Ensure your Spring Boot application is properly configured and packaged. You should have a `.jar` or `.war` file ready for deployment. You can build this using Maven or Gradle.

2. **Install Java on the server:**
   
   - Make sure Java Runtime Environment (JRE) or Java Development Kit (JDK) is installed on your local server. You can download and install it from the official Java website if it's not already installed.

3. **Copy your application to the server:**
   
   - Transfer your packaged Spring Boot application (`.jar` or `.war` file) to your local server. You can use SCP, FTP, or any other file transfer method you prefer.

4. **Run the application:**
   
   - Once the application is on the server, you can run it using the Java command line. Open a terminal or command prompt, navigate to the directory where your `.jar` or `.war` file is located, and execute the following command:
     
     Copy code
     
     `java -jar your-application-name.jar`
     
     Replace `your-application-name.jar` with the actual name of your application file.

5. **Configure server ports and settings:**
   
   - Ensure that the server ports your Spring Boot application is using do not conflict with other services running on the server. You can configure the port in your `application.properties` or `application.yml` file.

6. **Check application logs:**
   
   - Monitor the logs of your Spring Boot application to ensure that it starts successfully and to troubleshoot any issues if they arise. Logs are usually written to the console by default but can be configured to write to files.

7. **Access your application:**
   
   - Once your application is up and running, you should be able to access it using a web browser or API client. If you're using the default configurations, your application will typically be accessible at `http://localhost:8080`.

8. **Security considerations:**
   
   - Ensure that proper security measures are in place, especially if your application deals with sensitive data. This might include securing server access, configuring firewalls, enabling HTTPS, etc.

9. **Monitoring and maintenance:**
   
   - Regularly monitor your application for performance issues, errors, and security vulnerabilities. Perform necessary maintenance tasks such as applying updates and patches to keep your application and server secure and up-to-date.

## DEPLOYMENT STEPS

Deploying a Spring Boot application on a local server involves several steps. Here’s a general guide you can follow:

### 1. **Build Your Application**

First, make sure your Spring Boot application is built and ready for deployment. You can package your application as a JAR or WAR file using Maven or Gradle.

**For Maven:**

```bash
mvn clean package
```

This will create a JAR file in the `target` or `build/libs` directory.

### 2. **Prepare the Local Server**

Ensure you have a local server environment set up. This could be a physical machine or a virtual machine running a Linux or Windows operating system.

### 3. **Install Java**

Make sure Java is installed on the server. Spring Boot requires Java to run. You can check if Java is installed with:

```bash
java -version
```

If not installed, you can install it using your system’s package manager or by downloading it from the [official Java website](https://www.oracle.com/java/technologies/javase-downloads.html).

### 4. **Transfer Your Application**

Transfer the built JAR or WAR file to your local server. You can use SCP, SFTP, or any file transfer method you're comfortable with.

### 5. **Run Your Application**

SSH into your server and navigate to the directory where you placed the JAR file. You can run your Spring Boot application using the following command:

```bash
java -jar your-application.jar
```

### 6. **Configure Application Properties**

You might need to configure application properties specific to your local environment. This can include database connection strings, server ports, etc. You can do this by modifying the `application.properties` or `application.yml` file in your `src/main/resources` directory, or by setting environment variables.

### 7. **Run as a Background Service (Optional-Linux)**

For a production-like setup, you might want to run your application as a background service. On Linux, you can use `systemd` to create a service file:

**Create a service file** `/etc/systemd/system/myapp.service`:

```ini
[Unit]
Description=My Spring Boot Application

[Service]
User=youruser
ExecStart=/usr/bin/java -jar /path/to/your-application.jar
Restart=always

[Install]
WantedBy=multi-user.target
```

**Start and enable the service**:

```bash
sudo systemctl start myapp
sudo systemctl enable myapp
```

On Windows, you can use tools like NSSM (Non-Sucking Service Manager) to run your application as a Windows service.

### 8. **Configure Firewall and Networking**

Ensure that the server’s firewall rules allow traffic on the port your Spring Boot application is using (default is 8080). You might need to adjust firewall settings or network configurations depending on your local setup.

### 9. **Monitor and Maintain**

Once your application is running, you might want to monitor its performance and logs. Spring Boot provides logging capabilities that you can configure in the `application.properties` or `application.yml` file.

Regularly update your application and server to ensure they are secure and functioning correctly.

### ACTIVE PORTS

- **Control Panel > System and Security**

- **Windows Defender Firewall > Advanced Settings**

- **Inbound Rules** 

- **right click> new click** 

- choose **PORT** - **TCP** - **SPECIFIC PORT** - **8080**

- **CHECK ALL CHECKBOXS**

- **SAME FOR OUTBOUND**
