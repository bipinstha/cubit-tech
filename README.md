# AlindusApp
#mysql jdbc configuration
spring.datasource.url: jdbc:mysql://localhost/dbname
spring.datasource.username=username
spring.datasource.password=password
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
