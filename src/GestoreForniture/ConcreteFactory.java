package GestoreForniture;

import GestioneMagazzini.Fumetto;
import GestioneMagazzini.ActionFigure;
import java.util.TreeMap;

public class ConcreteFactory extends EFactory {
    float prezzo;
    TreeMap<ActionFigure, Integer> fornituraAF = new TreeMap<>();
    TreeMap<Fumetto, Integer> fornituraFumetti = new TreeMap<>();
    GestoreForniture gestore;

    public ConcreteFactory(GestoreForniture gestore) {
        this.gestore = gestore;
    }

    @Override
    public  void creaElemento(Boolean tipo, String nome, String casaEditrice, float costo, String serie, int capitolo, Boolean special,String size, int quantita){
        if(special)
            prezzo = (float) (costo * 1.50);
        else
            prezzo = (float) (costo * 1.40);
        if (tipo) {
           Fumetto fumetto = new Fumetto(casaEditrice, prezzo, serie, capitolo);
           fornituraFumetti.put(fumetto, quantita);
           if(fornituraFumetti.size() == 50) {
               gestore.riceviOrdineFumetto(fornituraFumetti);
           }
        }
        else {
            ActionFigure actionFigure = new ActionFigure(nome, prezzo, serie, size);
            fornituraAF.put(actionFigure, quantita);
            if(fornituraAF.size() == 5) {
                gestore.riceviOrdineAF(fornituraAF);
            }
        }

    }


}