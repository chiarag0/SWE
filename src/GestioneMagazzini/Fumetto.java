package GestioneMagazzini;
//TODO aggiungi codice

import java.util.ArrayList;
import java.util.Observer;

public class Fumetto extends Elemento {
    private String casaEditrice;
    private final int capitolo;

    public Fumetto(String casaEditrice, float prezzo, String serie, int capitolo) {
        super(prezzo,serie);
        this.casaEditrice = casaEditrice;
        this.capitolo = capitolo;
    }

    public int getCapitolo() {
        return capitolo;
    }

}
