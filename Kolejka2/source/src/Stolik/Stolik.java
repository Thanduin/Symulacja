package Stolik;

public class Stolik {
    int nr_stolika;
    boolean dostepnosc_stolika = true;

    private static Stolik instance = null;

    public Stolik(int nr_stolika){
        this.nr_stolika = nr_stolika;
        System.out.println("Utworzono stolik nr " + nr_stolika);
    }

    public boolean zajmij_stolik(int nr_stolika, int id_klienta){

            this.dostepnosc_stolika = false;
            System.out.println("\033[41m" + "Klient o ID " + "Stolik nr " + nr_stolika + " został zajęty przez klienta nr " + id_klienta + "\033[0m");

        return true;
    }

    public boolean zwolnij_stolik(int nr_stolika, int id_klienta){
            this.dostepnosc_stolika = true;
            System.out.println("\033[42m" + "Stolik nr " + nr_stolika + " został zwolniony przez klienta nr " + id_klienta + "\033[0m");
        return false;
    }

//    static public Stolik getInstance(){
//        if(instance==null) instance = new Stolik();
//        return instance;
//    }

    public boolean getDostepnosc(){return dostepnosc_stolika;}
}
