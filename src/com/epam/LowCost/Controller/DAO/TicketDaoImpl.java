package com.epam.LowCost.Controller.DAO;

import com.epam.LowCost.Model.Flight;
import com.epam.LowCost.Model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TicketDaoImpl implements TicketDao {
    private Connection connection;

    TicketDaoImpl(Connection con){
        this.connection = con;
    }
    @Override
    public void create(String date_of_departure, String city_of_departure, String city_of_arrival,String baggage, String priority_regist_land, long client_id, Flight flight) throws SQLException{

        String sql = "INSERT INTO Ticket (time_of_departure, date_of_departure ,city_of_departure,time_of_arrival,city_of_arrival,baggage,priority_regist_land, client_id,flight_id) VALUES(?,?,?,?,?,?,?,?,?);";

        if( baggage==null)
            baggage="false";
        if( priority_regist_land==null)
            priority_regist_land="false";

        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1,flight.getTime());
            stm.setString(2, date_of_departure);
            stm.setString(3, city_of_departure);
            stm.setString(4,flight.getArrival_time());
            stm.setString(5, city_of_arrival);
            stm.setString(6,baggage);
            stm.setString(7,priority_regist_land);
            stm.setLong(8, client_id);
            stm.setInt(9,flight.getFlight_id());

          stm.executeUpdate();

    }catch (SQLException e) {
        e.printStackTrace();
    }

    }

    @Override
    public Ticket read(long client_id) throws SQLException{
        Ticket ticket = new Ticket();
        String sql = "SELECT * FROM Ticket WHERE client_id=? ORDER BY ticket_id DESC;";
        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, client_id);
            ResultSet rs = stm.executeQuery();
            rs.next();

                ticket.setId(rs.getInt("ticket_id"));
                ticket.setFlight_id(rs.getInt("flight_id"));
                ticket.setTime_of_departure(rs.getString("time_of_departure"));
                ticket.setDate_of_departure(rs.getString("date_of_departure"));
                ticket.setCity_of_departure(rs.getString("city_of_departure"));
                ticket.setDeparture_terminal(rs.getString("departure_terminal"));
                ticket.setTime_of_arrival(rs.getString("time_of_arrival"));
                ticket.setCity_of_arrival(rs.getString("city_of_arrival"));
                ticket.setArrival_terminal(rs.getString("arrival_terminal"));
                ticket.setBaggage(rs.getString("baggage"));
                ticket.setPriority_check_in_and_boarding(rs.getString("priority_regist_land"));

            } catch (SQLException e) {
            e.printStackTrace();
        }


        return ticket;
    }

    @Override
    public void update(Ticket ticket, long key) throws SQLException{
        String sql = "UPDATE Ticket SET  price=?, time_of_departure=? WHERE ticket_id=?";

        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setDouble(1, ticket.getPrice());
            stm.setString(2,ticket.getTime_of_departure());
            stm.setLong(3, key);

             stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Ticket ticket, long key) throws SQLException{
        String sql = "DELETE FROM Ticket WHERE ticket_id=?;";
        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1,key);

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public List<Ticket> getAll() throws SQLException {
        String sql = "SELECT * FROM Ticket;";
        List<Ticket> list = new ArrayList<>();
        try(PreparedStatement stm=connection.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Ticket ticket = new Ticket();

                ticket.setId(rs.getInt("ticket_id"));
                ticket.setDate_of_departure(rs.getString("date_of_departure"));
                ticket.setCity_of_departure(rs.getString("city_of_departure"));
                ticket.setDeparture_terminal(rs.getString("departure_terminal"));
                ticket.setCity_of_arrival(rs.getString("city_of_arrival"));
                ticket.setArrival_terminal(rs.getString("arrival_terminal"));
                ticket.setBaggage(rs.getString("baggage"));
                ticket.setPriority_check_in_and_boarding(rs.getString("priority_regist_land"));

                list.add(ticket);
            }
        } catch (SQLException e ){
            e.printStackTrace();
        }

        return list;
    }

}
