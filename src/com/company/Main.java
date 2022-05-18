package com.company;

import GestioneMagazzini.Key;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        TreeMap<Key,Integer> elementi = new TreeMap<>();
        Key k = new Key(8800,002);
        Key kk = new Key (5390,201);
        Key kkk = new Key (2232, 394);
        elementi.put(k,1);
        elementi.put(kk,1);
        elementi.put(kkk,1);


        System.out.println(elementi);

    }
}



