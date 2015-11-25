package com.epam.LowCost.Controller.DAO;

import com.epam.LowCost.Model.Flight;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;




public interface FlightDao {
    void Init() throws  SQLException;
     void create(String date, String city_of_departure, String arrival_city, String flight_places)throws SQLException;
     Flight read(String date_of_departure, String city_of_departure,String city_of_arrival)throws SQLException;
     void update(int flight_places, int key)throws SQLException;
     void delete()throws SQLException;
     ArrayList<Flight> getAll()throws SQLException;
     boolean isAvailablePlace(String date_of_departure, String city_of_departure, String arrival_city) throws SQLException;
     LinkedList<String> datesOfDirection(String city_of_departure, String arrival_city) throws SQLException;
}
