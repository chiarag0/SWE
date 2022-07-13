package StockManagement;

import ComunicazioneCliente.Cliente;

import java.util.*;
import java.lang.String;


public class StockManager extends Observable{
    public TreeMap<Key,Integer> elementi = new TreeMap<>();
    public TreeMap<Key,Integer> elementiPrenotati = new TreeMap<>(); //codice elemento e quantità, solo se disponibili
    public TreeMap<Key,ArrayList<Cliente>> prenotazioni = new TreeMap<>(); //codice elemento e codici cliente(observer), disponibili e non
    public TreeMap<String,Integer> codiceSerie = new TreeMap<>();
    public ArrayList<Fumetto> fumetti = new ArrayList<>();
    public ArrayList<ActionFigure> actionFigures = new ArrayList<>();
    private int countSeries ;
    private static StockManager instance = null;

    public int getCountSeries() {
        return countSeries;
    }

    public void setCountSeries() {
        this.countSeries += 50;
    }

    private StockManager() {
        this.countSeries = 0;
    }

    static public StockManager getInstance() {
        if (instance != null)
            return instance;
        else{
            instance = new StockManager();
            return instance;
        }
    }

    public void addFumetto(Fumetto fumetto){
        int codSerie = codiceSerie.get(fumetto.getSerie());
        int codCapitolo = fumetto.getCapitolo();
        Key key = new Key(codSerie,codCapitolo);
        if (prenotazioni.get(key).size() > elementiPrenotati.get(key))
            elementiPrenotati.put(key,elementiPrenotati.get(key)+1);
        else
            elementi.put(key,elementi.get(key)+1);
        ArrayList<Cliente> observers = new ArrayList<>();
        Set keys = prenotazioni.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            Key chiave = (Key) i.next();
            if (chiave == fumetto.getCodice()) {
                observers = prenotazioni.get(key);
            }
        }
        notifyObservers(observers, fumetto);
        fumetto.setCodice(key);
        if(fumetti.contains(fumetto))
            fumetti.add(fumetto);
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

    public void prenotaElementi(ArrayList<Key> codici, Cliente cliente){ //add observer
       for(Key k : codici){
           if(elementi.get(k)>0){   //elemento presente e non esaurito
               elementi.computeIfPresent(k,(t,v) -> v-1);
               if(elementiPrenotati.containsKey(k)) {
                   elementiPrenotati.computeIfPresent(k, (t,v) -> v + 1);
                   ArrayList<Cliente> tmp = prenotazioni.get(k);
                   tmp.add(cliente);
                   prenotazioni.put(k,tmp);
               }
               else{
                   elementiPrenotati.put(k,1);
                   ArrayList<Cliente> tmp = new ArrayList<>();
                   tmp.add(cliente);
                   prenotazioni.put(k,tmp);
               }
           }
           else{    //elemento non presente
               if(prenotazioni.containsKey(k)) {
                   ArrayList<Cliente> tmp = prenotazioni.get(k);
                   tmp.add(cliente);
                   prenotazioni.put(k,tmp);
               }
               else{
                   ArrayList<Cliente> tmp = new ArrayList<>();
                   tmp.add(cliente);
                   prenotazioni.put(k,tmp);
               }
           }
       }

    }


    public void soldElement(Key codiceElemento){
        elementi.put(codiceElemento, elementi.get(codiceElemento)-1);
    }

    public void notifyObservers(ArrayList<Cliente> observers, Fumetto fumetto) {
        for(Observer o: observers) {
            o.update(this,fumetto);
        }
    }
}
