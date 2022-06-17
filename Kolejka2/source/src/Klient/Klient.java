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

    int nr_zajetego;
    int calosc;
    private Random random = new Random();

    double czasWejscia;


    public Klient(int nr_kolejki, int id_klienta, double czasWejscia){
        this.nr_kolejki = nr_kolejki;
        this.czasWejscia = czasWejscia;
        pierwsze_danie = random.nextInt(10) + 2;
        calosc = pierwsze_danie;
        if(pierwsze_danie%2 != 0){
            pierwsze_danie++;
        }
        czy_drugie_danie = random.nextBoolean();
        if (czy_drugie_danie==true){
            drugie_danie = random.nextInt(10)+1;
            calosc += drugie_danie;
        }
        cierpliwosc = random.nextInt(10)+1;
        this.id_klienta=id_klienta;
        System.out.println("\033[47m" + "Moje ID to "+id_klienta +". Jestem " + nr_kolejki + " w kolejce. Pierwsze danie bede spozywal " + pierwsze_danie);
        if (czy_drugie_danie==true){
            System.out.println("Drugie danie bede spozywal " + drugie_danie + ". Razem to da " + calosc);
        }
        System.out.println("Zniecierpliwie sie po " + cierpliwosc + "\033[0m");
    }

    public int wejdzKolejka(){
        return this.id_klienta;
    }

    public int wejdzSala(){
        System.out.println("\033[41m" + "Klient o ID " + id_klienta + " zajmuje stolik nr " + nr_zajetego + "\033[0m");
        return this.id_klienta;
    }

    public int wyjdzSala(){
        System.out.println("\033[42m" + "Klient o ID " + id_klienta + " zwalnia stolik nr" + nr_zajetego +  "\033[0m");
        return this.id_klienta;
    }

    public int wyjdzKolejka(){
        System.out.println("\033[44m" + "Klient o ID " + id_klienta + " zniecierpliwil sie." + "\033[0m");
        return id_klienta;
    }

    public int getNr_zajetego(){
        return this.nr_zajetego;
    }

    public int getPierwsze_danie(){return pierwsze_danie;}

    public int getId_klienta(){return  id_klienta;}

    public double getCzasWejscia() { return  czasWejscia;    }

}
