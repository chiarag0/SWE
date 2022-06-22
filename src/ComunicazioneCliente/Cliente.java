package ComunicazioneCliente;

import GestioneMagazzini.Elemento;

public class Cliente implements Comparable<Cliente> {
    private String nome;
    private String cognome;
    private String email;
    private int codice;
    private Boolean premium ;

    public Cliente(String nome, String cognome, String email, int codice, Boolean premium) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.codice = codice;
        this.premium = premium;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    @Override
    public int compareTo(Cliente c) {
        return Integer.compare(codice,c.codice);
    }
}
