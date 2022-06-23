package ComunicazioneCliente;

import GestioneMagazzini.Elemento;
import GestioneMagazzini.GestioneMagazzini;
import GestioneMagazzini.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class AttivitàClienti {
    private TreeMap<Cliente, ArrayList<Integer>> clientiIscritti = new TreeMap<>();
    private HashMap<Elemento, Integer> interesse = new HashMap<>();
    private int numClienti = 0;
    private GestioneMagazzini magazzino;

    public AttivitàClienti(GestioneMagazzini magazzino) {
        this.magazzino = magazzino;
    }

    public void subscribe(Cliente c){
        clientiIscritti.put(c,new ArrayList<Integer>());
        numClienti++;
        c.setCodice(numClienti);
    }

    public void subscribeSerie(Cliente c,Integer codiceSerie){
            clientiIscritti.get(c).add(codiceSerie);
    }



}
