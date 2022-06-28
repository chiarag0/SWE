package StockManagement;

public class ActionFigure extends Elemento {
    private String nome;
    private final String size;

    public String getSize() {
        return size;
    }

    public ActionFigure(String nome, float prezzo, String serie, String size) {
        super(prezzo,serie);
        this.nome = nome;
        this.size = size;
    }
}
