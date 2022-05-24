package GestioneMagazzini;


import java.ObserverPattern.Observer;
import java.ObserverPattern.Subject;
import java.util.*;
import java.lang.String;


public class GestioneMagazzini extends Subject {
    Comparator<Key> cSerieComparator = Comparator.comparing(Key::getCodiceSerie);


    public TreeMap<Key,Integer> elementi = new TreeMap<>();
    public TreeMap<Key,Integer> elementiPrenotati = new TreeMap<>(); //codice elemento e quantità
    public TreeMap<Key,ArrayList<Integer>> prenotazioni = new TreeMap<>(); //codice elemento e codici cliente
    //mappa elementi prenotati ma non disponibili
    public TreeMap<String,Integer> codiceSerie = new TreeMap<>(); //TODO
    private ArrayList<Observer> observers = new ArrayList<>(); //???
    private int countSeries = 0;
    public ArrayList<Fumetto> fumetti = new ArrayList<>();



    public void addFumetto(Fumetto fumetto){
        if (codiceSerie.get(fumetto.getSerie()) == null) {
            codiceSerie.put(fumetto.getSerie(), countSeries + 1);
            countSeries++;
        }
        int codSerie = codiceSerie.get(fumetto.getSerie());
        int codCapitolo = fumetto.getCapitolo();
        Key key = new Key(codSerie,codCapitolo);
        if (prenotazioni.containsKey(key)){
            elementiPrenotati.put(key,elementiPrenotati.get(key)+1);
        } else elementi.put(key,elementi.get(key)+1);
        fumetto.setCodice(key);
        fumetti.add(fumetto);
        //triggera l'update

    }

    public void addActionFigure(ActionFigure actionFigure){
        if (codiceSerie.get(actionFigure.getSerie()) == null) {
            codiceSerie.put(actionFigure.getSerie(), countSeries + 1);
            countSeries++;
        }
        int codSerie = codiceSerie.get(actionFigure.getSerie());
        String size = actionFigure.getSize();
        int codSize = 998;
        switch (size) {
            case "small" -> codSize = 999;
            case "large" -> codSize = 997;
        }
        Key key = new Key(codSerie,codSize);
        elementi.put(key,elementi.get(key)+1);
        actionFigure.setCodice(key);
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
