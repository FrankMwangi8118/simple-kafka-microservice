package Mail;

import org.springframework.stereotype.Service;

@Service
public interface MailSender {
    boolean sendDownTimeAlert(String email,String serverName,String ipAddress) throws Exception;
}
