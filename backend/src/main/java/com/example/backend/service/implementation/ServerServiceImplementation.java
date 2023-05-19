package com.example.backend.service.implementation;

import com.example.backend.enumeration.Status;
import com.example.backend.model.Server;
import com.example.backend.repository.ServerRepository;
import com.example.backend.service.ServerService;
import jakarta.servlet.Servlet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor // Create a Constructor
@Service // Service Class
@Transactional // Transactional
@Slf4j // Print in console
public class ServerServiceImplementation implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Creating New Server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching All Servers");
        return serverRepository.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching Server: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting ServerID: {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    @SneakyThrows
    @Override
    public Server ping(String ipAddress) {
        log.info("Pinging Server at: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        server.setStatus(inetAddress.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    private String setServerImageUrl() {
        String[] imageNames = {
                "Server1.png",
                "Server2.png",
                "Server3.png",
                "Server4.png",
        };

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
