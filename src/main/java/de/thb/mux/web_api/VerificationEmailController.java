package de.thb.mux.web_api;

import de.thb.mux.domain.VerificationEmail;
import de.thb.mux.service.service_impl.VerificationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class VerificationEmailController {
    @Autowired
    private VerificationEmailService verificationEmailService;

    @PostMapping(value = "/verificationEmail", consumes = "application/json")
    public ResponseEntity sendVerificationEmail(@RequestBody VerificationEmail verificationEmail) {
        try {
            verificationEmailService.sendVerificationEmail(verificationEmail);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/verifyCode", consumes = "application/json")
    public ResponseEntity verifyCode(@RequestBody VerificationEmail verificationEmail){
        if(verificationEmailService.getVerificationCode(verificationEmail).equals(verificationEmail.getVerificationCode())){
            verificationEmailService.deleteById(verificationEmail.getEmail());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}
