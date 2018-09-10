package dao;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBUtil;
import Utils.*;
public class BookDao {
    public static List<Book> getAllBook() throws Exception{
        Connection cn = DBUtil.getConnection();
        PreparedStatement ptmt = cn.prepareStatement("SELECT * FROM tieyif4_book_list");
        ResultSet rs = ptmt.executeQuery();
        List<Book> result = new ArrayList<Book>();
        while(rs.next()){
            Book book = new Book();
            book.setBid(rs.getInt("bid"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setPagenum(rs.getInt("pagenum"));
            book.setIntroduction(rs.getString("introduction"));
            book.setStatus(rs.getInt("status"));
            book.setCount(rs.getInt("count"));
            book.setOwnerid(rs.getInt("ownerid"));
            book.setHolderid(rs.getInt("holderid"));
            System.out.println(rs.getInt("bid"));
            result.add(book);
        }
        return result;
    }
    public static List<Book> getValidBook() throws Exception{
        Connection cn = DBUtil.getConnection();
        PreparedStatement ptmt = cn.prepareStatement("SELECT * FROM tieyif4_book_list WHERE `status` = 0");
        ResultSet rs = ptmt.executeQuery();
        List<Book> result = new ArrayList<Book>();
        while(rs.next()){
            Book book = new Book();
            book.setBid(rs.getInt("bid"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setPagenum(rs.getInt("pagenum"));
            book.setIntroduction(rs.getString("introduction"));
            book.setStatus(rs.getInt("status"));
            book.setCount(rs.getInt("count"));
            book.setOwnerid(rs.getInt("ownerid"));
            book.setHolderid(rs.getInt("holderid"));
            System.out.println(rs.getInt("bid"));
            result.add(book);
        }
        return result;
    }
    public static Book getBookByBid(int bid) throws Exception{
        Connection cn = DBUtil.getConnection();
        PreparedStatement ptmt = cn.prepareStatement("SELECT * FROM tieyif4_book_list WHERE `bid` = " + bid);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()){
            Book book = new Book();
            book.setBid(rs.getInt("bid"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setPagenum(rs.getInt("pagenum"));
            book.setIntroduction(rs.getString("introduction"));
            book.setStatus(rs.getInt("status"));
            book.setCount(rs.getInt("count"));
            book.setOwnerid(rs.getInt("ownerid"));
            book.setHolderid(rs.getInt("holderid"));
            System.out.println(rs.getString("name"));
            return book;
        }else {
            return null;
        }
    }
    public static returnObj updateBook(Book book){
        try {
            Connection cnn = DBUtil.getConnection();
            PreparedStatement ptmt = cnn.prepareStatement("UPDATE tieyif4_book_list SET name=?,author=?,pagenum=?,introduction=?,status=?,ownerid=?,holderid=? WHERE `bid` = " + book.getBid());
            ptmt.setString(1,book.getName());
            ptmt.setString(2,book.getAuthor());
            ptmt.setInt(3,book.getPagenum());
            ptmt.setString(4,book.getIntroduction());
            ptmt.setInt(5,book.getStatus());
            ptmt.setInt(6,book.getOwnerid());
            ptmt.setInt(7,book.getHolderid());
            ptmt.execute();
            returnObj res = new returnObj();
            res.setStatus(true);
            return  res;
        }catch (Exception E){
            returnObj res = new returnObj();
            res.setStatus(false);
            res.setMsg(E.getMessage());
            return  res;
        }

    }
    public static returnObj insertBook(Book book) throws Exception{
        try {
            Connection cnn = DBUtil.getConnection();
            PreparedStatement ptmt = cnn.prepareStatement("INSERT INTO tieyif4_book_list (name,author,pagenum,introduction,status,ownerid) VALUES (?,?,?,?,?,?)");
            ptmt.setString(1,book.getName());
            ptmt.setString(2,book.getAuthor());
            ptmt.setInt(3,book.getPagenum());
            ptmt.setString(4,book.getIntroduction());
            ptmt.setInt(5,0);
            ptmt.setInt(6,book.getOwnerid());
            ptmt.execute();
            returnObj res = new returnObj();
            res.setStatus(true);
            return  res;
        }catch (Exception E){
            returnObj res = new returnObj();
            res.setStatus(false);
            res.setMsg(E.getMessage());
            return  res;
        }
    }
}
