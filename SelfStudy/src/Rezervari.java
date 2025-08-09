/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Renata
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Rezervari {

    private int id;
    private String nume;
    private String data;
    private int numarDeOaspeti;

    public Rezervari(int id, String nume, String data, int numarDeOaspeti) {
        this.id = id;
        this.nume = nume;
        this.data = data;
        this.numarDeOaspeti = numarDeOaspeti;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getData() {
        return data;
    }

    public int getNumarDeOaspeti() {
        return numarDeOaspeti;
    }
}

class RezervariSistem {

    private List<Rezervari> rezervari = new ArrayList<>();
    private int nextId = 1;
    private int id;
    private String nume;
    private String data;
    private int numarDeOaspeti;

    public Rezervari creeazaRezervari(String nume, String data, int numarDeOaspeti) {
        Rezervari rezervari = new Rezervari(nextId++, nume, data, numarDeOaspeti);
        rezervari.add(rezervari);
        return rezervari;
    }

    public List<Rezervari> getRezervari() {
        return rezervari;
    }

    public Rezervari getRezervatiDupaId(int id) {
        for (Rezervari rezervari : rezervari) {
            if (rezervari.getId() == id) {
                return rezervari;
            }
        }
        return null;
    }

    public boolean cancelRezervatii(int id) {
        Rezervari rezervari = getRezervatiDupaId(id);
        if (rezervari != null) {
            rezervari.remove(rezervari);
            return true;
        }
        return false;
    }
}

class RezervariSistemUI {

    private RezervariSistem rezervatiSistem = new RezervariSistem();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Creeaza o rezervare");
            System.out.println("2. Vizualizeaze reservarile");
            System.out.println("3. Anuleaza rezervarea");
            System.out.println("4. Exit");

            int alegere = scanner.nextInt();
            scanner.nextLine();

            switch (alegere) {
                case 1:
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();
                    System.out.print("Data: ");
                    String data = scanner.nextLine();
                    System.out.print("Numar de oaspeti: ");
                    int numarDeOaspeti = scanner.nextInt();
                    scanner.nextLine();

                    Rezervari rezervari = rezervatiSistem.creeazaRezervari(nume, data, numarDeOaspeti);
                    System.out.println("Rezervatia facuta cu ID " + rezervari.getId());
                    break;
                case 2:
                    System.out.println("Rezervari:");
                    for (Rezervari r : rezervatiSistem.getRezervari()) {
                        System.out.println(r.getId() + " - " + r.getNume() + " - " + r.getData() + " - " + r.getNumarDeOaspeti());
                    }
                    break;
                case 3:
                    System.out.print("Reservation ID to cancel: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (rezervatiSistem.cancelRezervatii(id)) {
                        System.out.println("Rezervare anulata");
                    } else {
                        System.out.println("Rezervatia nu a fost gasita");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Alegere invalida");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        RezervariSistemUI obj = new RezervariSistemUI();
        obj.start();
    }
}
