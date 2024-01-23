/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author PHOENIX DINESH
 */
public class GetAccidentDetails extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            double lat1 = Double.parseDouble(request.getParameter("latitude"));
            double lon1 = Double.parseDouble(request.getParameter("longitude"));
            String sql = "SELECT ad.*,tu.username,tu.mobile,tu.email FROM accident_details ad INNER JOIN tbl_users tu ON ad.car_id=tu.email  WHERE accident_status=0";/*and logged_in='online' need to add */
            System.out.println(sql);
            JSONObject json = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            DBConnection db = new DBConnection();
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                double lat2 = Double.parseDouble(rs.getString("latitude"));
                double lon2 = Double.parseDouble(rs.getString("longitude"));
                double dist = distance(lat1, lon1, lat2, lon2, 'K');
                if (dist < 10) {
                    JSONObject object = new JSONObject();
                    object.put("id", rs.getString("id"));
                    object.put("username", rs.getString("username"));
                    object.put("email", rs.getString("email"));
                    object.put("mobile", rs.getString("mobile"));
                    object.put("lat", rs.getString("latitude"));
                    object.put("lon", rs.getString("longitude"));
                    object.put("status", rs.getString("accident_status"));
                    object.put("added_on", rs.getString("added_on"));
                    jSONArray.put(object);
                }
            }
            json.put("success", 1);
            json.put("AccidentInfo", jSONArray);
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
 /*::  This function converts decimal degrees to radians             :*/
 /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
 /*::  This function converts radians to decimal degrees             :*/
 /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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
