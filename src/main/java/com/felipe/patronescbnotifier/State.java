package com.felipe.patronescbnotifier;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public abstract class State {
    private final Notifier notifier;
    private final RestClient restClient;
    private String urlProveedor;
    private String nombreProveedor;

    public State(Notifier notifier) {
        this.notifier = notifier;
        restClient = RestClient.create();
    }

    public void setUrlProveedor(String urlProveedor) {
        this.urlProveedor = urlProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public ResultDTO execute() {
        ResponseEntity<String> responseEntity;
        HttpStatusCode statusCode;
        String body;
        int retries;
        ProviderDTO providerDTO;

        for (retries = 0; retries < 5; retries++) {
            try {
                responseEntity = restClient.get().uri(urlProveedor + "/notificar").retrieve().toEntity(String.class);
                statusCode = responseEntity.getStatusCode();
                body = responseEntity.getBody();

                if (statusCode.is2xxSuccessful()) {
                    providerDTO = DTOGenerator.generateProviderDTO(nombreProveedor, statusCode.toString(), body, retries);
                    return DTOGenerator.generateResultDTO(true, providerDTO);
                }
            } catch (Exception e) {
                System.out.println("Transacción fallida. Reintentos: " + retries);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        providerDTO = DTOGenerator.generateProviderDTO(nombreProveedor, "500", "-", retries);
        return DTOGenerator.generateResultDTO(false, providerDTO);
    }

    public abstract ProviderDTO doNotify();
    public void transferState(String state) {
        if ("closed".equals(state)) {
            notifier.setState(new StateClosed(notifier));
        } else if ("open".equals(state)) {
            notifier.setState(new StateOpen(notifier));
        } else {
            notifier.setState(new StateHalfOpen(notifier));
        }
    }
}