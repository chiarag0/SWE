package ComunicazioneCliente;

import SendEmail.SendEmail;
import StockManagement.StockManager;
import StockManagement.Key;
import SuppliesManagement.SuppliesManager;
import java.util.ArrayList;
import java.util.HashMap;

public class AttivitàClienti {
    public ArrayList<Cliente> clientIscritti = new ArrayList<>();
    private final HashMap<Integer, Integer> interesse = new HashMap<>(); // codice serie valore di interesse
    private int numClienti;
    private final StockManager magazzino=  StockManager.getInstance();
    private final SuppliesManager fornitore =  SuppliesManager.getInstance(this);
    private static AttivitàClienti instance = null;

    public AttivitàClienti() {
        numClienti = 0;
    }



    public void subscribe(Cliente c){
        clientIscritti.add(c);
        numClienti++;
        c.setCodice(numClienti);
        SendEmail.send("BENVENUTO NELLA COMUNITY!!","Ciao "+ c.getNome() + ", confermiamo la tua iscrizione che ti consentirà di diventare un cliente premium, prenotare qualsiasi prodotto e resterai sempre aggiornato dulle novità!", c.getEmail());
    }

    public void unsubscribe(Cliente c){
        for (Cliente c1: clientIscritti) {
            if(c.getEmail()==c1.getEmail())
                clientIscritti.remove(c);
        }
    }

    public void subscribeSerie(Cliente c,Integer codiceSerie){
        ArrayList<Cliente> clienti;
        for (Cliente c1: clientIscritti) {
            if (c == c1){
                clienti = fornitore.iscrizioni.get(codiceSerie);
                clienti.add(c);
                fornitore.iscrizioni.put(codiceSerie, clienti);
            }
        }
    }

    public void unsubscribeSerie(Cliente c,Integer codiceSerie){
        ArrayList<Cliente> clienti;
        for (Cliente c1: clientIscritti) {
            if (c == c1){
                clienti = fornitore.iscrizioni.get(codiceSerie);
                clienti.remove(c);
                fornitore.iscrizioni.put(codiceSerie, clienti);
            }
        }
    }

    private void notifyInterest(Integer codiceSerie, boolean isPremium){
        int tmp = 0;
        if(isPremium)
            tmp = interesse.get(codiceSerie) + 3;
        else
            tmp = interesse.get(codiceSerie) + 1;
        interesse.put(codiceSerie, tmp);
        if(interesse.get(codiceSerie) >= 15)
            fornitore.ordineSpeciale(codiceSerie);
    }

    public void bookingElement(Cliente cliente, ArrayList<Key> codici){
        magazzino.prenotaElementi(codici, cliente);
    }

    public void soldElement(Key codiceElemento, int codice, Boolean subscribed){
          magazzino.soldElement( codiceElemento);
          if(subscribed){
              for(Cliente c: clientIscritti) {
                  if(codice == c.getCodice())
                    c.setAcquisti();
              }
          }

    }

    public void notifyNewSeries(String titoloSerie){
        for(Cliente c:clientIscritti){
            SendEmail.send("NUOVA SERIE DISPONIBILE!!", titoloSerie+" presto disponibile", c.getEmail());
        }
    }

}
