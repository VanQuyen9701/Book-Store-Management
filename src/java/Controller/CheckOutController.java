/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO;
import DTO.UserDTO;
import java.io.IOException;
import java.util.List;
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
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            DAO dao = new DAO();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            List<Book> list = dao.getBookList("");
            boolean check = true;
            for (Book book : cart.getCart().values()) {
                for (int i = 0; i < list.size(); i++) {

                    if (book.getBookID().equals(list.get(i).getBookID())) {
                        if (list.get(i).setQuantity(list.get(i).getQuantity() - book.getQuantity())) {
                            if (dao.updateAfterCheckOut(user)) {
                                check = true;
                            }
                        } else {
                            session.setAttribute("CHECK_OUT", "Quantity is not valid : "+list.get(i).getName());
                            check = false;
                            return;
                        }
                    }
                }

            }
            if (check) {
                String status = dao.getStatus(user);
                String orderID = dao.getOrderID(user);
                if (dao.insertTblOrdersDetail(cart, orderID, status)) {
                    if (dao.updateTblProduct(list)) {
                        check = true;
                    }
                }
                if (check) {
                    session.setAttribute("CART", null);
                    session.setAttribute("CHECK_OUT", "You have made a successful purchase !");
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at checkout controller" + e.toString());
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
