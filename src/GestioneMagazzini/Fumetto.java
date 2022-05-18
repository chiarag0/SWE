package GestioneMagazzini;
//TODO aggiungi codice

public class Fumetto implements Elemento {
    private String titolo;
    private String casaEditrice;
    private float prezzo;
    private String serie;
    private int capitolo;

    public Fumetto(String titolo, String casaEditrice, float prezzo, String serie, int capitolo) {
        this.titolo = titolo;
        this.casaEditrice = casaEditrice;
        this.prezzo = prezzo;
        this.serie = serie;
        this.capitolo = capitolo;
    }
}
