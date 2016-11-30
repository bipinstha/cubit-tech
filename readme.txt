Create application.properties file in src/main/resource location and add below content to run the project


#mysql jdbc configuration
spring.datasource.url: jdbc:mysql://localhost/alindus
spring.datasource.username=root
spring.datasource.password=inspiron
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

#Spring email configuration
spring.mail.properties.mail.smtp.starttls.enable = true

spring.mail.host = smtp.gmail.com
spring.mail.username = alindusincorporation@gmail.com
spring.mail.password = alindus123

spring.mail.properties.mail.smtp.auth = true
spring.mail.properties.mail.smtp.socketFactory.port = 25 
#25
spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.fallback = false
#spring.mail.smtps.ssl.enable = true




