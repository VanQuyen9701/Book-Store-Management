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
public class UpdateController extends HttpServlet {

    private static final String ERROR = "searchController";
    private static final String SUCCESS = "searchController";
    private static final String UPDATE_ADMIN = "LogoutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullname = request.getParameter("name");
            String roleID = request.getParameter("roleID");
            String address = request.getParameter("address");
            String status = request.getParameter("status");
            UserError error = new UserError("", "", "", "", "", "");
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            boolean check = true;
            if (fullname.length() < 5) {
                error.setNameError("Full name must be least 5 character");
                check = false;
            }
            if (roleID.length() < 2) {
                error.setRoleIdError("RoleID >2");
                check = false;
            }
            if (address.length() < 5) {
                error.setAddressError("Address must be least 5 character");
                check = false;
            }
            if (check) {
                DAO dao = new DAO();
                UserDTO user = new UserDTO(userID, fullname, address, roleID, "", status);
                boolean checkUpdate = dao.updateUser(user);
                if (checkUpdate) {
                    if (userID.equals(loginUser.getId())) {
                        url = UPDATE_ADMIN;
                    } else {
                        url = SUCCESS;
                    }

                }
            } else {
                request.setAttribute("USER_ERROR", error);
            }
        } catch (Exception e) {
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
