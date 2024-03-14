package train.train.configemail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service

public class MailServiceImpl implements  MailService {

    @Autowired

    private JavaMailSender mailSender;
@Autowired
private TemplateEngine templateEngine;

    @Override
    public void sendTextEmail(String sendTo, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setTo(sendTo);
            helper.setSubject(subject);

            // Use Thymeleaf to process the HTML template
            Context context = new Context();
            context.setVariable("content", body);
            String processedHtml = templateEngine.process("Email", context);

            // Set the processed HTML as the email body
            helper.setText(processedHtml, true); // Set the second parameter to true to indicate it's HTML content
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendTextmailupdateregister(String sendTo, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setTo(sendTo);
            helper.setSubject(subject);

            // Use Thymeleaf to process the HTML template
            Context context = new Context();
            context.setVariable("content", body);
            String processedHtml = templateEngine.process("Emailupdateregister", context);

            // Set the processed HTML as the email body
            helper.setText(processedHtml, true); // Set the second parameter to true to indicate it's HTML content
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
   
}