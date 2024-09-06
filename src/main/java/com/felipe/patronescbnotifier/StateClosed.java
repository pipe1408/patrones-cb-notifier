package com.felipe.patronescbnotifier;

public class StateClosed extends State {

    public StateClosed(Notifier notifier) {
        super(notifier);
    }

    @Override
    public ProviderDTO doNotify() {
        return super.doNotify();
    }

    @Override
    public void transferState() {
        super.getNotifier().setState(new StateOpen(getNotifier()));
    }
}
