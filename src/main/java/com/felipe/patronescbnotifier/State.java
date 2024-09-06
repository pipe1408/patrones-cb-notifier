package com.felipe.patronescbnotifier;

public interface State {
    ProviderDTO doNotify();
    String doPing();
}