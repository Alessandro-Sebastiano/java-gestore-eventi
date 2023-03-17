package org.lessons.java;

import java.time.LocalDate;

public class Evento {

    private String event;
    private LocalDate date;
    private int totalSeats;
    private int reservedSeats;

    public Evento(String event, LocalDate date, int totalSeats) throws Exception{

        if(totalSeats <= 0){
            throw new Exception("Il numero di posti totali deve eseere maggiore di zero");
        }

        if (date.isBefore(LocalDate.now())){
            throw new Exception("La data deve essere maggiore o uguale alla data corrente");
        }

        this.event = event;
        this.date = date;
        this.totalSeats = totalSeats;
        this.reservedSeats = 0;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void reserve() throws Exception{

        if(date.isBefore(LocalDate.now())){
            throw new Exception("L'evento è gia avvenuto");
        }

        if(reservedSeats == totalSeats){
            throw new Exception("Tutti i posti dell'evento sono prenotati");
        }

        reservedSeats++;

    }

    public void cancel() throws Exception{

        if(date.isBefore(LocalDate.now())){
            throw new Exception("L'evento è gia avvenuto");
        }

        if(reservedSeats == 0){
            throw new Exception("Non ci sono prenotazioni per questo evento");
        }

        reservedSeats--;

    }

    public int availableSeats(){
        return totalSeats - reservedSeats;
    }

    @Override
    public String toString() {
        return date + " - " + event;
    }
}
