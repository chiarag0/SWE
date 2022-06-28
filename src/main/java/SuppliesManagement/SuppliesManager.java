package SuppliesManagement;

import StockManagement.StockManager;
import StockManagement.Key;
import StockManagement.Fumetto;
import StockManagement.ActionFigure;

import java.util.*;

public class SuppliesManager {
    private ArrayList<Fornitore> fornitori;
    private StockManager stockManager;
    private GregorianCalendar calendar = new GregorianCalendar();
    private int giorno;
    private int mese;
    private TreeMap<Fumetto, Integer> ordine = new TreeMap<>();  //una volta inviato deve cancellarsi,  metodo non implementato
    private TreeMap<ActionFigure, Integer> ordineAF = new TreeMap<>();

    public SuppliesManager(ArrayList<Fornitore> fornitori, StockManager stockManager) {
        this.fornitori = fornitori;
        this.stockManager = stockManager;
        this.giorno = calendar.get(Calendar.DATE);
        this.mese = calendar.get(Calendar.MONTH) + 1;
    }

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


    private void ordinaDaFornitore() { //ordine periodico
        int numCapitolo = 0;
        int quantità = 0;
        Fumetto fumetto = null;
        for (int numSerie = 1; numSerie < 9999; numSerie++) {
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
                        if (k == f.getCodice())
                            fumetto = f;
                    }
                    if (stockManager.elementi.get(k) < 3 && k.getCodiceCapitolo() < 997) {
                        ordine.put(fumetto, quantità);
                    }
                }

            }
        }
    }

    public void ordineSpeciale(int numSerie){ // ordine in base all' interesse di fumetti e action figure
        int numCapitolo = 0;
        Key key = new Key(0,0);
        key.codiceSerie=numSerie;
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
        key.codiceCapitolo = numCapitolo;
        for (Fumetto f : stockManager.fumetti) {
            if (key == f.getCodice())
                fumetto = f;
        }
        ordine.put(fumetto, 15);
        for (int i = 997; i<1000; i++) {
            key.codiceCapitolo = i;
            for (ActionFigure af : stockManager.actionFigures) {
                if (key == af.getCodice())
                    actionFigure = af;
            }
            ordineAF.put(actionFigure, 2);
        }
    }

    protected void riceviOrdineFumetto(TreeMap<Fumetto,Integer> fornitura){
        Set fumetti = fornitura.keySet();
        for (Iterator i = fumetti.iterator(); i.hasNext(); ) {
            Fumetto fumetto = (Fumetto) i.next();
            Integer quantita = (Integer) fornitura.get(fumetto);
            for (int j = 0; j < quantita; j++)
                stockManager.addFumetto(fumetto);
        }
    }
    protected void riceviOrdineAF(TreeMap<ActionFigure,Integer> fornitura){
        Set actionFigures = fornitura.keySet();
        for (Iterator i = actionFigures.iterator(); i.hasNext(); ) {
            ActionFigure actionFigure = (ActionFigure) i.next();
            Integer quantita = (Integer) fornitura.get(actionFigure);
            for (int j = 0; j < quantita; j++)
                stockManager.addActionFigure(actionFigure);
        }
    }

    private void riceviNotifica(){

    }
}