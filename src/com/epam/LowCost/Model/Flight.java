package com.epam.LowCost.Model;

/**
 * Created by Igor on 22.11.2015.
 */
public class Flight {
    int flight_id;
    String city_of_departure;
    String date_of_departure;
    String time;
    String arrival_city;
    String arrival_time;
    int flight_places;

    public String getDate_of_departure() {
        return date_of_departure;
    }

    public void setDate_of_departure(String date_of_departure) {
        this.date_of_departure = date_of_departure;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getFlight_places() {
        return flight_places;
    }

    public void setFlight_places(int flight_places) {
        this.flight_places = flight_places;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCity_of_departure() {
        return city_of_departure;
    }

    public void setCity_of_departure(String city_of_departure) {
        this.city_of_departure = city_of_departure;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

}
