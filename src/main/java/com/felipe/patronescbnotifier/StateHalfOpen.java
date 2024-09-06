package com.felipe.patronescbnotifier;

public class StateHalfOpen extends State {
    private int successCounter;

    public StateHalfOpen(Notifier notifier) {
        super(notifier);
        setUrlProveedor("http://localhost:8081/api/");
        setNombreProveedor("Proveedor 1 (Probando)");
        successCounter = 0;
    }

    @Override
    public ProviderDTO doNotify() {
        ResultDTO result = execute();
        if(!result.successful()) {
            transferState("open");
        } else {
            successCounter++;
            if(successCounter == 3) {
                transferState("closed");
            }
        }
        return result.providerDTO();
    }
}
