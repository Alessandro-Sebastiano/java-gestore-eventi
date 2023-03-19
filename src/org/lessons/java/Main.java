package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] arg) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.println("Creare un nuovo evento: ");

        System.out.println("Titolo evento: ");
        String evenTitle = input.nextLine();

        LocalDate eventFormatDate = null;
        while (eventFormatDate == null) {
            try {
                System.out.println("Data evento: (dd/MM/yyyy) ");
                String eventDate = input.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                eventFormatDate = LocalDate.parse(eventDate, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Inserire una data valida");
            }
        }

        int eventSeats = 0;
        while (eventSeats <= 0) {
            try {
                System.out.println("Posti totali evento: ");
                eventSeats = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Inserire un nuero valido");
            }
        }

        Evento newEvent = null;
        try {
            newEvent = new Evento(evenTitle, eventFormatDate, eventSeats);
            System.out.println(newEvent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        String option = null;
        try {
            System.out.println("Vuoi pernotare dei posti? (s / n)");
            option = input.nextLine();

            if (option != "s" || option != "n") {
                throw new Exception("Inserire un opzione valida");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        switch (option) {

            case "s":


                boolean stop = false;
                while (!stop) {
                    try {

                        System.out.println("Quante prenotazioni vuoi fare?");

                        int reserveNum = Integer.parseInt(input.nextLine());

                        if (reserveNum > newEvent.getTotalSeats()) {
                            throw new Exception("Non ci sono abbastanza posti disponibili");
                        }
                        stop = true;
                        for (int i = 1; i <= reserveNum; i++) {
                            newEvent.reserve();
                        }


                    } catch (NumberFormatException e) {
                        System.out.println("Inserire un numero valido");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }


                System.out.println("Posti prenotati: " + newEvent.getReservedSeats() + "\n" + "Posti disponibili: " + newEvent.availableSeats());
                break;


            case "n":
                System.out.println("Nessuna prenotazione effettuata");
                break;

        }


        System.out.println("Vuoi disdire delle prenotazioni? (s / n)");

        String secOption = input.nextLine();

        switch (secOption) {

            case "s":

                boolean stop = false;
                while (!stop) {
                    try {

                        System.out.println("Quante prenotazioni vuoi disdire?");

                        int cancelNum = Integer.parseInt(input.nextLine());

                        if (cancelNum > newEvent.getTotalSeats()) {
                            throw new Exception("Non ci sono" + cancelNum + "Prenotazioni");
                        }
                        stop = true;
                        for (int i = 1; i <= cancelNum; i++) {
                            newEvent.cancel();
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Inserire un numero valido");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }


                System.out.println("Posti prenotati: " + newEvent.getReservedSeats() + "\n" + "Posti disponibili: " + newEvent.availableSeats());
                break;


            case "n":
                System.out.println("Nessuna prenotazione disdetta");
                break;

        }


    }

}
