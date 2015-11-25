package com.epam.LowCost.Model;



import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by Igor on 16.11.2015.
 */
public class Ticket {

    long id;
    int flight_id;
    String time_of_departure;
    String date_of_departure;
    String city_of_departure;
    String departure_terminal;
    String time_of_arrival;
    String city_of_arrival;
    String arrival_terminal;
    String baggage;
    String priority_check_in_and_boarding;
    double price;
    public Ticket(){
        price=2000;
    }

    public void calculatePrice(int flight_places) throws ParseException{

        String[] date=getDate_of_departure().split("-");
        LocalDate now = LocalDate.now();
        LocalDate departure = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
        long period_between = ChronoUnit.DAYS.between(now,departure);
        price+=price/period_between+(price*1.5)/flight_places;
        if (Boolean.valueOf(getBaggage()))
            price+=1000;
        if(Boolean.valueOf(getPriority_check_in_and_boarding()))
            price+=1000;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getTime_of_departure() {
        return time_of_departure;
    }

    public void setTime_of_departure(String time_of_departure) {
        this.time_of_departure = time_of_departure;
    }

    public String getDate_of_departure() {
        return date_of_departure;
    }

    public void setDate_of_departure(String date_of_departure) {
        this.date_of_departure = date_of_departure;
    }

    public String getCity_of_departure() {
        return city_of_departure;
    }

    public void setCity_of_departure(String city_of_departure) {
        this.city_of_departure = city_of_departure;
    }

    public String getDeparture_terminal() {
        return departure_terminal;
    }

    public void setDeparture_terminal(String departure_terminal) {
        this.departure_terminal = departure_terminal;
    }

    public String getTime_of_arrival() {
        return time_of_arrival;
    }

    public void setTime_of_arrival(String time_of_arrival) {
        this.time_of_arrival = time_of_arrival;
    }

    public String getCity_of_arrival() {
        return city_of_arrival;
    }

    public void setCity_of_arrival(String city_of_arrival) {
        this.city_of_arrival = city_of_arrival;
    }

    public String getArrival_terminal() {
        return arrival_terminal;
    }

    public void setArrival_terminal(String arrival_terminal) {
        this.arrival_terminal = arrival_terminal;
    }

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public String getPriority_check_in_and_boarding() {
        return priority_check_in_and_boarding;
    }

    public void setPriority_check_in_and_boarding(String priority_check_in_and_boarding) {
        this.priority_check_in_and_boarding = priority_check_in_and_boarding;
    }

}
