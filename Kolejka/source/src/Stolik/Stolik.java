package Stolik;

public class Stolik {
    int nr_stolika;
    boolean dostepnosc_stolika = true;

    private static Stolik instance = null;

    public boolean zajmij_stolik(int nr_stolika, int id_klienta){
        if(nr_stolika == this.nr_stolika) {
            this.dostepnosc_stolika = false;
            System.out.println("Stolik nr " + nr_stolika + " został zajęty przez klienta nr " + id_klienta);
        }
        return dostepnosc_stolika;
    }

    public boolean zwolnij_stolik(int nr_stolika, int id_klienta){
        if(nr_stolika == this.nr_stolika) {
            this.dostepnosc_stolika = true;
            System.out.println("Stolik nr " + nr_stolika + " został zwolniony przez klienta nr " + id_klienta);
        }
        return dostepnosc_stolika;
    }

    static public Stolik getInstance(){
        if(instance==null) instance = new Stolik();
        return instance;
    }

    public boolean getDostepnosc(){return dostepnosc_stolika;}
}
