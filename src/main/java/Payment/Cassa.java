package Payment;
import ComunicazioneCliente.AttivitàClienti;
import ComunicazioneCliente.Cliente;
import ComunicazioneCliente.InterfacciaOnline;

import java.io.IOException;
import java.util.Scanner;

enum mod{
    POS, CASH, APP18
}
public class Cassa {
    AttivitàClienti ac;
    InterfacciaOnline io;

    public Cassa(AttivitàClienti ac, InterfacciaOnline io) {
        this.ac = ac;
        this.io = io;
    }

    private void effettuaPagamento(mod mod, int somma, int codiceCliente) throws IOException {
        switch(mod){
            case POS:
                pagamentoPOS(somma);
            case CASH:
                pagamentoCASH(somma);
            case APP18:
                pagamento18APP(somma);
        }
        if(codiceCliente > 0){
            for (Cliente c: ac.clientIscritti) {
                if(c.getCodice() == codiceCliente)
                    c.setAcquisti();
                if(c.getAcquisti() == 100)
                    c.setPremium(Boolean.TRUE);
            }
        }else
            io.subscribeMe();
    }

    private void pagamentoPOS(int somma){
        // implementazione pagamento
    }

    private void pagamentoCASH(int somma){
        // implementazione pagamento
    }

    private void pagamento18APP(int somma){
        // implementazione pagamento
    }
}
