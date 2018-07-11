package com.shashank.ping;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PingController {

    @GetMapping(path = "/ping")
    public ResponseEntity ping(){
        return ResponseEntity.ok("pong");
    }
}
