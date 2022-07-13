package ComunicazioneCliente;

import StockManagement.Elemento;
import StockManagement.Fumetto;
import StockManagement.Key;
import StockManagement.StockManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.*;




//new class that implements the ActionListener interface
public class InterfacciaOnline implements ActionListener {
    private ArrayList<Elemento> catalogoOnline;
    private AttivitàClienti ac;
    ArrayList<JButton> buttons = new ArrayList<>();
    JFrame frm;
    JTextArea txt;
    ArrayList<String> series = new ArrayList<>();
    ArrayList<Integer> elementsN = new ArrayList<>();
    ArrayList<String> elementsSerie = new ArrayList<>();
    Dimension d;
    Dimension dBtn;
    StockManager magazzino;

    public InterfacciaOnline(TreeMap<Key, Integer> catalogo) {
        d=new Dimension(300,300);
        dBtn=new Dimension(150,200);
        magazzino = StockManager.getInstance();
    }


    public void subscribeMe(){

    }

    protected void  bookElement(ArrayList<Fumetto> fumetti, TreeMap<Integer, ArrayList<Cliente>> prenotazioni, Cliente cliente){
        elementsSerie.clear();
        elementsN.clear();
        Set keys = prenotazioni.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            Key codice = (Key) i.next();
            if(prenotazioni.get(codice).contains(cliente)){
                for(Fumetto f : fumetti) {
                    if (f.getCodice() == codice) {
                        elementsSerie.add(f.getSerie());
                        elementsN.add(f.getCapitolo());
                    }
                }
            }
        }
        JButton btn;
        btn = new JButton("Fatto!");
        btn.setBackground(Color.GREEN);
        btn.addActionListener(this);
        buttons.add(btn);
        btn.setMinimumSize(dBtn);
        for (int i = 0; i<fumetti.size(); i++) {
            JButton btn1;
            btn1 = new JButton(elementsSerie.get(i)+elementsN.get(i));
            btn1.addActionListener(this);
            buttons.add(btn1);
            btn.setMinimumSize(dBtn);
        }
        frm = new JFrame("CATALOGO");
        frm.setLocation(600,100);
        frm.setMinimumSize(d);
        frm.setLayout(new GridLayout(fumetti.size()/2+1,2));
        txt = new JTextArea(1, 2);
        txt.setLineWrap(false);
        frm.add(txt);
        for (int i=0; i<fumetti.size(); i++)
            frm.add(buttons.get(i));
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
        ArrayList<Key> codici=new ArrayList<>();
        for(int i = 0; i< elementsSerie.size(); i++){
            int codSerie = magazzino.codiceSerie.get(elementsSerie.get(i));
            int codiceCap = elementsN.get(i);
            Key codice=new Key(codSerie,codiceCap);
            codici.add(codice);
        }
        ac.bookingElement(cliente,codici);
    }

    protected void  subscribeMeSerie(TreeMap<Integer, ArrayList<Cliente>> iscrizioni, Cliente cliente){
        series.clear();
        ArrayList<String> seriesPre = new ArrayList<>();
        Set keys = iscrizioni.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            Integer codice = (Integer) i.next();
            if(iscrizioni.get(codice).contains(cliente)){
                Set keys1 = magazzino.codiceSerie.keySet();
                for (Iterator j = keys1.iterator(); j.hasNext(); ) {
                    String titolo = (String) j.next();
                    if(magazzino.codiceSerie.get(titolo)==codice)
                        series.add(titolo);
                }
            }
        }
        seriesPre=series;
        Set keys2 = magazzino.codiceSerie.keySet();
        JButton btn;
        btn = new JButton("Fatto!");
        btn.setBackground(Color.GREEN);
        btn.addActionListener(this);
        buttons.add(btn);
        btn.setMinimumSize(dBtn);
        for (Iterator i = keys2.iterator(); i.hasNext(); ) {
            String titolo = (String) i.next();
            JButton btn1;
            btn1 = new JButton(titolo);
            btn1.addActionListener(this);
            buttons.add(btn1);
            btn.setMinimumSize(dBtn);
        }
        frm = new JFrame("SERIE");
        frm.setLocation(600,100);
        frm.setMinimumSize(d);
        frm.setLayout(new GridLayout(magazzino.codiceSerie.size()/2+1,2));
        txt = new JTextArea(1, 2);
        txt.setLineWrap(false);
        frm.add(txt);
        for (int i=0; i<magazzino.codiceSerie.size(); i++)
            frm.add(buttons.get(i));
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
        for(String s: series){
            if(!seriesPre.contains(s))
                ac.subscribeSerie(cliente, magazzino.codiceSerie.get(s));
        }
        for(String s: seriesPre){
            if(!series.contains(s))
                ac.unsubscribeSerie(cliente, magazzino.codiceSerie.get(s));
        }
    }

    protected void cancellaIscrizione(){

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        JButton b = (JButton)ev.getSource();
        if(b.getText()=="Fatto!")
            frm.dispose();
        if(series.contains(b.getText()))
            series.remove(b.getText());
        else
            series.add(b.getText());
        txt.setText(series.toString());
    }
}