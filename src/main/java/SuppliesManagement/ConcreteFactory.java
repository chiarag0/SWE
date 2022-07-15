package SuppliesManagement;

import StockManagement.Fumetto;
import StockManagement.ActionFigure;
import StockManagement.Key;
import StockManagement.StockManager;

import java.util.TreeMap;

public class ConcreteFactory extends EFactory {
    float prezzo;
    TreeMap<ActionFigure, Integer> fornituraAF = new TreeMap<>();
    TreeMap<Fumetto, Integer> fornituraFumetti = new TreeMap<>();
    SuppliesManager gestore;
    StockManager magazzino = StockManager.getInstance();

    public ConcreteFactory(SuppliesManager gestore) {
        this.gestore = gestore;
    }

    @Override
    public void creaElemento(Boolean nuovo, Boolean end, Boolean tipo, String nome, String casaEditrice, float costo, String serie, int capitolo, Boolean special,String size, int quantita) {
        if(special)
            prezzo = (float) (costo * 1.50);
        else
            prezzo = (float) (costo * 1.40);

        if (tipo) {
            Key codice = new Key(magazzino.codiceSerie.get(serie),capitolo);
            Fumetto fumetto = new Fumetto(casaEditrice, prezzo, serie, capitolo);
            fumetto.setCodice(codice);
            if(!nuovo) {
                fornituraFumetti.put(fumetto, quantita);
                if (end) {
                    gestore.riceviOrdineFumetto(fornituraFumetti);
                    fornituraFumetti.clear();
                }
            }else
                magazzino.addNew(fumetto);
        }
        else {
            ActionFigure actionFigure = new ActionFigure(nome, prezzo, serie, size);
            int codSerie = magazzino.codiceSerie.get(actionFigure.getSerie());
            int codSize = 998;
            switch (actionFigure.getSize()) {
                case "small" -> codSize = 999;
                case "large" -> codSize = 997;
            }
            Key key = new Key(codSerie,codSize);
            actionFigure.setCodice(key);
            fornituraAF.put(actionFigure, quantita);
            if(end)
                gestore.riceviOrdineAF(fornituraAF);
        }

    }


}