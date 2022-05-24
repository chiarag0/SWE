
package GestioneMagazzini;

public class Key implements Comparable<Key>{
    public int codiceSerie = 0;
    public int codiceCapitolo = 0;

    public Key(int codiceSerie, int codiceCapitolo) {
        this.codiceSerie = codiceSerie;
        this.codiceCapitolo = codiceCapitolo;
    }

    public int getCodiceSerie() {
        return codiceSerie;
    }

    public int getCodiceCapitolo() {
        return codiceCapitolo;
    }

    @Override
    public int compareTo(Key k) {
        return Integer.compare(codiceSerie,k.codiceSerie);
    }
}
