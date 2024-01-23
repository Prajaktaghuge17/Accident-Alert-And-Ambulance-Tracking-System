/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import connection.DBConnection;
import helper.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author PHOENIX DINESH
 */
public class DetectAccident extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            //get location and update accident details
            String latitude = request.getParameter("lat");
            String longitude = request.getParameter("lon");
            String car_id = request.getParameter("id");

            String sql = "INSERT INTO accident_details(car_id,latitude,longitude) VALUES('" + car_id + "','" + latitude + "','" + longitude + "')";

            JSONObject json = new JSONObject();
            //create database object
            DBConnection db = new DBConnection();
            ResultSet rs = db.executeQuery("SELECT * FROM accident_details WHERE car_id='" + car_id + "' AND accident_status=0");
            if (rs.next()) {
                json.put(Constants.FAILED, true);
                json.put(Constants.MESSAGE, "Accident details already added..");

            } else {
                //insert into table
                int row_affected = db.executeUpdate(sql);
                if (row_affected > 0) {
                    json.put(Constants.SUCCESS, true);
                    json.put(Constants.MESSAGE, "Accident details added..");
                } else {
                    json.put(Constants.FAILED, true);
                    json.put(Constants.MESSAGE, "Error while inserting");
                }
            }
            db.close();
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
