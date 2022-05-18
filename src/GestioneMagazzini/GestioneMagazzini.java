package GestioneMagazzini;


import java.ObserverPattern.Observer;
import java.ObserverPattern.Subject;
import java.util.*;
import java.lang.String;


public class GestioneMagazzini extends Subject {
    Comparator<Key> cSerieComparator = Comparator.comparing(Key::getCodiceSerie);


    public TreeMap<Key,Integer> elementi = new TreeMap<>();
    public TreeMap<Integer,Integer> elementiPrenotati = new TreeMap<>(); //codice serie+capitolo e quantità
    public TreeMap<Integer,Integer> prenotazioni = new TreeMap<>(); //codice elemento e codice cliente
    //mappa elementi prenotati ma non disponibili
    public TreeMap<Integer,String> codiceTitolo = new TreeMap<>(); //TODO
    private ArrayList<Observer> observers = new ArrayList<>();



    public void addElemento(Elemento elemento){

        //TODO si aggiungono ad una mappa secondo il codice di serie
        //triggera l'update
        // 2 casi: inventario oppure prenotati
    }


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
