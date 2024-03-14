package train.train.services;


import train.train.dto.MailSenderDto;

public interface MailSenderService {
    void sendNewMail(MailSenderDto mailSenderDto);

}
