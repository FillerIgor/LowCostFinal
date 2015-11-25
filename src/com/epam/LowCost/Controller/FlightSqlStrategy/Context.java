package com.epam.LowCost.Controller.FlightSqlStrategy;

/**
 * Created by Igor on 24.11.2015.
 */
public class Context {
    private  FlightStrategy flightStrategy;
    public Context(FlightStrategy flightStrat){
        this.flightStrategy = flightStrat;
    }
    public String executeFlightStrategy(){
        return flightStrategy.getSQL();
    }
}
