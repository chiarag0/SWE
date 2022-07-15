package SuppliesManagement;

import ComunicazioneCliente.AttivitàClienti;
import ComunicazioneCliente.Cliente;
import StockManagement.Fumetto;
import StockManagement.Key;
import StockManagement.StockManager;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class SuppliesManagerTest {
    static SuppliesManager fornitore;
    static StockManager gestoreMagazzino;
    static AttivitàClienti ac;
    static Fumetto f1;
    static Fumetto f2;
    static Fumetto f3;
    static Fumetto f4;
    static Fumetto f5;
    static Fumetto f6;
    static Cliente c;
    static Cliente c1;

    @BeforeAll
    static void  init() {
        ac = new AttivitàClienti();
        fornitore= SuppliesManager.getInstance(ac);
        gestoreMagazzino = StockManager.getInstance();
        c = new Cliente("Marco","Rossi","fumetteriaSWE@gmail.com",7);
        c1 = new Cliente("Mirco","Rossi","fumetteriaSWE@gmail.com",8);
    }

    @BeforeEach
    void setUp() {
        ArrayList<Cliente> clienti = new ArrayList<>();
        ArrayList<Cliente> clienti1 = new ArrayList<>();
        clienti.add(c);
        clienti1.add(c1);
        f1 = new Fumetto("panini", 2, "aaa", 5);
        f2 = new Fumetto("panini", 3, "aaa", 15);
        f3 = new Fumetto("panini", 4, "bbb", 20);
        f4 = new Fumetto("panini", 4, "bbb", 35);
        f5 = new Fumetto("panini", 5, "ccc", 10);
        f6 = new Fumetto("panini", 5, "ccc", 100);
        gestoreMagazzino.addFumetto(f1);
        gestoreMagazzino.addFumetto(f1);
        gestoreMagazzino.addFumetto(f1);
        gestoreMagazzino.addFumetto(f1);
        gestoreMagazzino.addFumetto(f2);
        gestoreMagazzino.addFumetto(f3);
        gestoreMagazzino.addFumetto(f4);
        gestoreMagazzino.addFumetto(f5);
        gestoreMagazzino.addFumetto(f6);
        //fornitore.iscrizioni.put(gestoreMagazzino.codiceSerie.get(f1.getSerie()),clienti);
        //fornitore.iscrizioni.put(gestoreMagazzino.codiceSerie.get(f5.getSerie()),clienti1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ordinaDaFornitore(){
        Key k=new Key(50,16);
        fornitore.ordinaDaFornitore();
        Assertions.assertEquals(5,fornitore.ordine.size());
        Assertions.assertEquals(10, fornitore.ordine.get(f2));
        Assertions.assertEquals(5, fornitore.ordine.get(f3));
        Assertions.assertEquals(10, fornitore.ordine.get(f4));
        Assertions.assertEquals(3, fornitore.ordine.get(f5));
        Assertions.assertEquals(10, fornitore.ordine.get(f6));
        Assertions.assertTrue(gestoreMagazzino.elementi.containsKey(k));
    }

}

class ConcreteFactoryTest {
    static ConcreteFactory factory;
    static SuppliesManager fornitore;
    static StockManager gestoreMagazzino;
    static AttivitàClienti ac;
    static Fumetto f1;
    static Fumetto f2;
    static Fumetto f3;
    static Fumetto f4;
    static Fumetto f5;
    static Fumetto f6;

    @BeforeAll
    static void init(){
        ac = new AttivitàClienti();
        fornitore= SuppliesManager.getInstance(ac);
        gestoreMagazzino = StockManager.getInstance();
        factory = new ConcreteFactory(fornitore);
        f1 = new Fumetto("panini", 2, "aaa", 5);
        f2 = new Fumetto("panini", 3, "aaa", 15);
        f3 = new Fumetto("panini", 4, "bbb", 20);
        f4 = new Fumetto("panini", 4, "bbb", 35);
        f5 = new Fumetto("panini", 5, "ccc", 10);
        f6 = new Fumetto("panini", 5, "ccc", 100);
        gestoreMagazzino.addFumetto(f1);
        gestoreMagazzino.addFumetto(f1);
        gestoreMagazzino.addFumetto(f1);
        gestoreMagazzino.addFumetto(f1);
        gestoreMagazzino.addFumetto(f2);
        gestoreMagazzino.addFumetto(f3);
        gestoreMagazzino.addFumetto(f4);
        gestoreMagazzino.addFumetto(f5);
        gestoreMagazzino.addFumetto(f6);
    }


    @Test
    void CreaElemento(){
        Key k1 = new Key(50,15);
        Key k2 = new Key(100,20);
        Key k3 = new Key(50,5);
        Key chiaveAF1 = new Key(50,997);
        Key chiaveAF2 = new Key(100,998);
        Key chiaveAF3 = new Key(150,999);
        factory.creaElemento(false,false,true,"","Panini",4,"aaa",5,false,null,5);
        factory.creaElemento(false,false,true,"","Panini",4,"aaa",15,false,null,5);
        factory.creaElemento(false,true,true,"","Panini",4,"bbb",20,false,null,3);
        factory.creaElemento(false,false,false,"Levi","Panini",4,"aaa",0,false,"large",1);
        factory.creaElemento(false,false,false,"Levi","Panini",4,"bbb",0,false,"medium",2);
        factory.creaElemento(false,true,false,"Levi","Panini",4,"ccc",0,false,"small",3);
        Assertions.assertEquals(9,gestoreMagazzino.elementi.get(k3));
        Assertions.assertEquals(6,gestoreMagazzino.elementi.get(k1));
        Assertions.assertEquals(4,gestoreMagazzino.elementi.get(k2));
        Assertions.assertEquals(1,gestoreMagazzino.elementi.get(chiaveAF1));
        Assertions.assertEquals(2,gestoreMagazzino.elementi.get(chiaveAF2));
        Assertions.assertEquals(3,gestoreMagazzino.elementi.get(chiaveAF3));
    }

}