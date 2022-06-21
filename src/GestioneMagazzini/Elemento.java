package GestioneMagazzini;

//TODO fai costruttori per tutto


public abstract class Elemento implements Comparable<Elemento> {
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

    public Key getCodice() {
        return codice;
    }

    @Override
    public int compareTo(Elemento e) {
        return String.CASE_INSENSITIVE_ORDER.compare(getSerie(),e.getSerie());
    }
}
