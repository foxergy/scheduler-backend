package de.thb.mux.service.service_impl;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService {

    public String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
