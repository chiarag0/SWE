package ComunicazioneCliente;

import StockManagement.Elemento;
import StockManagement.Fumetto;
import StockManagement.Key;
import StockManagement.StockManager;
import SuppliesManagement.SuppliesManager;
import com.sun.mail.util.ASCIIUtility;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;




//new class that implements the ActionListener interface
public class InterfacciaOnline implements ActionListener {
    private AttivitàClienti ac;
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<JButton> buttonsChapters = new ArrayList<>();
    JFrame frm;
    JFrame frm1;
    JTextArea txt;
    ArrayList<String> series = new ArrayList<>();
    ArrayList<String> seriesPre = new ArrayList<>();
    ArrayList<String> chapters = new ArrayList<>();
    Dimension d;
    Dimension dBtn;
    StockManager magazzino;
    SuppliesManager fornitore = SuppliesManager.getInstance(ac);
    public Cliente cliente;
    private boolean booking;
    private boolean selectCap;
    int serie;
    ArrayList<Key> codici = new ArrayList<>();

    public InterfacciaOnline(AttivitàClienti ac) {
        d = new Dimension(300, 300);
        dBtn = new Dimension(150, 200);
        magazzino = StockManager.getInstance();
        this.ac = ac;
    }

    public void chooseActivity() throws IOException {
        System.out.println("Cosa vuoi fare?:");
        System.out.println("1: Iscriviti ad una serie");
        System.out.println("2: Prenota un articolo");
        System.out.println("3: Cancella iscrizione");
        System.out.println("4: LogOut");
        int action = System.in.read();
        switch (action) {
            case 49: {
                subscribeMeSerie();
                break;
            }
            case 50: {
                bookElement();
                break;
            }
            case 51: {
                cancellaIscrizione();
                break;
            }
            case 52: {
                cliente = null;
                System.out.println("LOGOUT effettuato con successo!");
                break;
            }
        }
        if (cliente != null)
            chooseActivity();

    }

    public void accedi() throws IOException {
        int a = 0;
        Accesso accessPage = new Accesso("ACCEDI");
        while (accessPage.frame.isEnabled()) {
            if (accessPage.frame.isEnabled())
                System.out.println("");
            a++;
        }
        for (Cliente c : ac.clientIscritti) {
            if (Objects.equals(c.getEmail(), accessPage.mail))
                this.cliente = c;
        }
        if (this.cliente == null)
            subscribeMe();
    }


    public void subscribeMe() {
        int a;
        Iscrizione iscrizione = new Iscrizione("ISCRIVITI ALLA NEWSLETTER!!");
        while (iscrizione.frame.isActive())
            a = 0;
        Cliente c = new Cliente(iscrizione.nome, iscrizione.cognome, iscrizione.mail, 0);
        Cliente c1 = new Cliente(iscrizione.nome, iscrizione.cognome, iscrizione.mail, 0);
        this.cliente = c;
        ac.subscribe(c1);
        c.setCodice(ac.numClienti);
    }

    protected void cancellaIscrizione() throws IOException {
        if (cliente == null)
            accedi();
        int a = 0;
        Iscrizione iscrizione = new Iscrizione("DISISCRIVIMI");
        while (iscrizione.frame.isActive())
            a = 0;
        ac.unsubscribe(cliente.getEmail());
        cliente = null;
    }

    public void bookElement() throws IOException {
        codici.clear();
        this.booking = true;
        selectSeries();
        magazzino.prenotaElementi(codici,cliente);
    }

    public void selectChapters(String titolo){
        buttonsChapters.clear();
        chapters.clear();
        JButton btn;
        btn = new JButton("Fatto!");
        btn.setBackground(Color.GREEN);
        btn.addActionListener(this);
        buttonsChapters.add(btn);
        for (Fumetto f : magazzino.fumetti) {
            if(Objects.equals(magazzino.codiceSerie.get(titolo), f.getCodice().getCodiceSerie())) {
                String title = Integer.toString(f.getCapitolo());
                JButton btn1;
                btn1 = new JButton(title);
                btn1.addActionListener(this);
                buttonsChapters.add(btn1);
            }
        }
        frm = new JFrame("SERIE");
        frm.setLocation(600, 100);
        frm.setMinimumSize(d);
        frm.setLayout(new GridLayout(magazzino.codiceSerie.size() / 2 + 1, 2));
        txt = new JTextArea(1, 2);
        txt.setLineWrap(false);
        frm.add(txt);
        for (int i = 0; i < magazzino.codiceSerie.size() + 1; i++)
            frm.add(buttonsChapters.get(i));
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
        int a;
        while (frm.isActive())
            a = 0;
    }

    public void subscribeMeSerie() throws IOException {
        this.booking = false;
        selectSeries();
        for (String s : series) {
            if (!seriesPre.contains(s))
                ac.subscribeSerie(cliente, magazzino.codiceSerie.get(s));
        }
        for (String s : seriesPre) {
            if (!series.contains(s))
                ac.unsubscribeSerie(cliente, magazzino.codiceSerie.get(s));
        }
    }


    class Iscrizione implements ActionListener {
        private static JLabel nameLabel, surnameLabel, emailLabel;
        private static JTextField name;
        private static JTextField surname;
        private static JTextField email;
        //create a button
        JButton button;
        //create a frame
        JFrame frame;
        //create a text area
        String nome;
        String cognome;
        String mail;

