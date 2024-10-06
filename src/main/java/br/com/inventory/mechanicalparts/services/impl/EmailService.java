package br.com.inventory.mechanicalparts.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${support.mail}")
    private String supportMail;

    public void sendMailToClient(String subject, String email, String content) throws MessagingException {
        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(mail);
        message.setSubject(subject);
        message.setText(content, true);
        message.setFrom(supportMail);
        message.setTo(email);

        mailSender.send(mail);
    }

    public String getContentMail(String name, String password, String url) {
        return "<p style=\"padding-left: 80px; text-align: justify;\">Ol&aacute; <strong>" + name + "</strong>, segue abaixo sua senha provis&oacute;ria:</p>" +
                "<p style=\"padding-left: 80px; text-align: justify;\"><strong>Senha provis&oacute;ria: " + password + " </strong></p>" +
                "<p style=\"padding-left: 80px; text-align: justify;\">Para alterar sua senha acesse a p&aacute;gina do link para cadastrar uma nova senha!" + url + "</p>" +
                "<p style=\"padding-left: 80px; text-align: justify;\"><span style=\"color: #ff0000;\"><strong>Lembre-se que sua senha &eacute; particular e n&atilde;o deve ser compartilhada</strong></span>.</p>";
    }

}


