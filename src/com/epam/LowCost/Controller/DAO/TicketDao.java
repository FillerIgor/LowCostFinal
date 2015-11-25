package com.epam.LowCost.Controller.DAO;

import com.epam.LowCost.Model.Flight;
import com.epam.LowCost.Model.Ticket;

import java.sql.SQLException;
import java.util.List;


public interface TicketDao {
     void create(String date_of_departure, String city_of_departure, String city_of_arrival,String baggage, String priority_regist_land,long client_id,Flight flight)throws SQLException;
     Ticket read(long client_id)throws SQLException;
     void update(Ticket ticket, long key)throws SQLException;
     void delete(Ticket ticket,long key)throws SQLException;
     List<Ticket> getAll()throws SQLException;
}
