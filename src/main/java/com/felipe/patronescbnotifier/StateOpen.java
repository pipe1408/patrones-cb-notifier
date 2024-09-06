package com.felipe.patronescbnotifier;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StateOpen extends State {

    public StateOpen(Notifier notifier) {
        super(notifier);
        setUrlProveedor("http://localhost:8082/api/");
        setNombreProveedor("Proveedor 2");
        int timeout = 10;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> transferState("half"), timeout, TimeUnit.SECONDS);
    }

    @Override
    public ProviderDTO doNotify() {
        return execute().providerDTO();
    }
}
