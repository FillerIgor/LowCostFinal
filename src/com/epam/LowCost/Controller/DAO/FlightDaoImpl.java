package com.epam.LowCost.Controller.DAO;

import com.epam.LowCost.Controller.FlightSqlStrategy.*;
import com.epam.LowCost.Model.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;


public class FlightDaoImpl implements FlightDao {
    private Connection connection;
    FlightDaoImpl(Connection con){
        this.connection=con;
    }
    public void Init(){
        Context context;
        LocalDate now = LocalDate.now();
        String sql ="SELECT date_of_departure FROM Flight WHERE flight_id=(SELECT MAX(flight_id) FROM Flight);";
        try(Statement stm = connection.createStatement()){
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            String[] data=rs.getString("date_of_departure").split("-");
            if(now.getDayOfMonth()==Integer.valueOf(data[2])){
                delete();
                for(int i=0,j=0;i<5;i++){
                    try(Statement stm1=connection.createStatement()){
                        context = new Context(new Spb_Msk(now.plusDays(j++).toString()));
                        stm1.addBatch(context.executeFlightStrategy());
                        context = new Context(new Spb_Simf(now.plusDays(j++).toString()));
                        stm1.addBatch(context.executeFlightStrategy());
                        context = new Context(new Spb_Minsk(now.plusDays(j++).toString()));
                        stm1.addBatch(context.executeFlightStrategy());
                        context = new Context(new Msk_Spb(now.plusDays(j++).toString()));
                        stm1.addBatch(context.executeFlightStrategy());
                        context = new Context(new Simf_Spb(now.plusDays(j++).toString()));
                        stm1.addBatch(context.executeFlightStrategy());
                        context = new Context(new Minsk_Spb(now.plusDays(j++).toString()));
                        stm1.addBatch(context.executeFlightStrategy());
                        stm1.executeBatch();

                    }catch (SQLException e){
                        e.printStackTrace();

                    }

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    @Override
    public void create(String time, String city_of_departure, String arrival_city,String flight_places) throws SQLException {

        String sql = "INSERT INTO Flight (city_of_departure,time,arrival_city,flight_places) VALUES(?,?,?,?);";


        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, city_of_departure);
            stm.setString(2,time);
            stm.setString(3, arrival_city);
            stm.setString(4, flight_places);


            stm.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Flight read(String date_of_departure, String city_of_departure,String city_of_arrival) throws SQLException {
        Flight flight = new Flight();
        String sql = "SELECT * FROM Flight WHERE date_of_departure=? AND city_of_departure=? AND arrival_city=? ;";
        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, date_of_departure);
            stm.setString(2,city_of_departure);
            stm.setString(3,city_of_arrival);
            ResultSet rs = stm.executeQuery();
            rs.next();
                flight.setFlight_id(rs.getInt("flight_id"));
                flight.setCity_of_departure(rs.getString("city_of_departure"));
                flight.setDate_of_departure(rs.getString("date_of_departure"));
                flight.setTime(rs.getString("time"));
                flight.setArrival_city(rs.getString("arrival_city"));
                flight.setArrival_time(rs.getString("arrival_time"));
                flight.setFlight_places(rs.getInt("flight_places"));


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return flight;
    }

    @Override
    public void update(int flight_places, int key) throws SQLException {
        String sql = "UPDATE Flight SET  flight_places=? WHERE flight_id=?";

        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, flight_places);

            stm.setInt(2, key);

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete( ) throws SQLException {
        String sql = "DELETE FROM Flight ;";
        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Flight> getAll() throws SQLException {
        String sql = "SELECT * FROM Flight;";
        ArrayList<Flight> list = new ArrayList<>();
        try(PreparedStatement stm=connection.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Flight flight = new Flight();

                flight.setFlight_id(rs.getInt("flight_id"));
                flight.setCity_of_departure(rs.getString("city_of_departure"));
                flight.setTime(rs.getString("time"));
                flight.setArrival_city(rs.getString("arrival_city"));
                flight.setFlight_places(rs.getInt("flight_places"));

                list.add(flight);
            }
        } catch (SQLException e ){
            e.printStackTrace();
        }

        return list;
    }
    public boolean isAvailablePlace(String date_of_departure, String city_of_departure, String arrival_city) throws SQLException{

        String sql= "SELECT flight_id, flight_places FROM Flight WHERE city_of_departure=? AND arrival_city=? AND date_of_departure=?;";
        try(PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setString(1, city_of_departure);
            stm.setString(2, arrival_city);
            stm.setString(3, date_of_departure);
            ResultSet rs= stm.executeQuery();

            if(rs.next()){
                if(rs.getInt("flight_places")!=0) {
                    update(rs.getInt("flight_places")-1,rs.getInt("flight_id"));
                    return true;
                }else{
                    return false;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
    public LinkedList<String> datesOfDirection(String city_of_departure, String arrival_city) throws SQLException{
        LinkedList<String> resultdates = new LinkedList<>();
        int i=0;
        String sql= "SELECT date_of_departure FROM Flight WHERE city_of_departure=? AND arrival_city=? ;";
        try(PreparedStatement stm= connection.prepareStatement(sql)){
            stm.setString(1,city_of_departure);
            stm.setString(2,arrival_city);
            ResultSet rs= stm.executeQuery();
            while (rs.next()){
                resultdates.add(rs.getString("date_of_departure"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultdates;
    }


}
