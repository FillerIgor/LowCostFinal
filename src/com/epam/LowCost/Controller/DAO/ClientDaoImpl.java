package com.epam.LowCost.Controller.DAO;

import com.epam.LowCost.Model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private Connection connection;
    ClientDaoImpl(Connection con){
        this.connection = con;
    }
    @Override
    public void create(String login,String password,String firstname,String lastname,String birth_date,String mobile_phone,String email) {
        String sql = "INSERT INTO Client (login,password,firstname,lastname,birth_date,mobile_phone,email) VALUES(?,?,?,?,?,?,?);";
        try(PreparedStatement stm = connection.prepareStatement(sql)) {

                stm.setString(1,login);
                stm.setString(2,password);
                stm.setString(3,firstname);
                stm.setString(4,lastname);
                stm.setString(5,birth_date);
                stm.setString(6,mobile_phone);
                stm.setString(7,email);

                stm.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Client read(String key) throws SQLException{

        String sql = "SELECT * FROM Client WHERE login=?;";
        Client client = new Client();
        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();

            client.setId(rs.getLong("client_id"));
            client.setLogin(rs.getString("login"));
            client.setPassword(rs.getString("password"));
            client.setFirstname(rs.getString("firstname"));
            client.setLastname(rs.getString("lastname"));
            client.setBirth_date(rs.getString("birth_date"));
            client.setNumber_of_passport(rs.getString("number_of_passport"));
            client.setMobile_phone(rs.getString("mobile_phone"));
            client.setEmail(rs.getString("email"));


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return client;
    }

    @Override
    public void update(String number_of_passport, String login) {

        String sql = "UPDATE Client SET  number_of_passport=? WHERE login=?";

        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, number_of_passport);
            stm.setString(2, login);

            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Client client, String login) {
        String sql = "DELETE FROM Client WHERE login=?;";
        try(PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1,login);

             stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public List<Client> getAll() throws SQLException {
        String sql = "SELECT * FROM Client;";
        List<Client> list = new ArrayList<>();
        try(PreparedStatement stm=connection.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Client client = new Client();

                client.setId(rs.getInt("client_id"));
                client.setLogin(rs.getString("login"));
                client.setPassword(rs.getString("password"));
                client.setFirstname(rs.getString("firstname"));
                client.setLastname(rs.getString("lastname"));
                client.setBirth_date(rs.getString("birth_date"));
                client.setNumber_of_passport(rs.getString("number_of_passport"));
                client.setMobile_phone(rs.getString("mobile_phone"));
                client.setEmail(rs.getString("email"));

                list.add(client);
            }
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return list;
    }
    public boolean duplicatefinderSignIn(String login, String password,String operation) throws SQLException{
        String sql= "SELECT * FROM Client WHERE login=? AND password=?;";
         if (operation.equals("OR")){
            sql = "SELECT * FROM Client WHERE login=? ;";
        }
        try(PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, login);
            stm.setString(2,password);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
        e.printStackTrace();
    }
        return false;
    }
}
