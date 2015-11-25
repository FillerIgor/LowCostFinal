package com.epam.LowCost.Controller.FlightSqlStrategy;

/**
 * Created by Igor on 24.11.2015.
 */
public class Minsk_Spb implements FlightStrategy {
    private String date;
    public Minsk_Spb(String d){
        this.date=d;
    }
    @Override
    public String getSQL() {
        return "INSERT INTO Flight (city_of_departure, date_of_departure, time," +
                " arrival_city, arrival_time, flight_places) VALUES ('Minsk','"+date+
                "','19:00','Saint-Peterburg','21:00',189);";
    }
}
