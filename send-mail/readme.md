# 📧 Configuration et Envoi d'Emails avec Spring Boot 🚀

Ce guide vous explique comment configurer et envoyer des emails avec Spring Boot. Nous utiliserons des icônes pour rendre les étapes plus visuelles et faciles à suivre.

## 📋 Prérequis

- 🛠️ **Java Development Kit (JDK)** 11 ou supérieur

- 🌐 **Spring Boot** 2.x ou supérieur

- 📦 **Gestionnaire de dépendances** (Maven ou Gradle)

- 📧 **Compte email** (Gmail, Outlook, etc.)

## 🛠️ Configuration du Projet

### 1. � Créer un Projet Spring Boot

Utilisez [Spring Initializr](https://start.spring.io/) pour générer un projet Spring Boot avec les dépendances suivantes :

- **Spring Web**

- **Spring Mail**

### 2. 📦 Ajouter les Dépendances

Si vous utilisez **Maven**, ajoutez les dépendances suivantes dans votre `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

### 3. ⚙️ Configurer les Propriétés de l'Email

Dans votre fichier `application.properties` (ou `application.yml`), configurez les propriétés pour l'envoi d'emails. Voici un exemple pour Gmail :

```properties
# Configuration SMTP pour Gmail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=votre_email@gmail.com
spring.mail.password=votre_mot_de_passe
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

**Remarque :** Pour Gmail, vous devrez peut-être activer l'accès aux applications moins sécurisées ou générer un mot de passe d'application.

## ⚙️ Configuration des Propriétés SMTP SSL/TLS

Dans votre fichier `application.properties` (ou `application.yml`), configurez les propriétés SMTP avec les paramètres SSL/TLS fournis :

```properties
spring.mail.host=centif.tg
spring.mail.port=465
spring.mail.username=centif@centif.tg
spring.mail.password=votre_mot_de_passe
spring.mail.protocol=smtp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.ssl.enable=true
spring.mail.properties.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.port=465
spring.mail.properties.mail.smtp.starttls.enable=false
```

## 📝 Créer un Service pour Envoyer des Emails

### 1. 🏗️ Créer un Service `EmailService`

Créez une classe `EmailService` qui utilisera `JavaMailSender` pour envoyer des emails.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSend;
    public void sendEmail(String to, String subject, String text) {
    // Crée un objet SimpleMailMessage pour représenter l'email
    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom("centif@centif.tg");

    // Définit le destinataire de l'email
    message.setTo(to);

    // Définit le sujet de l'email
    message.setSubject(subject);

    // Définit le contenu textuel de l'email
    message.setText(text);

    // Envoie l'email en utilisant JavaMailSender
    mailSender.send(message);
    }
}
```

### 2. 🎯 Utiliser le Service dans un Contrôleur

Créez un contrôleur REST pour exposer un endpoint qui envoie un email.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendEmail(to, subject, text);
        return "Email sent successfully!";
    }
}
```

#### 🛠️ Configuration Avancée

### 1. 📎 Envoyer des Pièces Jointes

Pour envoyer des emails avec des pièces jointes, vous pouvez utiliser `MimeMessageHelper`.

```java
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public void sendEmailWithAttachment(String to, String subject, String text, String attachmentPath) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(text);

    // Ajouter une pièce jointe
    ClassPathResource file = new ClassPathResource(attachmentPath);
    helper.addAttachment(file.getFilename(), file);

    mailSender.send(message);
}
```

### 2. 🎨 Envoyer des Emails HTML

Pour envoyer des emails au format HTML, utilisez `MimeMessageHelper` avec `setText` et `true` pour indiquer que le contenu est HTML.

```java
helper.setText("<h1>Ceci est un email HTML</h1>", true);
```

## 📚 Ressources Supplémentaires

- [Documentation Officielle Spring Mail](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#mail)

- [Guide Spring Boot](https://spring.io/guides/gs/serving-web-content/)
