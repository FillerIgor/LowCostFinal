package com.epam.LowCost.Controller;

import com.epam.LowCost.Controller.DAO.DaoFactoryImpl;
import com.epam.LowCost.Model.Client;
import com.epam.LowCost.Model.Flight;
import com.epam.LowCost.Model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

@WebServlet(name = "ServletLowCost", urlPatterns = "/ServletLowCost")
public class ServletLowCost extends HttpServlet {
    private DaoFactoryImpl daoFactory;
    private String login;
    private Ticket ticket;
    private Client client;
    private  Flight flight;
    static final Logger log = Logger.getLogger(ServletLowCost.class);




    public ServletLowCost() {
        super();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        requestHandler(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        daoFactory= new DaoFactoryImpl();
        try (Connection connection = daoFactory.getConnection()) {

           daoFactory.getFlightDao(connection).Init();

            switch (request.getParameter("page_name")) {
                case "index":

                    if (request.getParameter("sign_in") != null) {
                        if (daoFactory.getClientDao(connection).duplicatefinderSignIn(request.getParameter("login"), request.getParameter("password"), "AND")) {
                            login = request.getParameter("login");
                            request.getRequestDispatcher("welcome.jsp").forward(request, response);

                        } else {
                            PrintWriter out = response.getWriter();
                            out.println("<p>You entered incorrect login or password.Please try again or press button registration.</p>");
                            request.getRequestDispatcher("index.jsp").include(request, response);
                        }
                    }
                    if (request.getParameter("registration") != null) {
                        request.getRequestDispatcher("registration.jsp").forward(request, response);
                    }

                    break;
                case "registration":
                    if (daoFactory.getClientDao(connection).duplicatefinderSignIn(request.getParameter("login"), request.getParameter("password"), "OR")) {
                        PrintWriter out = response.getWriter();
                        out.println("<p>This login already exist.Please try again.</p>");
                        request.getRequestDispatcher("registration.jsp").include(request, response);
                        break;
                    }
                    daoFactory.getClientDao(connection).create(request.getParameter("login"), request.getParameter("password"),
                            request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("birth_date"),
                            request.getParameter("mobile_phone"), request.getParameter("email"));
                    request.getRequestDispatcher("welcome.jsp").forward(request, response);

                    break;
                case "welcome":

                    client = daoFactory.getClientDao(connection).read(login);
                    if (daoFactory.getFlightDao(connection).isAvailablePlace(request.getParameter("date_of_departure"), request.getParameter("city_of_departure"), request.getParameter("city_of_arrival"))) {
                        flight = daoFactory.getFlightDao(connection).read(request.getParameter("date_of_departure"), request.getParameter("city_of_departure"), request.getParameter("city_of_arrival"));
                        daoFactory.getTicketDao(connection).create(request.getParameter("date_of_departure"), request.getParameter("city_of_departure"), request.getParameter("city_of_arrival"),
                                request.getParameter("baggage"), request.getParameter("priority_regist_land"), client.getId(),flight);

                        request.getRequestDispatcher("order.jsp").forward(request, response);
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("<p>No available flights. Please choose another day/month.</p>");
                       List<String> availabledates= daoFactory.getFlightDao(connection).datesOfDirection(request.getParameter("city_of_departure"),request.getParameter("city_of_arrival"));
                        for(String date: availabledates){
                            out.println("<p>Available date on your direction:"+date+"</p>");
                        }
                        request.getRequestDispatcher("welcome.jsp").include(request, response);
                    }
                    break;
                case "order":
                     ticket = daoFactory.getTicketDao(connection).read(client.getId());
                    client.setNumber_of_passport(request.getParameter("number_of_passport"));
                    daoFactory.getClientDao(connection).update(request.getParameter("number_of_passport"), login);

                        try {
                            ticket.calculatePrice(flight.getFlight_places());
                        } catch (ParseException e) {
                            log.error(e);
                          e.printStackTrace();
                        }
                        daoFactory.getTicketDao(connection).update(ticket, ticket.getId());


                    PrintWriter out = response.getWriter();
                    out.println("<p>Your credit card number:" + request.getParameter("credit_card_number") + "</p>");
                    out.println("<p>Your number of passport:" + request.getParameter("number_of_passport") + "</p>");

                    request.setAttribute("ticket", ticket);
                    request.setAttribute("client", client);
                    DecimalFormat dx= new DecimalFormat("####0.00");
                    request.setAttribute("ticket_price",dx.format(ticket.getPrice()));

                    request.getRequestDispatcher("result_order.jsp").include(request, response);

                    break;
                case "result_order":
                    request.getRequestDispatcher("index.jsp").include(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            log.error(e);
            e.printStackTrace();
        }
    }
}
