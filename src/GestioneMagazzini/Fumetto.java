package GestioneMagazzini;
//TODO aggiungi codice

public class Fumetto extends Elemento implements Comparable<Fumetto> {
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


    @Override
    public int compareTo(Fumetto f) {
        return String.CASE_INSENSITIVE_ORDER.compare(getSerie(),f.getSerie());
    }
}
