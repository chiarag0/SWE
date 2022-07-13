package StockManagement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockManagerTest {
    StockManager sm = StockManager.getInstance();

    @BeforeAll
    public void setUp() throws Exception {
        Fumetto f1 = new Fumetto("panini", 2, "aaa", 1);
        Fumetto f2 = new Fumetto("panini", 3, "aaa", 2);
        Fumetto f3 = new Fumetto("panini", 4, "aaa", 2);
        sm.addFumetto(f1);
        sm.addFumetto(f2);
        sm.addFumetto(f3);
    }

    @Test
    void addFumetto() {
       assertEquals(sm.fumetti.size(),3);
    }
}