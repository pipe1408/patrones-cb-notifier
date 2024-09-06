package com.felipe.patronescbnotifier;

public interface State {
    ProviderDTO doNotify();
    public void doPing();
}