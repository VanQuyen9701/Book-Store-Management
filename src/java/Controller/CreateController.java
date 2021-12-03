/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO;
import DTO.UserDTO;
import DTO.UserError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError error = new UserError("", "", "", "", "", "");
        try {
            HttpSession session = request.getSession();

            boolean check = true;

            String userID = request.getParameter("userID");
            String fullname = request.getParameter("fullname");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String confirmPass = request.getParameter("confirmPassword");

            if (userID.length() > 10 || userID.length() < 2) {
                error.setUserIdError("2 to 10 character");
                check = false;
            }
            if (fullname.length() > 20 || fullname.length() < 2) {
                error.setNameError("2 to 20 character");
                check = false;
            }
            if (address.length() > 100 || fullname.length() < 2) {
                error.setAddressError("2 to 100 character");
                check = false;
            }
            if (!password.equals(confirmPass)) {
                error.setConfirmPasswordError("Confirm password must be same your password");
                check = false;
            }
            if (check) {
                DAO userDAO = new DAO();
                UserDTO userNew = new UserDTO(userID, fullname, address, "US", password, "ON");
                boolean checkInsert = userDAO.insertUser(userNew);
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_ERROR", error);
            }
        } catch (Exception e) {
            log("ERROR at create controller" + e.toString());
            if (e.toString().contains("duplicate")) {
                error.setUserIdError("Duplicate userID, please input new UserID !");
                request.setAttribute("USER_ERROR", error);
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
