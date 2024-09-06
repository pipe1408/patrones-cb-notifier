package com.felipe.patronescbnotifier;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class StateClosed implements State {
    private final Notifier notifier;
    private final String urlProveedor;
    private final RestClient restClient;

    public StateClosed(Notifier notifier) {
        this.notifier = notifier;
        this.restClient = RestClient.create();
        this.urlProveedor = "http://localhost:8081/api";
    }

    @Override
    public ProviderDTO doNotify() {
        HttpStatusCode statusCode;
        String body;
        int retries;

        for (retries = 0; retries < 5; retries++) {
            try {
                ResponseEntity<String> responseEntity = restClient.get().uri(urlProveedor + "/notificar").retrieve().toEntity(String.class);
                statusCode = responseEntity.getStatusCode();
                body = responseEntity.getBody();

                if (statusCode.is2xxSuccessful()) {
                    return new ProviderDTO("Proveedor 1", statusCode.toString(), body, retries);
                }
            } catch (Exception e) {
                System.out.println("Transacción fallida. Reintentos: " + retries);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        notifier.setState(new StateOpen(notifier));
        return new ProviderDTO("Proveedor 1", "ERR", "FAILED", retries);
    }

    @Override
    public void doPing() {
    }
}
