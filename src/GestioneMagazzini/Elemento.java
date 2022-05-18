package GestioneMagazzini;

//TODO fai costruttori per tutto


public abstract class Elemento {
    private float prezzo;
    private String serie;
    private Key codice = null;

    public Elemento(float prezzo, String serie) {
        this.prezzo = prezzo;
        this.serie = serie;
    }
    public void setCodice(Key codice) {
        this.codice = codice;
    }

    public String getSerie() {
        return serie;
    }


}
