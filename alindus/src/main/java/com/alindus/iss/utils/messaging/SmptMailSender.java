package com.alindus.iss.utils.messaging;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
public class SmptMailSender {

@Autowired
private JavaMailSender javaMailSender;  

 @Autowired 
 private TemplateEngine templateEngine;
 public void sendSimpleMail(final String recipientName, final String    recipientEmail) 
            throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context();
        ctx.setVariable("name", recipientName);
        ctx.setVariable("imageResource", "classpath:/static/images/");
                
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Cubic Approval");
        //message.setFrom("alindusincorporation@gmail.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("emailTemplate", ctx);
        message.setText(htmlContent, true /* isHtml */); //  true indicates we can provide html tags in the body

        // Send email
        this.javaMailSender.send(mimeMessage);

    }
 }

/*@Component
public class SmtpMailSender {
	
	@Autowired
	private JavaMailSender javaMailSender;	
	
	public void send(String to, String subject, String body) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true); // true indicates
													   // multipart message
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true); // true indicates html
		// continue using helper object for more functionalities like adding attachments, etc.  
		
		javaMailSender.send(message);
		
		
	}

}*/
/*@Configuration 
public class MailConfig {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private Integer port;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPort(port);

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "false");
        return properties;
    }
}*/