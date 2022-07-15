package StockManagement;


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
        String a = Integer.toString(codice.getCodiceCapitolo());
        String b = serie+a;
        String a1 = Integer.toString(e.getCodice().getCodiceCapitolo());
        String b1 = e.getSerie()+a1;
        return String.CASE_INSENSITIVE_ORDER.compare(b,b1);
    }
}
