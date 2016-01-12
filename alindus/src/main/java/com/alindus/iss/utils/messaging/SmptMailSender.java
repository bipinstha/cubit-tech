package com.alindus.iss.utils.messaging;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
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

	Logger logger = (Logger) Logger.getInstance(SmptMailSender.class);
	@Autowired
	private TemplateEngine templateEngine;

	public void sendSimpleMail(final String recipientName, final String recipientEmail) {
		// Prepare the evaluation context
		final Context ctx = new Context();
		ctx.setVariable("name", recipientName);
		ctx.setVariable("imageResource", "classpath:/static/images/");
		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		try {
			message.setSubject("Cubic Approval");
			message.setValidateAddresses(false);
			message.setTo(recipientEmail);
			// Create the HTML body using Thymeleaf
			final String htmlContent = this.templateEngine.process("emailTemplate", ctx);
			message.setText(htmlContent, true /* isHtml */); // true indicates
		} catch (MessagingException ex) {
			logger.error(ex.getMessage());
		}
		this.javaMailSender.send(mimeMessage);
	}
}