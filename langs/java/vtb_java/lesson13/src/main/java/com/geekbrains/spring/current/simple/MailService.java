package com.geekbrains.spring.current.simple;

import org.springframework.stereotype.Component;

@Component
public class MailService {
    public void sendMail() {
        System.out.println("Письмо отправлено");
    }
}
