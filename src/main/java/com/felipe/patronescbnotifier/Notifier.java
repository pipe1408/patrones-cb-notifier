package com.felipe.patronescbnotifier;

import org.springframework.stereotype.Service;

@Service
public class Notifier {
    private State state = new StateClosed(this);

    public void setState(State state) {
        this.state = state;
    }

    public ResponseDTO notificar(String requestTime) {
        ProviderDTO responseTime = this.state.doNotify();
        return ResponseGenerator.generateResponseDTO(responseTime ,requestTime);
    }
}
