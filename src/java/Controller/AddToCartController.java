/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO;
import DTO.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping.Book;
import shopping.Cart;

/**
 *
 * @author ACER
 */
public class AddToCartController extends HttpServlet {

    private static final String SUCCESS = "shopping.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String BookID = request.getParameter("bookID");
            String name = request.getParameter("bookName");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantityInStore = Integer.parseInt(request.getParameter("quantity"));
            int quantityToCart = Integer.parseInt(request.getParameter("quantityToCart"));
            HttpSession session = request.getSession();
            if (quantityToCart > 0) {
                Book bookToCart = new Book(BookID, price, name, null, quantityToCart, null);

                Cart cart = (Cart) session.getAttribute("CART");
                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                DAO dao = new DAO();
                if (cart == null) {
                    if (dao.insertTblOrders(user)) {
                        cart = new Cart();
                    }
                }
                cart.addToCart(bookToCart);
                session.setAttribute("CART", cart);
                url = SUCCESS;

            }
            String message = "Add to cart: " + quantityToCart + " " + name + " success !";
            session.setAttribute("SHOPPING_MESSAGE", message);
        } catch (Exception e) {
            log("Error at Add To Cart Controller" + e.toString());
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
