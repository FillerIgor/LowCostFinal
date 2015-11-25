package com.epam.LowCost.Controller.DAO;

import java.sql.Connection;
import java.sql.SQLException;


public interface DaoFactory {
    Connection getConnection() throws SQLException;
    ClientDaoImpl getClientDao(Connection connection) throws SQLException;
    TicketDaoImpl getTicketDao(Connection connection) throws SQLException;
    FlightDaoImpl getFlightDao(Connection connection) throws SQLException;
}
