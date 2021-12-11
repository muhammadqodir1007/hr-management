package uz.pdp.hrmanagement.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Component
public class MailSender {
    @Autowired
    JavaMailSender javaMailSender;

    //manager,xodim verify
    public boolean sendVerify(String email, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.addHeader("content-type", "html/text");
            message.setFrom("PDP@gmail.com");
            message.setTo(email);
            message.setSubject("Tasdiqlash kodi");
            message.setText("<a href='localhost/api/auth/verifyEmail?email=" + email + "&code=" + code + "'>Tasdiqlash  kodi</a>");
            javaMailSender.send(message); //yuborish
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sendTask(String email, String title, UUID id) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.addHeader("content-type", "html/text");
            message.setFrom("PDP@gmail.com");
            message.setTo(email);
            message.setSubject("Task title:" + title);
            message.setText("<a href='localhost/api/auth/verifyEmail?email=" + email + "&taskId=" + id + "'>Task title</a>");
            javaMailSender.send(message); //yuborish
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean completedTask(String email, String title, UUID id) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.addHeader("content-type", "html/text");
            message.setFrom("PDP@gmail.com");
            message.setTo(email);
            message.setSubject("Task completed:" + title);
            message.setText("<a href='localhost/api/task/" + id + "'>Task</a>");
            javaMailSender.send(message); //yuborish
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //for example
//    public boolean send(String to, String text) throws MessagingException {
//        String from = "management@gmail.com";
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setSubject("Information!");
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setText(text, true);
//        javaMailSender.send(message);
//        return true;
//    }
//
//    public boolean mailTextAdd(String email, String code, String pass) throws MessagingException {
//        String link = "http:localhost:8080/api/user/verifyEmail?email=" + email +"&code="+code;
//        String text =
//                "<a href=\""+link+"\" style=\"padding: 10px 15px; background-color: darkslateblue; color: white; text-decoration: none; border-radius: 4px; margin: 10px; display: flex; max-width: 120px;\">Emailni tasdiqlash</a>\n" +
//                        "<br>\n" +
//                        "<p>Parolingiz: <b> "+ pass +"</b></p>\n" +
//                        "<br>\n" +
//                        "<p style=\"color: red\"><b>Iltimos uni hech kimga bermang!</b></p>";
//
//        return send(email,text);
//    }


}
