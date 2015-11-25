package com.epam.LowCost.Controller.FlightSqlStrategy;

/**
 * Created by Igor on 24.11.2015.
 */
public class Spb_Minsk implements FlightStrategy {
    private String date;
    public Spb_Minsk(String d){
        this.date=d;
    }
    @Override
    public String getSQL() {
        return "INSERT INTO Flight (city_of_departure, date_of_departure, time," +
                " arrival_city, arrival_time, flight_places) VALUES ('Saint-Peterburg','"+date+
                "','10:00','Minsk','12:00',189);";
    }
}
