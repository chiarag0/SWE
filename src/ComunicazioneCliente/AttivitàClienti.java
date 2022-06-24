package ComunicazioneCliente;

import GestioneMagazzini.GestioneMagazzini;
import GestioneMagazzini.Key;
import GestoreForniture.GestoreForniture;
import java.util.ArrayList;
import java.util.HashMap;

public class AttivitàClienti {
    public ArrayList<Cliente> clientIscritti = new ArrayList<>();
    private HashMap<Integer, Integer> interesse = new HashMap<>();
    private int numClienti = 0;
    private GestioneMagazzini magazzino;
    private GestoreForniture fornitore;

    public AttivitàClienti(GestioneMagazzini magazzino, GestoreForniture fornitore) {
        this.fornitore = fornitore;
        this.magazzino = magazzino;
    }

    public void subscribe(Cliente c){
        clientIscritti.add(c);
        numClienti++;
        c.setCodice(numClienti);
    }

    public void subscribeSerie(Cliente c,Integer codiceSerie){
        for (Cliente c1: clientIscritti) {
            if(c == c1)
                c1.interestSeries.add(codiceSerie);
        }
    }

    private void notifyInterest(Integer codiceSerie, boolean isPremium){
        int tmp = 0;
        if(isPremium)
            tmp = interesse.get(codiceSerie) + 3;
        else
            tmp = interesse.get(codiceSerie) + 1;
        interesse.put(codiceSerie, tmp);
        if(interesse.get(codiceSerie) >= 15){
            fornitore.ordineSpeciale(codiceSerie);
        }
    }

    private void bookingElement(Cliente cliente, ArrayList<Key> codici){
        magazzino.prenotaElementi(codici, cliente);
    }


}
