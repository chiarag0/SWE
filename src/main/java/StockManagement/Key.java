
package StockManagement;

public class Key implements Comparable<Key>{
    private int codiceSerie = 0;
    private int codiceCapitolo = 0;

    public Key(int codiceSerie, int codiceCapitolo) {
        this.codiceSerie = codiceSerie;
        this.codiceCapitolo = codiceCapitolo;
    }

    public void setCodiceCapitolo(int codiceCapitolo) {
        this.codiceCapitolo = codiceCapitolo;
    }

    public Integer getCodiceSerie() {
        return codiceSerie;
    }

    public Integer getCodiceCapitolo() {
        return codiceCapitolo;
    }

    @Override
    public int compareTo(Key k) throws NullPointerException {
        try {
            String a = Integer.toString(codiceSerie);
            String b = Integer.toString(codiceCapitolo);
            String c = a + b;
            String a1 = Integer.toString(k.getCodiceSerie());
            String b1 = Integer.toString(k.getCodiceCapitolo());
            String c1 = a1 + b1;
            return String.CASE_INSENSITIVE_ORDER.compare(c, c1);
        }catch (NullPointerException e){
            System.out.println("chiave nulla");
            return 0;
        }
    }

    @Override
    public boolean equals(Object o){
        Key k = (Key) o;
        if(this.codiceSerie == k.getCodiceSerie() && this.codiceCapitolo == k.getCodiceCapitolo() )
            return true;
        else return false;

    }


}
