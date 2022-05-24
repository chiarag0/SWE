package GestoreFornitori;

import GestioneMagazzini.GestioneMagazzini;
import GestioneMagazzini.Key;
import GestioneMagazzini.Fumetto;

import java.util.*;

public class GestoreFornitori {
    private ArrayList<Fornitore> fornitori;
    private GestioneMagazzini gestoreMagazzini;
    private GregorianCalendar calendar = new GregorianCalendar();
    private int giorno;
    private int mese;
    private TreeMap<Fumetto, Integer> ordine = new TreeMap<>();

    public GestoreFornitori(ArrayList<Fornitore> fornitori, GestioneMagazzini gestoreMagazzini) {
        this.fornitori = fornitori;
        this.gestoreMagazzini = gestoreMagazzini;
        this.giorno = calendar.get(Calendar.DATE);
        this.mese = calendar.get(Calendar.MONTH) + 1;
    }

    private void controllaGiorno() {
        if (mese == (calendar.get(Calendar.MONTH) + 1)) {
            if (giorno - (calendar.get(Calendar.DATE)) >= 7)
                ordinaDaFornitore();
        } else {
            switch (mese) {
                case 1, 3, 5, 7, 8, 10, 12:
                    if (giorno - (calendar.get(Calendar.DATE) + 31) >= 7)
                        ordinaDaFornitore();
                    break;
                case 4, 6, 9, 11:
                    if (giorno - (calendar.get(Calendar.DATE) + 30) >= 7)
                        ordinaDaFornitore();
                    break;
                case 2:
                    if (giorno - (calendar.get(Calendar.DATE) + 28) >= 7)
                        ordinaDaFornitore();
                    break;
            }
        }
    }


    private void ordinaDaFornitore() {
        int numSerie = 1;
        int numCapitolo = 0;
        int quantità = 0;
        Fumetto fumetto = null;
        for (Key k : gestoreMagazzini.elementi.keySet()) {
            if (numSerie != k.getCodiceSerie()) {
                numCapitolo = 0;
                for (Key k1 : gestoreMagazzini.elementi.keySet()) {
                    if (k1.getCodiceSerie() == numSerie) {
                        if (numCapitolo < k1.getCodiceCapitolo() && k1.getCodiceCapitolo() < 997) //trova l'ultimo capitolo che non è un'action figure
                            numCapitolo = k1.getCodiceCapitolo();
                    }
                }
            }
            int dist = numCapitolo - k.getCodiceCapitolo();
            if (dist <= 10)
                quantità = 10;
            if (dist > 10 && dist < 50)
                quantità = 5;
            if (dist >= 50)
                quantità = 3;

            for (Fumetto f : gestoreMagazzini.fumetti) {
                if (k == f.getCodice())
                    fumetto = f;
            }
            if (gestoreMagazzini.elementi.get(k) < 3 && k.getCodiceCapitolo() < 997) {
                ordine.put(fumetto, quantità);
            }

        }
    }


    private void riceviNotifica(){

    }
}
