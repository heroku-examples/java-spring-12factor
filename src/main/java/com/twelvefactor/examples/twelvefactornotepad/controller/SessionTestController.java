package com.twelvefactor.examples.twelvefactornotepad.controller;

import jakarta.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
public class SessionTestController {

    private static final String SESSION_COUNTER_KEY = "sessionCounter";

    @GetMapping("/test")
    public String testSession(HttpSession session) {
        Integer counter = (Integer) session.getAttribute(SESSION_COUNTER_KEY);
        if (counter == null) {
            counter = 0;
        }
        counter++;
        session.setAttribute(SESSION_COUNTER_KEY, counter);

        String hostname = "Unknown";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            // Log error or handle, for now, keep it simple
        }

        return String.format("Counter: %d, Session ID: %s, Processed by Host: %s", counter, session.getId(), hostname);
    }
}
