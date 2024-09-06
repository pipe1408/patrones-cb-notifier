package com.felipe.patronescbnotifier;

public interface State {
    ProviderDTO doNotify();
    void doPing();
}