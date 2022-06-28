
package StockManagement;

public class Key implements Comparable<Key>{
    public Integer codiceSerie = 0;
    public Integer codiceCapitolo = 0;

    public Key(Integer codiceSerie, Integer codiceCapitolo) {
        this.codiceSerie = codiceSerie;
        this.codiceCapitolo = codiceCapitolo;
    }

    public Integer getCodiceSerie() {
        return codiceSerie;
    }

    public Integer getCodiceCapitolo() {
        return codiceCapitolo;
    }

    @Override
    public int compareTo(Key k) {
        String code1 = codiceSerie.toString()+codiceCapitolo.toString();
        String code2 = k.codiceSerie.toString()+k.codiceCapitolo.toString();
        int codice1 = Integer.parseInt(code1);
        int codice2 = Integer.parseInt(code2);
        return Integer.compare(codice1,codice2);
    }
}
