package com.felipe.patronescbnotifier;

public class StateOpen implements State {

    public StateOpen(Notifier notifier) {
    }

    @Override
    public ProviderDTO doNotify() {
        return null;
    }

    @Override
    public String doPing() {
        return "";
    }
}
