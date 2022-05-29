package Stolik;

public class Stolik {
    int nr_stolika = 1;
    boolean dostepnosc_stolika = true;

    private static Stolik instance = null;

    public boolean zajmij_stolik(int nr_stolika, int id_klienta){

            this.dostepnosc_stolika = false;
            System.out.println("Stolik nr " + nr_stolika + " został zajęty przez klienta nr " + id_klienta);

        return true;
    }

    public boolean zwolnij_stolik(int nr_stolika, int id_klienta){
            this.dostepnosc_stolika = true;
            System.out.println("Stolik nr " + nr_stolika + " został zwolniony przez klienta nr " + id_klienta);
        return false;
    }

    static public Stolik getInstance(){
        if(instance==null) instance = new Stolik();
        return instance;
    }

    public boolean getDostepnosc(){return dostepnosc_stolika;}
}
