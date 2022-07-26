package com.example.demo;

//import com.example.demo.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Demo2Application {
    @Autowired
//    private EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        senderService.sendEmail("");
//    }
}
