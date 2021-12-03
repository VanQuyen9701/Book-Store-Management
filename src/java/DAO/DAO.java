/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DButils.DBUtils;
import DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import shopping.Book;
import shopping.Cart;

/**
 *
 * @author ACER
 */
public class DAO {

    // USER
    public UserDTO checkLogin(String userID, String password) {

        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT fullname, roleID, status FROM tblUsers WHERE userID =? and password=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    String roleID = rs.getString("roleID");
                    String status = rs.getString("status");
                    user = new UserDTO(userID, fullname, "", roleID, "", status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public List<UserDTO> getListUser(String search) {
        List<UserDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT userID, fullname, address, status, roleID FROM tblUsers  WHERE fullname like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullname = rs.getString("fullname");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    String address = rs.getString("address");
                    String status = rs.getString("status");
                    list.add(new UserDTO(userID, fullname, address, roleID, password, status));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean insertUser(UserDTO user) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUsers(UserID,fullname,address,roleID,password,status) VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getId());
                stm.setString(2, user.getName());
                stm.setString(3, user.getAddress());
                stm.setString(4, user.getRoleID());
                stm.setString(5, user.getPassword());
                stm.setString(6, user.getStatus());
                check = stm.executeUpdate() > 0;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public boolean updateUser(UserDTO user) {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE tblUsers SET fullname=?, roleID=?, address=?, status=? WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getName());
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getAddress());
                stm.setString(4, user.getStatus());
                stm.setString(5, user.getId());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    // BOOK
    public List<Book> getBookList(String search) {
        List<Book> list = new ArrayList();

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT productID, name, quantity, price, categoryID, status FROM tblProduct WHERE name like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("productID");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String category = rs.getString("categoryID");
                    String status = rs.getString("status");
                    if (status.equals("ON")) {
                        list.add(new Book(bookID, price, name, category, quantity, status));
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean insertTblOrders(UserDTO user) {
        boolean check = false;

        Connection con = null;
        PreparedStatement stm = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        String dateToDB = sdf.format(date);

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO tblOrders(userID,dateOrder,status) VALUES(?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getId());
                stm.setString(2, dateToDB);
                stm.setString(3, "ON");
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return check;
    }

    public boolean updateAfterCheckOut(UserDTO user) {
        boolean check = false;

        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE tblOrders SET status=? WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "DONE");
                stm.setString(2, user.getId());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return check;
    }

    public String getStatus(UserDTO user) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String status = new String();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        String dateToDB = sdf.format(date);
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sqlOrderID = "SELECT status FROM tblOrders WHERE userID= ? and dateOrder = ?";
                stm = con.prepareStatement(sqlOrderID);
                stm.setString(1, user.getId());
                stm.setString(2, dateToDB);
                rs = stm.executeQuery();
                while (rs.next()) {
                    status = rs.getString("status");
                }
                if (status.equals("DONE")) {
                    return status;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getOrderID(UserDTO user) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String orderID = new String();
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        String dateToDB = sdf.format(date);
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sqlOrderID = "SELECT orderID FROM tblOrders WHERE userID= ? and dateOrder = ?";
                stm = con.prepareStatement(sqlOrderID);
                stm.setString(1, user.getId());
                stm.setString(2, dateToDB);
                rs = stm.executeQuery();
                while (rs.next()) {
                    orderID = rs.getString("orderID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderID;
    }

    public boolean insertTblOrdersDetail(Cart cart, String orderID, String status) {
        boolean check = false;

        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (status.equals("DONE")) {
                    for (Book dto : cart.getCart().values()) {

                        String sql = "INSERT INTO tblOrdersDetail(productID, orderID, quantity, price, total) VALUES(?,?,?,?,?)";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, dto.getBookID());
                        stm.setString(2, orderID);
                        stm.setInt(3, dto.getQuantity());
                        stm.setDouble(4, dto.getPrice());
                        stm.setDouble(5, dto.getPrice() * dto.getQuantity());
                        check = stm.executeUpdate() > 0;
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return check;
    }

    public boolean updateTblProduct(List<Book> book) {
        boolean check = false;

        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (Book dto : book) {
                    String sql = "UPDATE tblProduct SET quantity=? WHERE productID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, dto.getQuantity());
                    stm.setString(2, dto.getBookID());
                    check = stm.executeUpdate() > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return check;
    }

    // for ADMIN
    public List<Book> getBookListForAdmin(String search) {
        List<Book> list = new ArrayList();

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT productID, name, quantity, price, categoryID, status FROM tblProduct WHERE name like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("productID");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String category = rs.getString("categoryID");
                    String status = rs.getString("status");

                    list.add(new Book(bookID, price, name, category, quantity, status));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean insertProductForAdmin(Book book) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblProduct(productID,name,quantity,price,categoryID,status) VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, book.getBookID());
                stm.setString(2, book.getName());
                stm.setInt(3, book.getQuantity());
                stm.setDouble(4, book.getPrice());
                stm.setString(5, book.getCatagory());
                stm.setString(6, book.getStatus());
                check = stm.executeUpdate() > 0;
            }
        }  finally {

                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            

        }
        return check;
    }

    public boolean updateProductForAdmin(Book book) {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE tblProduct SET name=?, quantity=?, price=?, categoryID=?, status=? WHERE productID = ?";
                stm = con.prepareStatement(sql);

                stm.setString(1, book.getName());
                stm.setInt(2, book.getQuantity());
                stm.setDouble(3, book.getPrice());
                stm.setString(4, book.getCatagory());
                stm.setString(5, book.getStatus());
                stm.setString(6, book.getBookID());

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }

}
