package SuppliesManagement;

import ComunicazioneCliente.AttivitàClienti;
import ComunicazioneCliente.Cliente;
import StockManagement.StockManager;
import StockManagement.Key;
import StockManagement.Fumetto;
import StockManagement.ActionFigure;

import java.util.*;

public class SuppliesManager extends Observable{
  //  private GregorianCalendar calendar = new GregorianCalendar();
  //  private int giorno;
  //  private int mese;
    TreeMap<Fumetto, Integer> ordine = new TreeMap<>();  //una volta inviato deve cancellarsi,  metodo non implementato
    private TreeMap<ActionFigure, Integer> ordineAF = new TreeMap<>();
    public TreeMap<Integer, ArrayList<Cliente>> iscrizioni = new TreeMap<>();
    public AttivitàClienti ac ;
    public StockManager stockManager = StockManager.getInstance();
    private static SuppliesManager instance = null;
    private ConcreteFactory factory;

    private SuppliesManager(AttivitàClienti ac) {
        this.ac = ac;
        factory = new ConcreteFactory(this);
        //this.giorno = calendar.get(Calendar.DATE);
       // this.mese = calendar.get(Calendar.MONTH) + 1;
    }

    static public SuppliesManager getInstance(AttivitàClienti ac) {
        if (instance != null)
            return instance;
        else {
            instance = new SuppliesManager(ac);
            return instance;
        }
    }
/*
    private void controllaGiorno() {
        if (mese == (calendar.get(Calendar.MONTH) + 1)) {
            if (giorno - (calendar.get(Calendar.DATE)) >= 7)
                ordinaDaFornitore();
        } else {
            switch (mese) {
                case 1, 3, 5, 7, 8, 10, 12:
                    if (giorno - (calendar.get(Calendar.DATE) + 31) >= 7)
                        ordinaDaFornitore();
                    break;
                case 4, 6, 9, 11:
                    if (giorno - (calendar.get(Calendar.DATE) + 30) >= 7)
                        ordinaDaFornitore();
                    break;
                case 2:
                    if (giorno - (calendar.get(Calendar.DATE) + 28) >= 7)
                        ordinaDaFornitore();
                    break;
            }
        }
    }


 */
    void ordinaDaFornitore() { //ordine periodico
        String serie = "";
        int numCapitolo = 0;
        int quantità = 0;
        Fumetto fumetto = null;
        ordine.clear();
        for (int numSerie = 50; numSerie < 10000; numSerie += 50) {
            for (Key k : stockManager.elementi.keySet()) {
                if (numSerie == k.getCodiceSerie()) {
                    numCapitolo = 0;
                    for (Key k1 : stockManager.elementi.keySet()) {
                        if (numSerie == k1.getCodiceSerie()) {
                            if (numCapitolo < k1.getCodiceCapitolo() && k1.getCodiceCapitolo() < 997) //trova l'ultimo capitolo che non è un'action figure
                                numCapitolo = k1.getCodiceCapitolo();
                        }
                    }
                    int dist = numCapitolo - k.getCodiceCapitolo();
                    if (dist <= 10)
                        quantità = 10;
                    if (dist > 10 && dist < 50)
                        quantità = 5;
                    if (dist >= 50)
                        quantità = 3;

                    for (Fumetto f : stockManager.fumetti) {
                        if (k == f.getCodice()) {
                            fumetto = f;
                            serie = f.getSerie();
                        }
                    }
                    if (stockManager.elementi.get(k) < 3 && k.getCodiceCapitolo() < 997) {
                        ordine.put(fumetto, quantità);
                    }
                }

            }
            //invia ordine a fornitore
            Key key = new Key(numSerie,numCapitolo + 1);
            if(Math.random() > 0.5){
                stockManager.elementi.put(key,0);
                factory.creaElemento(true,false,true,"","Panini",4,serie, key.getCodiceCapitolo(),false,"", 0);
                ArrayList<Cliente> observers = new ArrayList<>();
                Set keys = iscrizioni.keySet();
                for (Iterator i = keys.iterator(); i.hasNext(); ) {
                    int chiave = (int) i.next();
                    if (chiave == (fumetto.getCodice().getCodiceSerie())){
                        observers = iscrizioni.get(key.getCodiceSerie());
                    }
                }
                if(observers != null)
                    notifyObservers(observers, numSerie);
            }
        }
    }

    public void ordineSpeciale(int numSerie){ // ordine in base all' interesse di fumetti e action figure
        int numCapitolo = 0;
        Key key = new Key(numSerie,0);
        Fumetto fumetto = null;
        ActionFigure actionFigure = null;
        for (Key k : stockManager.elementi.keySet()) {
            if (numSerie == k.getCodiceSerie()) {
                numCapitolo = 0;
                for (Key k1 : stockManager.elementi.keySet()) {
                    if (numSerie == k1.getCodiceSerie()) {
                        if (numCapitolo < k1.getCodiceCapitolo() && k1.getCodiceCapitolo() < 997) //trova l'ultimo capitolo che non è un'action figure
                            numCapitolo = k1.getCodiceCapitolo();
                    }
                }
            }
        }
        key.setCodiceCapitolo(numCapitolo);
        for (Fumetto f : stockManager.fumetti) {
            if (key == f.getCodice())
                fumetto = f;
        }
        ordine.put(fumetto, 15);
        for (int i = 997; i<1000; i++) {
            key.setCodiceCapitolo(i);
            for (ActionFigure af : stockManager.actionFigures) {
                if (key == af.getCodice())
                    actionFigure = af;
            }
            ordineAF.put(actionFigure, 2);
        }
    }

    protected void riceviOrdineFumetto(TreeMap<Fumetto,Integer> fornitura) {
        Set fumetti = fornitura.keySet();
        for (Iterator i = fumetti.iterator(); i.hasNext(); ) {
            Fumetto fumetto = (Fumetto) i.next();
            Integer quantita = fornitura.get(fumetto);
            for (int j = 0; j < quantita; j++)
                stockManager.addFumetto(fumetto);
        }
    }

    protected void riceviOrdineAF(TreeMap<ActionFigure,Integer> fornitura){
        Set actionFigures = fornitura.keySet();
        for (Iterator i = actionFigures.iterator(); i.hasNext(); ) {
            ActionFigure actionFigure = (ActionFigure) i.next();
            Integer quantita = fornitura.get(actionFigure);
            for (int j = 0; j < quantita; j++)
                stockManager.addActionFigure(actionFigure);
        }
    }

    private void riceviNotifica(String titoloSerie){
        stockManager.setCountSeries();
        stockManager.codiceSerie.put(titoloSerie, stockManager.getCountSeries());
        Key key = new Key(stockManager.getCountSeries(), 1);
        stockManager.elementi.put(key, 0);
        ac.notifyNewSeries(titoloSerie);
    }

    public void notifyObservers(ArrayList<Cliente> observers, int numSerie) {
        String titoloSerie = null;
        Set keys = stockManager.codiceSerie.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            String titolo = (String) i.next();
            if (numSerie == stockManager.codiceSerie.get(titolo)) {
                titoloSerie = titolo;
            }
        }
        for(Observer o: observers) {
            o.update(this, titoloSerie);
        }
    }
}
