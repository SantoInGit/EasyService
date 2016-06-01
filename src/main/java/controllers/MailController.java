//package controllers;
//
//// [START simple_includes]
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//// [END simple_includes]
//
//// [START multipart_includes]
//import java.io.InputStream;
//import java.io.ByteArrayInputStream;
//import java.io.UnsupportedEncodingException;
//import javax.annotation.Resource;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Named;
//import javax.mail.Authenticator;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMultipart;
//// [END multipart_includes]
//
//@Named(value = "mailController")
//@RequestScoped
//@SuppressWarnings("serial")
//public class MailController {
//
//    @Resource(lookup = "EMailME")
//    public void sendSimpleMail() {
//        // [START simple_example]
////        final String username = "prajapatijagat2009";
////        final String password = "******";
////        Properties props = new Properties();
////
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.smtp.host", "smtp.gmail.com");
////        props.put("mail.smtp.port", "465");
//        Properties props = System.getProperties();
//        //props.setProperty("mail.smtp.host", "localhost");
//        Session session = Session.getDefaultInstance(props, null);
////        Session session = Session.getInstance(props,
////                new javax.mail.Authenticator() {
////                    @Override
////                    protected PasswordAuthentication getPasswordAuthentication() {
////                        return new PasswordAuthentication(username, password);
////                    }
////                });
//
//        try {
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress("prajapatijagat2014@gmail.com", "Admin"));
//            msg.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress("prajapatijagat2009@gmail.com", "Mr. User"));
//            msg.setSubject("Your easyservices.com.au account has been activated");
//            Transport.send(msg);
//        } catch (AddressException e) {
//            // ...
//        } catch (MessagingException e) {
//            // ...
//        } catch (UnsupportedEncodingException e) {
//            // ...
//        }
//        // [END simple_example]
//
////        String USER_NAME = "prajapatijagat2009";  // GMail user name (just the part before "@gmail.com")
////        String PASSWORD = "******"; // GMail password
////        String RECIPIENT = "prajapatijagat2009@gmail.com";
////        String from = USER_NAME;
////        String pass = PASSWORD;
////        String[] to = {RECIPIENT}; // list of recipient email addresses
////        String subject = "Java send mail example";
////        String body = "Welcome to JavaMail!";
////
////        sendFromGMail();
//    }
//
//    public void sendMultipartMail() {
//        Properties props = new Properties();
//        Session session = Session.getDefaultInstance(props, null);
//
//        String msgBody = "...";
//
//        try {
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress("info@easyservices.com.au", "Admin"));
//            msg.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress("prajapatijagat2009@gmail.com", "Mr. User"));
//            msg.setSubject("Your easyservices.com.au account has been activated");
//            msg.setText(msgBody);
//
//            // [START multipart_example]
//            String htmlBody = "";          // ...
//            byte[] attachmentData = null;  // ...
//            Multipart mp = new MimeMultipart();
//
//            MimeBodyPart htmlPart = new MimeBodyPart();
//            htmlPart.setContent(htmlBody, "text/html");
//            mp.addBodyPart(htmlPart);
//
//            MimeBodyPart attachment = new MimeBodyPart();
//            InputStream attachmentDataStream = new ByteArrayInputStream(attachmentData);
//            attachment.setFileName("manual.pdf");
//            attachment.setContent(attachmentDataStream, "application/pdf");
//            mp.addBodyPart(attachment);
//
//            msg.setContent(mp);
//            // [END multipart_example]
//
//            Transport.send(msg);
//
//        } catch (AddressException e) {
//            // ...
//        } catch (MessagingException e) {
//            // ...
//        } catch (UnsupportedEncodingException e) {
//            // ...
//        }
//    }
//
//    private static void sendFromGMail() {
//        final String SMTP_HOST = "smtp.gmail.com";
//        final String SMTP_PORT = "587";
//        final String GMAIL_USERNAME = "prajapatijagat2009";
//        final String GMAIL_PASSWORD = "*******";
//
//        System.out.println("Process Started");
//
//        Properties prop = System.getProperties();
//        prop.setProperty("mail.smtp.starttls.enable", "true");
//        prop.setProperty("mail.smtp.host", SMTP_HOST);
//        prop.setProperty("mail.smtp.user", GMAIL_USERNAME);
//        prop.setProperty("mail.smtp.password", GMAIL_PASSWORD);
//        prop.setProperty("mail.smtp.port", SMTP_PORT);
//        prop.setProperty("mail.smtp.auth", "true");
//        System.out.println("Props : " + prop);
//
//        Session session = Session.getInstance(prop, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(GMAIL_USERNAME,
//                        GMAIL_PASSWORD);
//            }
//        });
//
//        System.out.println("Got Session : " + session);
//
//        MimeMessage message = new MimeMessage(session);
//        try {
//            System.out.println("before sending");
//            message.setFrom(new InternetAddress(GMAIL_USERNAME));
//            message.addRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(GMAIL_USERNAME));
//            message.setSubject("My First Email Attempt from Java");
//            message.setText("Hi, This mail came from Java Application.");
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(GMAIL_USERNAME));
//            Transport transport = session.getTransport("smtp");
//            System.out.println("Got Transport" + transport);
//            transport.connect(SMTP_HOST, GMAIL_USERNAME, GMAIL_PASSWORD);
//            transport.sendMessage(message, message.getAllRecipients());
//            System.out.println("message Object : " + message);
//            System.out.println("Email Sent Successfully");
//        } catch (AddressException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//}
//
////import java.util.Properties;
////
////import javax.activation.DataHandler;
////import javax.activation.DataSource;
////import javax.activation.FileDataSource;
////import javax.mail.BodyPart;
////import javax.mail.Message;
////import javax.mail.MessagingException;
////import javax.mail.Multipart;
////import javax.mail.Session;
////import javax.mail.Transport;
////import javax.mail.internet.AddressException;
////import javax.mail.internet.InternetAddress;
////import javax.mail.internet.MimeBodyPart;
////import javax.mail.internet.MimeMessage;
////import javax.mail.internet.MimeMultipart;
////
////public class MailController {
////
////    private static String protocol = "smtp";
////
////    private String username;
////    private String password;
////
////    private Session session;
////    private Message message;
////    private Multipart multipart;
////
////    public MailController() {
////        this.multipart = new MimeMultipart();
////    }
////
////    public void setSender(String username, String password) {
////        this.username = username;
////        this.password = password;
////
////        this.session = getSession();
////        this.message = new MimeMessage(session);
////    }
////
////    public void addRecipient(String recipient) throws AddressException, MessagingException {
////        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
////    }
////
////    public void setSubject(String subject) throws MessagingException {
////        message.setSubject(subject);
////    }
////
////    public void setBody(String body) throws MessagingException {
////        BodyPart messageBodyPart = new MimeBodyPart();
////        messageBodyPart.setText(body);
////        multipart.addBodyPart(messageBodyPart);
////
////        message.setContent(multipart);
////    }
////
////    public void send() throws MessagingException {
////        Transport transport = session.getTransport(protocol);
////        transport.connect(username, password);
////        transport.sendMessage(message, message.getAllRecipients());
////
////        transport.close();
////    }
////
////    public void addAttachment(String filePath) throws MessagingException {
////        BodyPart messageBodyPart = getFileBodyPart(filePath);
////        multipart.addBodyPart(messageBodyPart);
////
////        message.setContent(multipart);
////    }
////
////    private BodyPart getFileBodyPart(String filePath) throws MessagingException {
////        BodyPart messageBodyPart = new MimeBodyPart();
////        DataSource dataSource = new FileDataSource(filePath);
////        messageBodyPart.setDataHandler(new DataHandler(dataSource));
////        messageBodyPart.setFileName(filePath);
////
////        return messageBodyPart;
////    }
////
////    private Session getSession() {
////        Properties properties = getMailServerProperties();
////        Session session = Session.getDefaultInstance(properties);
////
////        return session;
////    }
////
////    private Properties getMailServerProperties() {
////        Properties properties = System.getProperties();
////        properties.put("mail.smtp.starttls.enable", "true");
////        properties.put("mail.smtp.host", "sg2plcpnl0134.prod.sin2.secureserver.net");
////        properties.put("mail.smtp.user", username);
////        properties.put("mail.smtp.password", password);
////        properties.put("mail.smtp.port", "465");
////        properties.put("mail.smtp.auth", "true");
////
////        return properties;
////    }
////}
