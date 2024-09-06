package com.felipe.patronescbnotifier;

public class StateClosed extends State {
    private int failureCounter;

    public StateClosed(Notifier notifier) {
        super(notifier);
        setUrlProveedor("http://localhost:8081/api/");
        setNombreProveedor("Proveedor 1");
        failureCounter = 0;
    }

    @Override
    public ProviderDTO doNotify() {
        ResultDTO result = execute();
        if(!result.successful()) {
            failureCounter++;
            if(failureCounter == 3) {
                transferState("open");
            }
        }
        return result.providerDTO();
    }
}
