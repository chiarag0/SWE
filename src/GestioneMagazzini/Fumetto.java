package GestioneMagazzini;

public class Fumetto implements Elemento {
    private String titolo;
    private String casaEditrice;
    private float prezzo;
    private String serie;

    public Fumetto(String titolo, String casaEditrice, float prezzo, String serie) {
        this.titolo = titolo;
        this.casaEditrice = casaEditrice;
        this.prezzo = prezzo;
        this.serie = serie;
    }
}
