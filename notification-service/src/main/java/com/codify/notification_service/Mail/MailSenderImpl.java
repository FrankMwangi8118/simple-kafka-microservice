package Mail;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailSenderImpl implements MailSender {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MailSenderImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public boolean sendDownTimeAlert(String email, String serverName, String ipAddress) throws Exception {
        try {
            // 1. Set variables in the Thymeleaf context
            Context context = new Context();
            context.setVariable("serverName", serverName);
            context.setVariable("ipAddress", ipAddress);

            // 2. Generate HTML content from Thymeleaf template
            String body = templateEngine.process("Mail", context); // Mail.html in /templates

            // 3. Create and configure the email
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("🚨 Server Down Alert");
            helper.setText(body, true); // true = HTML content

            mailSender.send(mimeMessage);
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to send downtime alert: " + e.getMessage(), e);
        }
    }
}