        Iscrizione(String text) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            nameLabel = new JLabel("Nome");
            nameLabel.setBounds(100, 3, 70, 20);
            panel.add(nameLabel);
            surnameLabel = new JLabel("Cognome");
            surnameLabel.setBounds(100, 53, 70, 20);
            panel.add(surnameLabel);
            emailLabel = new JLabel("EMail");
            emailLabel.setBounds(100, 103, 70, 20);
            panel.add(emailLabel);
            frame = new JFrame();
            frame.setTitle(text);
            frame.setLocation(new Point(500, 300));
            frame.add(panel);
            frame.setSize(new Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            name = new JTextField();
            name.setBounds(100, 25, 193, 28);
            panel.add(name);
            surname = new JTextField();
            surname.setBounds(100, 75, 193, 28);
            panel.add(surname);
            email = new JTextField();
            email.setBounds(100, 125, 193, 28);
            panel.add(email);
            button = new JButton("ENTRA!");
            button.setBounds(100, 162, 90, 25);
            button.setForeground(Color.WHITE);
            button.setBackground(Color.BLACK);
            button.addActionListener(this);
            panel.add(button);

        }

        //action to be performed on clicking the button
        @Override
        public void actionPerformed(ActionEvent ev) {
            nome = name.getText();
            cognome = surname.getText();
            mail = email.getText();
            frame.dispose();
        }
    }

    class Accesso implements ActionListener {
        private static JLabel emailLabel;
        private static JTextField email;
        //create a button
        JButton button;
        //create a frame
        JFrame frame;
        //create a text area
        String mail;

        Accesso(String text) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            emailLabel = new JLabel("EMail");
            emailLabel.setBounds(100, 3, 70, 20);
            panel.add(emailLabel);
            frame = new JFrame();
            frame.setEnabled(true);
            frame.setTitle(text);
            frame.setLocation(new Point(500, 300));
            frame.add(panel);
            frame.setSize(new Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            email = new JTextField();
            email.setBounds(100, 25, 193, 28);
            panel.add(email);
            button = new JButton("Login");
            button.setBounds(100, 162, 90, 25);
            button.setForeground(Color.WHITE);
            button.setBackground(Color.BLACK);
            button.addActionListener(this);
            panel.add(button);

        }

        //action to be performed on clicking the button
        @Override
        public void actionPerformed(ActionEvent ev) {
            mail = email.getText();
            frame.setEnabled(false);
            frame.dispose();
        }
    }


    public void selectSeries() throws IOException {
        if (cliente == null)
            accedi();
        series.clear();
        buttons.clear();
        if(!booking){
            Set keys = fornitore.iscrizioni.keySet();
            for (Iterator i = keys.iterator(); i.hasNext(); ) {
                Integer codice = (Integer) i.next();
                for (Cliente c : fornitore.iscrizioni.get(codice)) {
                    if (c.getEmail().equals(cliente.getEmail())) {
                        Set keys1 = magazzino.codiceSerie.keySet();
                        for (Iterator j = keys1.iterator(); j.hasNext(); ) {
                            String titolo = (String) j.next();
                            if (magazzino.codiceSerie.get(titolo).equals(codice))
                                series.add(titolo);
                        }
                        break;
                    }
                }
            }
        }
        seriesPre = (ArrayList<String>) series.clone();
        Set keys2 = magazzino.codiceSerie.keySet();
        JButton btn;
        btn = new JButton("Fatto!");
        btn.setBackground(Color.GREEN);
        btn.addActionListener(this);
        buttons.add(btn);
        for (Object o : keys2) {
            String titolo = (String) o;
            JButton btn1;
            btn1 = new JButton(titolo);
            btn1.addActionListener(this);
            buttons.add(btn1);
        }
        frm1 = new JFrame("SERIE");
        frm1.setLocation(600, 100);
        frm1.setMinimumSize(d);
        frm1.setLayout(new GridLayout(magazzino.codiceSerie.size() / 2 + 1, 2));
        txt = new JTextArea(1, 2);
        txt.setLineWrap(false);
        txt.setText(seriesPre.toString());
        frm1.add(txt);
        for (int i = 0; i < magazzino.codiceSerie.size() + 1; i++)
            frm1.add(buttons.get(i));
        frm1.pack();
        frm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm1.setVisible(true);
        int a;
        while (frm1.isActive())
            a = 0;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        JButton b = (JButton) ev.getSource();
        if (!selectCap) {
            if (Objects.equals(b.getText(), "Fatto!")) {
                frm.dispose();
            } else {
                if (booking) {
                    serie = magazzino.codiceSerie.get(b.getText());
                    selectCap = true;
                    selectChapters(b.getText());
                }else {
                    if (!series.contains(b.getText()))
                        series.add(b.getText());
                    else
                        series.remove(b.getText());
                }
            }
            if(!booking)
                txt.setText(series.toString());
        }else{
            if (Objects.equals(b.getText(), "Fatto!")) {
                for (String s : chapters) { //TODO sistemare la prenotazione di un oggetto fatta due volte dallo stesso cliente
                    Key chiave = new Key(serie,Integer.parseInt(s));
                    codici.add(chiave);
                }
                selectCap = false;
                frm1.dispose();
            } else {
                if (!chapters.contains(b.getText()))
                    chapters.add(b.getText());
                else
                    chapters.remove(b.getText());
            }
            txt.setText(chapters.toString());
        }
    }
}