package Klient;

import java.util.Random;
import java.util.UUID;

public class Klient {
    int nr_kolejki;
    int pierwsze_danie;
    boolean czy_drugie_danie;
    int drugie_danie;
    int cierpliwosc;
    int id_klienta;
    private Random random = new Random();

    int timeToNext;

    public Klient(int nr_kolejki, int id_klienta){
        this.nr_kolejki = nr_kolejki;
        timeToNext = random.nextInt(10)+1;
        pierwsze_danie = random.nextInt(4) + 1;
        czy_drugie_danie = random.nextBoolean();
        //if (czy_drugie_danie==true){
            drugie_danie = random.nextInt(10)+1;
        //}
        cierpliwosc = random.nextInt(10)+1;
        this.id_klienta=id_klienta;
    }

    public int wejdzKolejka(){

        System.out.println("Jestem " + nr_kolejki + " w kolejce. Moje ID to "+id_klienta +". Pierwsze danie bede spozywal " + pierwsze_danie);
        System.out.println("Zniecierpliwie sie po " + cierpliwosc);
        return this.id_klienta;
    }

    public int getId_klienta(){return  id_klienta;}

    public double getTimeToNext() { return  timeToNext;
    }
}
