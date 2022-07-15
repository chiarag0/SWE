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

    public int getCountSeries() {
        return countSeries;
    }

    public void setCountSeries() {
        this.countSeries += 50;
    }

    public void addFumetto(Fumetto fumetto){
        int codSerie;
        int codCapitolo = fumetto.getCapitolo();
        if (!codiceSerie.containsKey(fumetto.getSerie())) {
            codiceSerie.put(fumetto.getSerie(), countSeries + 50);
            countSeries += 50;
        }
        codSerie = codiceSerie.get(fumetto.getSerie());
        Key key = new Key(codSerie, codCapitolo);
        fumetto.setCodice(key);
        if (!prenotazioni.containsKey(key) && !elementiPrenotati.containsKey(key) || prenotazioni.get(key).size()==0) {
            if(elementi.containsKey(key))
                elementi.put(key, elementi.get(key) + 1);
            else elementi.put(key, 1);
        }else{
            if(!elementiPrenotati.containsKey(key) || prenotazioni.get(key).size() > elementiPrenotati.get(key)){
                if(!elementiPrenotati.containsKey(key))
                    elementiPrenotati.put(key, 1);
                else
                    elementiPrenotati.put(key, elementiPrenotati.get(key)+1);
                if(prenotazioni.get(key).size() == elementiPrenotati.get(key)){
                    ArrayList<Cliente> observers = new ArrayList<>();
                    Set keys = prenotazioni.keySet();
                    for (Iterator i = keys.iterator(); i.hasNext(); ) {
                        Key chiave = (Key) i.next();
                        if (chiave.equals(fumetto.getCodice()))
                            observers = prenotazioni.get(key);
                    }
                    notifyObservers(observers, fumetto);
                }
            }
            else {
                if(elementi.containsKey(key))
                    elementi.put(key, elementi.get(key) + 1);
                else elementi.put(key, 1);
            }
        }
        boolean match = Boolean.FALSE;
        for (Fumetto f : fumetti) {
            if (fumetto.getCodice().equals(f.getCodice())) {
                match = Boolean.TRUE;
                break;
            }
        }
        if(!match)
            fumetti.add(fumetto);
    }

    public void addActionFigure(ActionFigure actionFigure){
        if (codiceSerie.get(actionFigure.getSerie()) == null) {
            codiceSerie.put(actionFigure.getSerie(), countSeries + 50);
            countSeries += 50;
        }
        if(elementi.containsKey(actionFigure.getCodice()))
            elementi.put(actionFigure.getCodice(),elementi.get(actionFigure.getCodice())+1);
        else
            elementi.put(actionFigure.getCodice(),1);
        actionFigures.add(actionFigure);
    }

    public void prenotaElementi(ArrayList<Key> codici, Cliente cliente){
       for(Key k : codici){
           if(elementi.get(k)>0){   //elemento presente e non esaurito
               cliente.prenotazioni.put(k, Boolean.TRUE);
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
               cliente.prenotazioni.put(k, Boolean.FALSE);
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

    public void addNew(Fumetto fumetto) {
        fumetti.add(fumetto);
    }
}
