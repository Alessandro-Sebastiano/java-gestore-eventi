package org.lessons.java;

import java.nio.file.ReadOnlyFileSystemException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class Main {

    public static void main(String[] arg) throws Exception, IllegalFormatException, ParseException {

        Scanner input = new Scanner(System.in);

        System.out.println("Creare un nuovo evento: ");

        System.out.println("Titolo evento: ");
        String evenTitle = input.nextLine();

        LocalDate eventFormatDate = null;
        while(eventFormatDate == null) {
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
        while(eventSeats <= 0) {
            try {
                System.out.println("Posti totali evento: ");
                eventSeats = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Inserire un nuero valido");
            }
        }

        Evento newEvent = new Evento(evenTitle, eventFormatDate, eventSeats);
        System.out.println(newEvent);

        System.out.println("Vuoi pernotare dei posti? (s / n)");

        String option = input.nextLine();

        switch (option){

            case "s":
                System.out.println("Quante prenotazioni vuoi fare?");

                int reserveNum = Integer.parseInt(input.nextLine());

                try{
                    if(reserveNum > newEvent.getTotalSeats()){
                        throw new Exception("Non ci sono abbastanza posti disponibili");
                    }
                for (int i = 1; i <= reserveNum; i++) {
                    newEvent.reserve();
                }
                }catch (Exception e){
                System.out.println(e.getMessage());
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
                System.out.println("Quante prenotazioni vuoi disdire?");

                int cancelNum = Integer.parseInt(input.nextLine());

                try {
                    if (cancelNum > newEvent.getTotalSeats()) {
                        throw new Exception("Non ci sono" + cancelNum + "Prenotazioni");
                    }
                    for (int i = 1; i <= cancelNum; i++) {
                        newEvent.cancel();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("Posti prenotati: " + newEvent.getReservedSeats() + "\n" + "Posti disponibili: " + newEvent.availableSeats());
                break;


            case "n":
                System.out.println("Nessuna prenotazione disdetta");
                break;

        }


    }

}
