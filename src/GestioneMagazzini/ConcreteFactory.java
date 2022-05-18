package GestioneMagazzini;

public class ConcreteFactory extends EFactory {
    float prezzo;

    GestioneMagazzini magazzino;

    public ConcreteFactory(GestioneMagazzini magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public  void creaElemento(Boolean tipo, String nome, String casaEditrice, float costo, String serie, int capitolo, Boolean special,String size){
        if(special)
            prezzo = (float) (costo * 1.50);
        else
            prezzo = (float) (costo * 1.40);
        if (tipo) {
           Fumetto fumetto = new Fumetto(casaEditrice, prezzo, serie, capitolo);
           magazzino.addFumetto(fumetto);
        }
        else {
            ActionFigure actionFigure = new ActionFigure(nome, prezzo, serie, size);
            magazzino.addActionFigure(actionFigure);
        }

    }


}