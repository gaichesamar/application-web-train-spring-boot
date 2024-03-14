package train.train.dto;

import lombok.Data;

@Data
public class MailSenderDto {
    private String to;
    private String subject;
    private String body;
}
