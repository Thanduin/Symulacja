package Klient;

import java.util.Random;
import java.util.UUID;

public class Klient {
    int nr_kolejki;
    int pierwsze_danie;
    boolean czy_drugie_danie;
    int drugie_danie;
    int cierpliwosc;
    UUID id_klienta;
    private Random random = new Random();

    int timeToNext;

    public Klient(int nr_kolejki){
        this.nr_kolejki = nr_kolejki;
        timeToNext = random.nextInt(10)+1;
        id_klienta=UUID.randomUUID();
        cierpliwosc = random.nextInt(10)+1;
        czy_drugie_danie = random.nextBoolean();
        if (czy_drugie_danie==true){
            drugie_danie = random.nextInt(10)+1;
        }
    }

    public int wejdzKolejka(){
        pierwsze_danie = random.nextInt(4) + 1;
        System.out.println("Jestem " + nr_kolejki + " w kolejce. Moje ID to "+id_klienta +". Pierwsze danie bede spozywal " + pierwsze_danie);
        System.out.println("Zniecierpliwie sie po " + cierpliwosc);
        return this.nr_kolejki;
    }

    public UUID getId_klienta(){return  id_klienta;}

    public double getTimeToNext() { return  timeToNext;
    }
}
