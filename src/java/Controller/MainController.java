/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class MainController extends HttpServlet {

    public static final String ERROR = "error.jsp";
    public static final String LOGIN = "LoginController";
    public static final String LOGOUT = "LogoutController";
    public static final String CREATE = "CreateController";
    public static final String SEARCH = "searchController";
    public static final String UPDATE = "UpdateController";
    public static final String SEARCH_PRODUCT_CART = "SearchProductController";
    public static final String ADD_PRODUCT_CART = "AddToCartController";
    public static final String VIEW_PRODUCT_CART = "viewCart.jsp";
    public static final String UPDATE_PRODUCT_CART = "UpdateCartController";
    public static final String REMOVE_PRODUCT_CART = "RemoveCartController";
    public static final String CHECK_OUT = "CheckOutController";
    public static final String CREATE_PRODUCT = "CreateProductController";
    public static final String UPDATE_PRODUCT = "UpdateProductController";
    public static final String SEARCH_PRODUCT = "SearchProductForAdminController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            }
            if ("Create".equals(action)) {
                url = CREATE;
            }
            if ("Search".equals(action)) {
                url = SEARCH;
            }
            if ("Logout".equals(action)) {
                url = LOGOUT;
            }
            if ("Update".equals(action)) {
                url = UPDATE;
            }
            if ("SearchProduct".equals(action)) {
                url = SEARCH_PRODUCT_CART;
            }
            if ("Add to Cart".equals(action)) {
                url = ADD_PRODUCT_CART;
            }
            if ("View Cart".equals(action)) {
                url = VIEW_PRODUCT_CART;
            }
            if ("Modify".equals(action)) {
                url = UPDATE_PRODUCT_CART;
            }
            if ("Remove".equals(action)) {
                url = REMOVE_PRODUCT_CART;
            }
            if ("Check Out".equals(action)) {
                url = CHECK_OUT;
            }
            if ("Insert".equals(action)) {
                url = CREATE_PRODUCT;
            }
            if ("UpdateProduct".equals(action)) {
                url = UPDATE_PRODUCT;
            }
            if ("SearchBook".equals(action)) {
                url = SEARCH_PRODUCT;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_MESSAGE", "Not support this action !");
            }
        } catch (Exception e) {
            log("Error at main conftroller : " + e.toString());
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
