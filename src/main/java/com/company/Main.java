package com.company;

import StockManagement.Fumetto;
import StockManagement.Key;
import StockManagement.StockManager;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        // write your code here

        TreeMap<Key, Integer> elementi = new TreeMap<>();
        Key k14 = new Key(8800, 002);
        Key kk = new Key(5390, 201);
        Key kkk = new Key(2232, 394);
        Key k13 = new Key(8800, 003);
        Key k2 = new Key(2235, 327);
        Key k3 = new Key(2232, 122);
        Key k4 = new Key(2232, 420);
        Key k5 = new Key(2232, 162);
        Key k6 = new Key(2232, 245);
        Key k7 = new Key(2232, 432);
        Key k8 = new Key(2232, 343);
        Key k9 = new Key(2232, 430);
        Key k10 = new Key(2232, 234);
        Key k11 = new Key(2232, 342);
        Key k12 = new Key(2232, 123);

        elementi.put(k14, 2);
        elementi.put(kk, 2);
        elementi.put(kkk, 2);
        elementi.put(k13, 1);
        elementi.put(k2, 2);
        elementi.put(k3, 1);
        elementi.put(k4, 2);
        elementi.put(k5, 1);
        elementi.put(k6, 2);
        elementi.put(k7, 1);
        elementi.put(k8, 1);
        elementi.put(k9, 1);
        elementi.put(k10, 1);
        elementi.put(k11, 1);
        elementi.put(k12, 1);


       /* String a = elementi.toString();
        System.out.println(a);
       System.out.println(elementi.get(kk));
*/
        /*

        TreeMap<Key, Integer> ordine = new TreeMap<>();

        int numCapitolo = 0;
        int quantità = 0;
        Fumetto fumetto = null;
        for(int numSerie = 1; numSerie < 9999; numSerie++) {
            for (Key k : elementi.keySet()) {
                if (numSerie == k.getCodiceSerie()) {
                    numCapitolo = 0;
                    for (Key k1 : elementi.keySet()) {
                        if (numSerie == k1.getCodiceSerie()) {
                            if (numCapitolo < k1.getCodiceCapitolo() && k1.getCodiceCapitolo() < 997) {//trova l'ultimo capitolo che non è un'action figure
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


                    if (elementi.get(k) < 3 && k.getCodiceCapitolo() < 997) {

                        ordine.put(k, quantità);
                    }
                }
            }
        }



        Set chiavi = ordine.keySet();
        for (Iterator i = chiavi.iterator(); i.hasNext(); ) {
            Key key = (Key) i.next();
            Integer value = (Integer) ordine.get(key);
            System.out.println(key + " = " + value);
        }

        HashMap<Integer,ArrayList<Integer>> a = new HashMap<>();
        ArrayList<Integer> aa = new ArrayList<>();
        aa.add(2);
        a.put(0,aa);
        a.get(0).add(3);
        System.out.println(a.get(0));
*/
/*
        GregorianCalendar c= new GregorianCalendar();
        System.out.println(c.get(Calendar.MONTH) +1);
*/
        /*

        for(Map.Entry<Key,Integer> entry : elementi.entrySet()) {
            Key key = entry.getKey();
            Integer value = entry.getValue();

        }
        */
/*
        // Use iterator to display the keys and associated values
        Set keys = elementi.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            Key key = (Key) i.next();
            Integer value = (Integer) elementi.get(key);
            System.out.println(key + " = " + value);
        }
        System.out.println("MAPPA"+elementi.size());
    }
    */
    }
}




