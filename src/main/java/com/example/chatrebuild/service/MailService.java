package com.example.chatrebuild.service;

import com.example.chatrebuild.tool.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JwtProvider jwtProvider;

    public void sendActivationCode(String email, String username) throws Exception{
        var message = new SimpleMailMessage();
        message.setSubject("Activate your account NO REPLY");
        message.setFrom("www.maxim2003@ukr.net");
        message.setTo(email);
        message.setText(String.format
                        (
                "Hello %s!\n" +
                        "Welcome to FirstJob! \n"+
                        "Visit next link to activate your account \n"+
                        "http://localhost:8080/activate?token=%s&login=%s",
                username,
                jwtProvider.provideToken(username),
                                username
                        )
        );
        javaMailSender.send(message);
    }

}
