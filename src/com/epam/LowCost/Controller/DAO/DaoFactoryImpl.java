package com.epam.LowCost.Controller.DAO;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl implements DaoFactory {

    private MysqlDataSource dataSource;


    @Override
    public Connection getConnection() throws SQLException {
        try {
            dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("root123");
            dataSource.setURL("jdbc:mysql://localhost:3306/lowcost");
        } catch (Exception e) {

            e.printStackTrace();
        }
        return  dataSource.getConnection();
    }

    @Override
    public ClientDaoImpl getClientDao(Connection connection) throws SQLException {
        return new ClientDaoImpl(connection);
    }

    @Override
    public TicketDaoImpl getTicketDao(Connection connection) throws SQLException {
        return new TicketDaoImpl(connection);
    }

    @Override
    public FlightDaoImpl getFlightDao(Connection connection) throws SQLException {
        return new FlightDaoImpl(connection);
    }

}
