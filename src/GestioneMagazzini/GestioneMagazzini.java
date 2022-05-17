package GestioneMagazzini;


import java.ObserverPattern.Observer;
import java.ObserverPattern.Subject;
import java.util.ArrayList;
import java.lang.String;


public class GestioneMagazzini extends Subject {

    public static ArrayList<Elemento> elementi = new ArrayList<>();
    public ArrayList<Elemento> elementiPrenotati = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }
}
