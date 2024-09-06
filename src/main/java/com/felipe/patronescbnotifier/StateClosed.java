package com.felipe.patronescbnotifier;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class StateClosed implements State {
    private final Notifier notifier;
    private final String urlProveedor;
    private final RestClient restClient;
    private ResponseEntity<String> responseEntity;

    public StateClosed(Notifier notifier) {
        this.notifier = notifier;
        this.restClient = RestClient.create();
        this.urlProveedor = "http://localhost:8081/api";

        try {
            responseEntity = restClient.get().uri(urlProveedor + "/notificar").retrieve().toEntity(String.class);
        } catch (Exception e) {
            System.out.println("Fallo");
        }
    }

    @Override
    public ProviderDTO doNotify() {
        HttpStatusCode statusCode = null;
        String body = null;
        int retries;
        for (retries = 0; retries < 5; retries++) {

            try {
                responseEntity = restClient.get().uri(urlProveedor + "/notificar").retrieve().toEntity(String.class);
                statusCode = responseEntity.getStatusCode();
                body = responseEntity.getBody();
                if (statusCode.is2xxSuccessful()) {
                    return new ProviderDTO("Proveedor 1", statusCode.toString(), body, retries);
                }
            } catch (Exception e) {
                System.out.println("Fallo");
            }

            System.out.println("Transacción fallida. Reintentos: " + retries);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        //notifier.setState(new StateOpen(notifier));

        try {
            return new ProviderDTO("Proveedor 1", statusCode.toString(), "-", retries);
        } catch (Exception e) {
            System.out.println("Fallo");
        }
        return new ProviderDTO("Proveedor 1", "statusCode.toString()", "-", retries);
    }

    @Override
    public String doPing() {
        return "";
    }
}
