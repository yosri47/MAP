package interfaces;

import javax.ejb.Local;

@Local
public interface MailSenderLocal {
    public void send(String to,String subject,String message);
}
