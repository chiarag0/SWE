package com.company;



import ComunicazioneCliente.AttivitàClienti;
import ComunicazioneCliente.InterfacciaOnline;
import StockManagement.Fumetto;
import StockManagement.Key;
import StockManagement.StockManager;
import SuppliesManagement.ConcreteFactory;
import SuppliesManagement.SuppliesManager;
import com.sun.mail.util.ASCIIUtility;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        StockManager magazzino = StockManager.getInstance();
        AttivitàClienti ac = new AttivitàClienti();
        SuppliesManager fornitore = SuppliesManager.getInstance(ac);
        ConcreteFactory factory = new ConcreteFactory(fornitore);
        InterfacciaOnline onlineInterface = new InterfacciaOnline(ac);
        factory.creaElemento(false,false,true,"","panini",4,"Naruto",1,false,"",2);
        factory.creaElemento(false,false,true,"","panini",4,"Naruto",2,false,"",5);
        factory.creaElemento(false,false,true,"","panini",4,"Naruto",19,false,"",2);
        factory.creaElemento(false,false,true,"","panini",4,"Naruto",100,false,"",1);
        factory.creaElemento(false,false,true,"","panini",4,"One Piece",1,false,"",2);
        factory.creaElemento(false,false,true,"","panini",4,"One Piece",5,false,"",5);
        factory.creaElemento(false,false,true,"","panini",4,"One Piece",7,false,"",2);
        factory.creaElemento(false,true,true,"","panini",4,"One Piece",80,false,"",10);
        factory.creaElemento(false,false,false,"Naruto","panini",40,"Naruto",999,false,"small",1);
        factory.creaElemento(false,false,false,"Sasuke","panini",90,"Naruto",997,false,"large",1);
        factory.creaElemento(false,true,false,"Zoro","panini",60,"One Piece",998,false,"medium",1);
        onlineInterface.chooseActivity();
        factory.creaElemento(false,false,true,"","panini",4,"Naruto",1,false,"",2);
        factory.creaElemento(false,false,true,"","panini",4,"Naruto",19,false,"",2);
        factory.creaElemento(false,false,true,"","panini",4,"Naruto",100,false,"",1);
        factory.creaElemento(false,false,true,"","panini",4,"One Piece",1,false,"",2);
        factory.creaElemento(false,false,true,"","panini",4,"One Piece",5,false,"",5);
        factory.creaElemento(false,true,true,"","panini",4,"One Piece",7,false,"",2);
        fornitore.riceviNotifica("Attack on Titan");
        factory.creaElemento(false,false,true,"","panini",4,"Attack on Titan",1,false,"",2);
        factory.creaElemento(false,true,true,"","panini",4,"Attack on Titan",2,false,"",2);
        fornitore.ordinaDaFornitore();

    }
}




