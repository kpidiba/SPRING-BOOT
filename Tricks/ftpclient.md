# FTP File Server Connection and File Upload

This project demonstrates how to establish a connection to an FTP file server, create directories, and upload files using Java and the Apache Commons Net library. The implementation allows for configuration values to be retrieved from a database or from the `application.properties` file, with refactoring to avoid code repetition.

## Features

- **FTP Server Connection:** Establishes a connection to an FTP server using credentials stored in a database or in the `application.properties` file.
- **Directory Management:** Checks if a specific directory exists on the server, creates it if necessary, and changes the working directory to the newly created or existing directory.
- **File Upload:** Uploads files to the specified directory on the FTP server.
- **Graceful Error Handling:** Provides meaningful error messages during connection, directory creation, and file upload processes.
- **Code Reusability:** Refactored code using utility classes to avoid repetition and enhance maintainability.

## Requirements

- Java 8 or higher
- Spring Boot
- Apache Commons Net library (`commons-net` dependency)

## Configuration

The project supports two methods for retrieving FTP server configuration:

### 1. **Database Configuration**

Configuration values such as the FTP server's IP address, username, and password are retrieved from a database using the `ConfigRepository`.

### 2. **Application Properties Configuration**

Alternatively, the project supports fetching FTP server configurations from the `application.properties` file using Spring's `@Value` annotation.

### Example `application.properties` Configuration:

```properties
ftp.server=ftp.yourserver.com
ftp.port=21
ftp.username=yourUsername
ftp.password=yourPassword
```

## Implementation

### Refactoring to Avoid Code Repetition

To avoid repeating code for FTP configuration and connection setup, a utility class `FtpClientUtil` has been created. This class encapsulates the logic for retrieving configuration values and creating an `FTPClient` instance.

#### `FtpClientUtil` Class:

```properties
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FtpClientUtil {

    @Autowired
    private ConfigRepository configRepository;

    public FTPClient createFtpClient() throws IOException {
        // Get configuration values for FTP server connection
        Config confhost = configRepository.findByKeyConfig("HOSTIPVPN");
        Config auth = configRepository.findByKeyConfig("FTPUSER");

        String HOSTIPVPN = confhost.getValueConfig().replaceAll("ftp://|/", "");
        String FTPUSER = auth.getValueConfig();
        String[] parts = FTPUSER.split("\\*");
        String user = parts[0];
        String password = parts[1];

        // Create and configure FTPClient
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(HOSTIPVPN, 21);
        ftpClient.login(user, password);
        ftpClient.enterLocalPassiveMode(); // Optional, depending on server config

        return ftpClient;
    }
}

```

