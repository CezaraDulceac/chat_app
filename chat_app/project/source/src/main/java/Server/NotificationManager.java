package Server;

import Model.Notificare;

import java.util.Observable;

public class NotificationManager extends Observable {

    private Notificare watchedValue;
    public void ObservedObject(Notificare value) {
        watchedValue = value;
    }

    public synchronized void setValue(Notificare value) {
        watchedValue = value;
        setChanged();
        notifyObservers(value);
    }
}
