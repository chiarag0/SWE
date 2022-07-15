package StockManagement;

import ComunicazioneCliente.Cliente;
import org.junit.jupiter.api.*;
import java.util.ArrayList;



class StockManagerTest {
    static StockManager sm;
    static Cliente c;
    static Cliente c1;
    static ArrayList<Cliente> clienti;
    static ArrayList<Key> codici;
    static Fumetto f1;
    static Fumetto f1b;
    static Fumetto f2;
    static Fumetto f3;
    static Fumetto f4;

    @BeforeAll
    public static void init() {
        sm = StockManager.getInstance();
        f1 = new Fumetto("panini", 2, "aaa", 1);
        f1b = new Fumetto("mondadori", 5, "aaa", 1);
        f2 = new Fumetto("panini", 3, "bbb", 1);
        f3 = new Fumetto("panini", 4, "aaa", 2);
        f4 = new Fumetto("panini", 4, "aaa", 3);
    }

    @BeforeEach
    public void setUp() {
        c = new Cliente("Marco","Rossi","fumetteriaSWE@gmail.com",7);
        c1 = new Cliente("Marco","Rossi","fumetteriaSWE@gmail.com",8);
    }

    @AfterEach
    public void tearDown(){
        sm.elementiPrenotati.clear();
        sm.elementi.clear();
        sm.prenotazioni.clear();
        sm.fumetti.clear();
    }


    @Test
    void prenotaElementi() {
        codici = new ArrayList<>();
        sm.addFumetto(f1);
        f1.getCodice();
        sm.addFumetto(f2);
        sm.addFumetto(f2);
        sm.addFumetto(f3);
        sm.addFumetto(f4);
        codici.add(f1.getCodice());
        codici.add(f2.getCodice());
        sm.soldElement(f1.getCodice());
        sm.soldElement(f2.getCodice());
        sm.soldElement(f2.getCodice());
        sm.prenotaElementi(codici,c);
    /*    Assertions.assertEquals(0, sm.elementi.get(f1.getCodice()));
        Assertions.assertEquals(1, sm.elementi.get(f2.getCodice()));
        Assertions.assertEquals(1, sm.prenotazioni.get(f2.getCodice()).size());
        Assertions.assertEquals(2, sm.elementiPrenotati.size());
        Assertions.assertEquals(0, sm.elementi.get(f1.getCodice()));
        Assertions.assertEquals(2, sm.prenotazioni.get(f1.getCodice()).size());
        Assertions.assertEquals(1, sm.elementiPrenotati.get(f1.getCodice()));
        Assertions.assertEquals(3, sm.prenotazioni.get(f2.getCodice()).size());
        Assertions.assertEquals(2, sm.elementiPrenotati.get(f2.getCodice()));*/
        Assertions.assertFalse(c.prenotazioni.get(f1.getCodice()));
        if(c.prenotazioni.get(f1.getCodice()) == null)
            System.out.close();
        sm.addFumetto(f1);
        sm.addFumetto(f2);
    }

    @Test
    void addFumetto() {
        clienti = new ArrayList<>();
        clienti.add(c);
        sm.addFumetto(f1);
        Assertions.assertEquals(50, f1.getCodice().getCodiceSerie());
        Assertions.assertEquals(1, f1.getCodice().getCodiceCapitolo());
        sm.addFumetto(f2);
        Assertions.assertEquals(100, f2.getCodice().getCodiceSerie());
        Assertions.assertEquals(1, f2.getCodice().getCodiceCapitolo());
        Assertions.assertEquals(2, sm.fumetti.size());
        Assertions.assertEquals(2, sm.elementi.size());
        sm.addFumetto(f1b);
        Assertions.assertEquals(2, sm.elementi.size());
        sm.addFumetto(f3);
        Assertions.assertEquals(50, f3.getCodice().getCodiceSerie());
        Assertions.assertEquals(2, f3.getCodice().getCodiceCapitolo());
        Assertions.assertEquals(3, sm.elementi.size());
        sm.addFumetto(f4);
        Assertions.assertEquals(1, sm.elementi.get(f4.getCodice()));
        sm.prenotazioni.put(f4.getCodice(),clienti);
        clienti.add(c1);
        Assertions.assertEquals(4, sm.fumetti.size());
        Assertions.assertEquals(1, sm.prenotazioni.size());
        Assertions.assertEquals(0, sm.elementiPrenotati.size());
        sm.addFumetto(f4);
        sm.prenotazioni.put(f4.getCodice(),clienti);
        Assertions.assertEquals(1, sm.elementi.get(f4.getCodice()));
        Assertions.assertEquals(4, sm.fumetti.size());
        Assertions.assertEquals(1, sm.prenotazioni.size());
        Assertions.assertEquals(1, sm.elementiPrenotati.size());
        Assertions.assertEquals(1, sm.elementiPrenotati.get(f4.getCodice()));
        sm.addFumetto(f4);
        sm.addFumetto(f4);
        Assertions.assertEquals(2, sm.elementi.get(f4.getCodice()));
        Assertions.assertEquals(2, sm.elementiPrenotati.get(f4.getCodice()));
        Assertions.assertEquals(2, sm.prenotazioni.get(f4.getCodice()).size());
    }
}