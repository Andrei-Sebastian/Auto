package principalPACK.clase.preluate;

import com.email.durgesh.Email;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class SendEmail {
    //private static int HOST=465;

    public static void sendEmail(String code,String catre) {

        Email email = new Email("testprogramareasi@gmail.com", "Test@123");
        try {
            email.setFrom("testprogramareasi@gmail.com", "YaPiesa SRL");
            email.setText("Codul pentru recuperarea parolei este: " + code);
            email.addRecipient(catre);
            email.setSubject("Resetare parola");
            email.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
