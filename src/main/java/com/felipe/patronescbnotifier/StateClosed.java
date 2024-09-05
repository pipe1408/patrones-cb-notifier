package com.felipe.patronescbnotifier;

public class StateClosed implements State {

    @Override
    public String doNotify() {
        return "";
    }

    @Override
    public String doPing() {
        return "";
    }
}
