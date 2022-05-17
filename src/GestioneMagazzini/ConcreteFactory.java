package GestioneMagazzini;

public class ConcreteFactory extends EFactory {
    float prezzo = 0;
    Elemento elemento = null;

    @Override
    public  void creaElemento(Boolean tipo, String nome, String casaEditrice, float costo, String serie, Boolean special){
        if(special)
            prezzo = (float) (costo * 1.45);
        else
            prezzo = (float) (costo * 1.40);
        if (tipo)
            elemento = new Fumetto(nome,casaEditrice,prezzo,serie);
        else
            elemento = new ActionFigure(nome,prezzo,serie);
        GestioneMagazzini.elementi.add(elemento);

    }


}