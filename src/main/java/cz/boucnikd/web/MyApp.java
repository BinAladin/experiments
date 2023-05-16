package cz.boucnikd.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApp.class);
        app.run(args);
    }

    @GetMapping("/hello")
    public String hello() {
        int port = Integer.parseInt(System.getProperty("server.port"));
        System.out.println("Server running on port " + port);
        return "Hello from port " + port + "!";
    }
}
