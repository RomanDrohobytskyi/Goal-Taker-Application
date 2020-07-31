package application.services;

public interface MailSenderService {
    void send(String mailTo, String subject, String message);
}
