package com.felipe.patronescbnotifier;

public class StateClosed implements State {
    @Override
    public String onNotificar() {
        return "";
    }

    @Override
    public String onPing() {
        return "";
    }
}