#### Using `FtpClientUtil` in a Service:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FtpService {

    @Autowired
    private FtpClientUtil ftpClientUtil;

    public void uploadFiles(String code, List<MultipartFile> files) {
        FTPClient ftpClient = null;
        try {
            ftpClient = ftpClientUtil.createFtpClient();
            System.out.println("FTP Client Connection: " + ftpClient.isConnected());

            // Create folder
            final String DOS = "DES";
            String folderName = DOS + "/" + code;
            try {
                if (!ftpClient.changeWorkingDirectory(folderName)) {
                    ftpClient.makeDirectory(folderName);
                    ftpClient.changeWorkingDirectory(folderName);
                    System.out.println("Folder " + folderName + " created");
                } else {
                    System.out.println("Folder " + folderName + " already exists");
                }
            } catch (IOException ex) {
                throw new RuntimeException("Error creating folder " + folderName, ex);
            }

            // Send files
            for (MultipartFile file : files) {
                try (InputStream inputStream = file.getInputStream()) {
                    boolean done = ftpClient.storeFile(file.getOriginalFilename(), inputStream);
                    if (done) {
                        System.out.println("File " + file.getOriginalFilename() + " uploaded successfully.");
                    } else {
                        System.out.println("Failed to upload file " + file.getOriginalFilename());
                    }
                } catch (IOException ex) {
                    System.out.println("Error uploading file " + file.getOriginalFilename() + ": " + ex.getMessage());
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException("Error during FTP operation", ex);
        } finally {
            if (ftpClient != null && ftpClient.isConnected()) {
                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

```

### DELETE FILES AND FOLDER

```java
@Override
    public void deleteFolder(String code) {
        FTPClient ftpClient = null;
        final String DOS = "DES";
        try {
            ftpClient =ftpClientUtil.createFtpClient();
            System.out.println("FTP Client Connection: " + ftpClient.isConnected());

            // NOTE: CREATE A FOLDER
            String folderName = DOS + "/"+code;
             // Check if the directory exists
             if (ftpClient.changeWorkingDirectory(folderName)) {
                System.out.println(ftpClient.printWorkingDirectory()+"working 1");
                // Change to parent directory
                System.out.println(ftpClient.printWorkingDirectory()+"working 10");
                
                // Delete contents of the directory
                deleteDirectoryContents(folderName,ftpClient);
                ftpClient.changeWorkingDirectory("..");

                // Delete the directory
                if (ftpClient.removeDirectory(folderName)) {
                    System.out.println("Folder " + folderName + " deleted successfully");
                } else {
                    System.out.println("Failed to delete folder " + folderName);
                }
            } else {
                System.out.println("Folder " + folderName + " does not exist");
            }

            ftpClient.logout();
        } catch (Exception ex) {
            ex.printStackTrace();
            try{
                if ( ftpClient != null && ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException exo) {
                exo.printStackTrace();
                log.error("Erreur lors de la deconnexion du ftp", exo);
            }
            ex.printStackTrace();
            log.error("Error during upload", ex);
            throw new RuntimeException("Error during FTP operation", ex);
        }
    }

    private void deleteDirectoryContents(String directory,FTPClient ftpClient) throws IOException {
        System.out.println(ftpClient.printWorkingDirectory()+"working 11");

        // Change to the target directory
        ftpClient.changeWorkingDirectory(directory);
        System.out.println(ftpClient.printWorkingDirectory()+"working 11");

        // List contents
        String[] files = ftpClient.listNames();
        if (files != null) {
            for (String file : files) {
                System.out.println(file+" file 3");
                // Delete files and subdirectories
                if (ftpClient.listDirectories(file).length > 0) {
                    // It's a directory, recursively delete it
                    deleteDirectoryContents(file,ftpClient);
                } else {
                    // It's a file, delete it
                    System.out.println("DElete file"+file.getBytes());
                    if(ftpClient.deleteFile(file)){
                        System.out.println("deleted");
                    }else{
                        System.out.println("no deleted");
                    }
                }
            }
        }

        // Change back to parent directory and delete the directory itself
        ftpClient.changeToParentDirectory();
        ftpClient.removeDirectory(directory);
    }
```

### Dependency

To use the `FTPClient` class, ensure that the following dependency is added to your `pom.xml` if you're using Maven:

```xml
<dependency>
    <groupId>commons-net</groupId>
    <artifactId>commons-net</artifactId>
    <version>3.8.0</version>
</dependency>
```

## Usage

1. **Database Configuration:**
   
   - Ensure that your `ConfigRepository` is properly set up to fetch `HOSTIPVPN` and `FTPUSER` from the database.

2. **Application Properties Configuration:**
   
   - Set up the FTP server details in your `application.properties` file.
   - The `FtpClientConfig` class will handle the connection setup using these properties.

3. **Using the Service:**
   
   - Inject the `FtpService` into your application and call `uploadFiles` to handle FTP operations.

```java
@Autowired
private FtpService ftpService;

// Example usage
ftpService.uploadFiles("someCode", files);
```

## Conclusion

This project provides a robust solution for interacting with an FTP server, including establishing connections, managing directories, and uploading files. The refactored approach using utility classes ensures code reusability and maintainability while offering flexible configuration options for different environments.
