package org.example.projektuno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProjektUnoApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProjektUnoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void execCodeAfterStartup() {

    }
}
