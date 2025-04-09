package dev.ens.join_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/")
public class ServerController {

    @GetMapping("wakeup")
    public String wakeup() {
        return "Server is running";
    }
}
