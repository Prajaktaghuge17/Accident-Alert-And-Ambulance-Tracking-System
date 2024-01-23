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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author PHOENIX DINESH
 */
public class AcceptAccident extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            PrintWriter out = response.getWriter();
            String email = request.getParameter("email");
            String id = request.getParameter("id");

            DBConnection db = new DBConnection();
            String query = "SELECT *FROM accident_details WHERE id='" + id + "' AND accident_status=1";
            JSONObject json = new JSONObject();
            ResultSet rs = db.executeQuery(query);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Date date = new Date();
            String timeStamp = format.format(date);
            if (rs.next()) {
                json.put("status", 1);
                json.put("message", "Already accepted by another ambulance");
            } else {
              
                query = "UPDATE accident_details SET accident_status=1, ambulance_id='"+email+"',"
                        + "request_accepted='"+timeStamp+"' WHERE id='"+id+"'";
                int rowCount = db.executeUpdate(query);

                if (rowCount > 0) {
                    json.put("success", 1);
                    json.put("message", "You can now see location of accident");
                } else {
                    json.put("failed", 1);
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
