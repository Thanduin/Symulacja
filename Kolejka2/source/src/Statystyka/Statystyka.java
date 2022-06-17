package Statystyka;

public class Statystyka {

    int ilosc_stolikow;
    int ilosc_obsluzonych;
    int laczny_czas;

    double srednia = 0;

    private static Statystyka instance = null;

    public Statystyka(){
        ilosc_stolikow = 20;
        ilosc_obsluzonych = 0;
        laczny_czas=0;
        System.out.println("Utworzono statystyke, na start jest 20 stolikow.");
    }

//        static public Statystyka getInstance(){
//        if(instance==null) instance = new Statystyka();
//        return instance;
//    }

    public int getIlosc_stolikow(){
        System.out.println("\033[41m" + "Dostepnych jest " + ilosc_stolikow + " stolikow" + "\033[0m");
        return this.ilosc_stolikow;
    }

    public int getLaczny_czas(){
        System.out.println("\033[42m" + "Lacznie czekano " + laczny_czas);
        System.out.println("Obluzono " + ilosc_obsluzonych + " klientow");
        try {
            srednia = (double) laczny_czas / (double) ilosc_obsluzonych;
        } catch(ArithmeticException e){
            System.out.println("Nie obsluzono jeszcze zadnego klienta");
        }
        System.out.println("Sredni czas czekania: " + srednia + "\033[0m");
        return this.laczny_czas;
    }

    public void setLaczny_czas(int czas){
        this.laczny_czas = czas;
    }
}
