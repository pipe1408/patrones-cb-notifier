package com.felipe.patronescbnotifier;

public class StateHalfOpen extends State {

    public StateHalfOpen(Notifier notifier) {
        super(notifier);
    }

    @Override
    public ProviderDTO doNotify() {
        return super.doNotify();
    }

    @Override
    public void transferState() {
        super.getNotifier().setState(new StateClosed(getNotifier()));
    }
}
