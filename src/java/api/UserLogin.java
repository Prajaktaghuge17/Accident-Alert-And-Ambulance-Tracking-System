/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import connection.DBConnection;
import helper.PushNotifictionHelper;
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
public class UserLogin extends HttpServlet {

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
            String password = request.getParameter("password");
            String uType = request.getParameter("utype");
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");

            JSONObject json = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            DBConnection db = new DBConnection();
            String str = "SELECT * FROM tbl_users where email = '" + email + "' and password = '" + password + "' and utype='" + uType + "'";
            String sql = "UPDATE tbl_users set latitude='" + latitude + "', longitude='" + longitude + "' WHERE email='" + email + "'";

            int i = db.executeUpdate(sql);
            ResultSet rs = db.executeQuery(str);
            if (rs.next()) {
                String status = rs.getString("status_");
                JSONObject profileInfo = new JSONObject();
                profileInfo.put("username", rs.getString("username"));
                profileInfo.put("mno", rs.getString("mobile"));
                profileInfo.put("email", rs.getString("email"));
                profileInfo.put("address", rs.getString("address"));
                profileInfo.put("status", status);
                String message = "Welcome to Ambulance System";
                String token = rs.getString("register_token");

                PushNotifictionHelper.sendPushNotification(token, uType, message);

                jSONArray.put(profileInfo);
                json.put("profileInfo", jSONArray);
                json.put("success", 1);
            } else {
                json.put("failed", 1);
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
