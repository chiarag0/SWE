package java.ObserverPattern;


public abstract class Subject {
    public abstract void addObserver(Observer observer);
    public abstract void removeObserver(Observer observer);
    public abstract void notifyObservers();
}
