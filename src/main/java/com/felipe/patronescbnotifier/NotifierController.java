package com.felipe.patronescbnotifier;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotifierController {
    private final Notifier notifier;

    public NotifierController(Notifier notifier) {
        this.notifier = notifier;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/notificar/{requestTime}")
    public ResponseDTO notifyRequest(@PathVariable String requestTime) {
        return notifier.notificar(requestTime);
    }
}
