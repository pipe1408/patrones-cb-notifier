package com.felipe.patronescbnotifier;

public class StateOpen extends State {

    public StateOpen(Notifier notifier) {
        super(notifier);
    }

    @Override
    public ProviderDTO doNotify() {
        return super.doNotify();
    }

    @Override
    public void transferState() {
        super.getNotifier().setState(new StateHalfOpen(getNotifier()));
    }
}
