package com.eventmanagement.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.eventmanagement.dao.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddEventServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String type = request.getParameter("type");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO events (name, date, location, description, type) VALUES (?, ?, ?, ?, ?)")) {
            
            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setString(3, location);
            stmt.setString(4, description);
            stmt.setString(5, type);
            stmt.executeUpdate();

            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
