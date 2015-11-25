package com.epam.LowCost.Controller.DAO;

import com.epam.LowCost.Model.Client;

import java.sql.SQLException;
import java.util.List;


public interface ClientDao {
     void create(String login,String password,String firstname,String lastname,String birth_date,String mobile_phone,String email)throws SQLException;
     Client read(String key) throws SQLException;
     void update(String number_of_passport, String login)throws SQLException;
     void delete(Client client,String login) throws SQLException;
     List<Client> getAll() throws SQLException;
     boolean duplicatefinderSignIn(String login,String password,String operation)throws SQLException;

}
