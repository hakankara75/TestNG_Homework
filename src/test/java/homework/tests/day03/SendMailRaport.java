package homework.tests.day03;



import jakarta.mail.Message;
import jakarta.mail.Authenticator;
import jakarta.mail.internet.AddressException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;

public class SendMailRaport {
    private static final String username="hakanbatirhan@yahoo.com";
    private static final String host="smtp.mail.yahoo.com";
    private static final String password=",533Burak";
    private static final Properties properties=new Properties();
    static {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust",host);
        properties.put("mail.smtp.starttls.enabled", "true");



}

    public static void sendPlainTextEmail(String to, String subject, String message, boolean debug) {
        Authenticator authenticator = new Authenticator() { //e-posta gönderme işlemi için kullanılacak olan Authenticator sınıfını oluşturur. Bu sınıf,usernameve password ile kimlik doğrulamasını yapar.
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(properties, authenticator);
        session.setDebug(debug);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address); //E-postanın alıcı adresini ayarlar.
            msg.setSubject(subject); //E-postanın konusunu ayarlar.
            msg.setSentDate(new Date()); //E-postanın gönderilme tarihini ayarlar.

            Multipart mp = new MimeMultipart();

            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(message, "us-ascii");
            mp.addBodyPart(mbp);

            msg.setContent(mp);


            Transport.send(msg);

        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
        }
    }
}
