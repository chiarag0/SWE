package com.company;

import GestioneMagazzini.Key;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
	// write your code here

       TreeMap<Key,Integer> elementi = new TreeMap<>();
        Key k = new Key(8800,002);
        Key kk = new Key (5390,201);
        Key kkk = new Key (2232, 394);
        elementi.put(k,2);
        elementi.put(kk,3);
        elementi.put(kkk,1);
        String a = elementi.toString();
        System.out.println(a);
       System.out.println(elementi.get(kk));

        /*
        for(Map.Entry<Key,Integer> entry : elementi.entrySet()) {
            Key key = entry.getKey();
            Integer value = entry.getValue();


        }

        // Use iterator to display the keys and associated values
        Set keys = elementi.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            Key key = (Key) i.next();
            Integer value = (Integer) elementi.get(key);
            System.out.println(key + " = " + value);*/
        }

    }




