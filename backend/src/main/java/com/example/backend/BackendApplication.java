package com.example.backend;

import com.example.backend.enumeration.Status;
import com.example.backend.model.Server;
import com.example.backend.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ServerRepository serverRepository) {
        return args -> {
            serverRepository.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
            serverRepository.save(new Server(null, "192.168.1.58", "Windows 11", "16 GB", "Dell Tower", "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));
            serverRepository.save(new Server(null, "192.168.1.21", "Fedora Linux", "32 GB", "Website Browser", "http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
            serverRepository.save(new Server(null, "192.168.1.14", "Red Hat Linux", "64 GB", "Mailing Server", "http://localhost:8080/server/image/server4.png", Status.SERVER_UP));
        };
    }

}
