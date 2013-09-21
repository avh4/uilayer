package net.avh4.framework.uilayer.mvc;

import org.pcollections.PVector;
import org.pcollections.TreePVector;

public abstract class Channel<T> {
    private PVector<Observer> observers = TreePVector.empty();

    protected synchronized void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public abstract T get();

    public synchronized void watch(Observer observer) {
        final T currentValue = get();
        observers = observers.plus(observer);
        if (currentValue != null) {
            observer.update();
        }
    }
}
