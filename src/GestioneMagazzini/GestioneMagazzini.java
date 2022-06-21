package GestioneMagazzini;


import java.ObserverPattern.Observer;
import java.ObserverPattern.Subject;
import java.util.*;
import java.lang.String;


public class GestioneMagazzini extends Subject {



    public TreeMap<Key,Integer> elementi = new TreeMap<>();
    public TreeMap<Key,Integer> elementiPrenotati = new TreeMap<>(); //codice elemento e quantità, solo se disponibili
    public TreeMap<Key,ArrayList<Integer>> prenotazioni = new TreeMap<>(); //codice elemento e codici cliente, disponibili e non
    public TreeMap<String,Integer> codiceSerie = new TreeMap<>(); //TODO
    private ArrayList<Observer> observers = new ArrayList<>(); //???
    private int countSeries = 0;
    public ArrayList<Fumetto> fumetti = new ArrayList<>();
    public ArrayList<ActionFigure> actionFigures = new ArrayList<>();


    public void addFumetto(Fumetto fumetto){
        if (codiceSerie.get(fumetto.getSerie()) == null) {
            codiceSerie.put(fumetto.getSerie(), countSeries + 50);
            countSeries++;
        }
        int codSerie = codiceSerie.get(fumetto.getSerie());
        int codCapitolo = fumetto.getCapitolo();
        Key key = new Key(codSerie,codCapitolo);
        if (prenotazioni.get(key).size() > elementiPrenotati.get(key))
            elementiPrenotati.put(key,elementiPrenotati.get(key)+1);
        else
            elementi.put(key,elementi.get(key)+1);
        fumetto.setCodice(key);
        fumetti.add(fumetto);
        //triggera l'update

    }

    public void addActionFigure(ActionFigure actionFigure){
        if (codiceSerie.get(actionFigure.getSerie()) == null) {
            codiceSerie.put(actionFigure.getSerie(), countSeries + 50);
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
        actionFigures.add(actionFigure);
    }

    public void prenotaElementi(ArrayList<Key> codici, int codiceCliente){
       for(Key k : codici){
           if(elementi.get(k)>0){   //elemento presente e non esaurito
               elementi.computeIfPresent(k,(t,v) -> v-1);
               if(elementiPrenotati.containsKey(k)) {
                   elementiPrenotati.computeIfPresent(k, (t,v) -> v + 1);
                   ArrayList<Integer> tmp = prenotazioni.get(k);
                   tmp.add(codiceCliente);
                   prenotazioni.put(k,tmp);
               }
               else{
                   elementiPrenotati.put(k,1);
                   ArrayList<Integer> tmp = new ArrayList<>();
                   tmp.add(codiceCliente);
                   prenotazioni.put(k,tmp);
               }
           }
           else{    //elemento non presente
               if(prenotazioni.containsKey(k)) {
                   ArrayList<Integer> tmp = prenotazioni.get(k);
                   tmp.add(codiceCliente);
                   prenotazioni.put(k,tmp);
               }
               else{
                   ArrayList<Integer> tmp = new ArrayList<>();
                   tmp.add(codiceCliente);
                   prenotazioni.put(k,tmp);
               }
           }
       }

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
