package GestioneMagazzini;

public class Key {
    private int codiceSerie;
    private int codiceCapitolo;
    public Key(int codiceSerie, int codiceCapitolo){
        codiceSerie= codiceSerie;
        codiceCapitolo= codiceCapitolo;
    }

    public int getCodiceSerie() {
        return codiceSerie;
    }
}
