package Observerable;

import java.util.ArrayList;
import java.util.List;

public abstract class Observerable<T> {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(T e){
        observers.forEach((o) -> {
            o.onChanged(this, e);
        } );
    }
}
