package GestioneMagazzini;

public class ActionFigure implements Elemento {
    private String nome;
    private float prezzo;
    private String serie;

    public ActionFigure(String nome, float prezzo, String serie) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.serie = serie;
    }
}
