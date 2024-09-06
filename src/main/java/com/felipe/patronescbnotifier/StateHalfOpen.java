package com.felipe.patronescbnotifier;

public class StateHalfOpen implements State {

    @Override
    public ProviderDTO doNotify() {
        return null;
    }

    @Override
    public String doPing() {
        return "";
    }
}
