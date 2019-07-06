package de.thb.mux.service.service_impl;

import de.thb.mux.data_access.VerificationEmailRepository;
import de.thb.mux.domain.VerificationEmail;
import de.thb.mux.service.service_api.VerificationEmailApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Service
public class VerificationEmailService implements VerificationEmailApi {
    @Value("${Sender.gmail.username}")
    private String SenderUserEmail;
    @Value("${Sender.gmail.password}")
    private String SenderUserPassword;

    @Autowired
    private VerificationEmailRepository verificationEmailRepository;

    @Override
    public Collection<VerificationEmail> findAll() {
        return verificationEmailRepository.findAll();
    }

    @Override
    public VerificationEmail findByID(String email) {
        return verificationEmailRepository.getOne(email);
    }

    @Override
    public VerificationEmail create(VerificationEmail verificationEmail) {
        return verificationEmailRepository.save(verificationEmail);
    }

    @Override
    public VerificationEmail update(VerificationEmail verificationEmail) throws EntityNotFoundException {
        if(!exists(verificationEmail.getEmail())){
            throw new EntityNotFoundException("The Email "+verificationEmail.getEmail()+" does not exist");
        }else {
            return verificationEmailRepository.save(verificationEmail);
        }
    }

    @Override
    public Boolean deleteById(String email) throws EntityNotFoundException {
        if(!exists(email)) {
            throw new EntityNotFoundException("The Email "+ email +" does not exist");
        }else {
            verificationEmailRepository.deleteById(email);
            return findByID(email)==null;
        }
    }

    @Override
    public Boolean exists(String email) {
        return findByID(email)!=null;
    }

    public Integer getVerificationCode(VerificationEmail verificationEmail){
        if(!exists(verificationEmail.getEmail())){
            throw new EntityNotFoundException("The Email "+ verificationEmail.getEmail() +" does not exist");
        }else{
            return findByID(verificationEmail.getEmail()).getVerificationCode();
        }
    }



    public void sendVerificationEmail(VerificationEmail verificationEmail) throws IOException, MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.username", SenderUserEmail);
        properties.put("mail.smtp.password", SenderUserPassword);
        properties.put("mail.smtp.port", "465");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(SenderUserEmail, SenderUserPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SenderUserEmail, false));

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(verificationEmail.getEmail()));
        message.setSubject("Please Verify your Email");
        //message.setContent("<h3>Email Verification</h3>\n", "text/html");
        message.setSentDate(new Date());

        Integer verificationCode = genRandom();

        // just for attachment
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h3>Email Verification</h3><br> <p>Hallo " + verificationEmail.getName() + ",</p>\n" +
                "<p>Please provide the following code to verify your Email Address</p>\n" +
                "<b><pre>"+ verificationCode +"</pre></b>" + "</br></br><p>SchedulerApp</p>" +
                "<br>" + getEmailDomain(verificationEmail.getEmail()) , "text/html");

        //---------------------------
        verificationEmail.setVerificationCode(verificationCode);
        create(verificationEmail);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachPart = new MimeBodyPart();
        File image = new ClassPathResource("images/image.png").getFile();
        attachPart.attachFile(image);
        //attachPart.attachFile("C:\\images\\image.png");

        multipart.addBodyPart(attachPart);
        message.setContent(multipart);
        Transport.send(message);

    }

    public String getEmailDomain(String email)
    {
        return  email.substring(email.indexOf("@") + 1);
    }

    private int genRandom() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

}

