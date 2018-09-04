package cn.linj2n.email.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.apache.commons.lang.CharEncoding;

import javax.mail.internet.MimeMessage;
@Service
//@PropertySource("classpath:email.yml")
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${spring.mail.username}")
    private String FROM_ADRRESS ;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        logger.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);
        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(FROM_ADRRESS);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            logger.debug("Sent e-mail to User '{}'", to);
        } catch (Exception e) {
            logger.warn("E-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
        }
    }

}