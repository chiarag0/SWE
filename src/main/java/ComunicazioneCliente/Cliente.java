package ComunicazioneCliente;

import SendEmail.SendEmail;
import StockManagement.Fumetto;
import StockManagement.Key;

import java.util.*;
import java.util.ArrayList;

public class Cliente implements Comparable<Cliente>,Observer{
    private String nome;
    private String cognome;
    private String email;
    private int codice;
    private Boolean premium = Boolean.FALSE;
    private int acquisti = 0;
    public HashMap<Key, Boolean> prenotazioni = new HashMap<>();

    public Cliente(String nome, String cognome, String email, int codice) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.codice = codice;
    }

    public String getEmail() {
        return email;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public int getAcquisti() {
        return acquisti;
    }

    public void setAcquisti() {
        this.acquisti ++;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int compareTo(Cliente c) {
        return Integer.compare(codice,c.codice);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.getClass() == Fumetto.class) {
            Fumetto fumetto = (Fumetto) arg;
            Boolean flag = Boolean.FALSE;
            Set keys = prenotazioni.keySet();
            for (Iterator i = keys.iterator(); i.hasNext(); ) {
                Key chiave = (Key) i.next();
                if (chiave.equals(fumetto.getCodice())) {
                    prenotazioni.put(chiave, Boolean.TRUE);
                }
            }
            for (Iterator i = keys.iterator(); i.hasNext(); ) {
                Key chiave = (Key) i.next();
                if (prenotazioni.get(chiave) == Boolean.FALSE) {
                    flag = Boolean.TRUE;
                }
            }
            if (!flag) {
                SendEmail.send("PRODOTTI DISPONIBILI!!", "Ciao " + this.nome + ", gli oggetti da lei prenotati sono disponibili per il ritiro in negozio", email);
            }
        }else{
            String titolo = (String) arg;
            SendEmail.send("NUOVO CAPITOLO!!", "Ciao " + this.nome + ", è uscito un nuovo capitolo della serie " + titolo, email);
        }
    }

}
