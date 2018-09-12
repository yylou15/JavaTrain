package dao;
import Controller.BookInfo;
import Controller.Login;
import global.BookStatus;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBUtil;
import Utils.*;
import model.BookRecord;

public class BookDao {
    public static List<Book> getCollectionByName(String name) throws Exception{
        Connection cn = DBUtil.getConnection();
        PreparedStatement ptmt = cn.prepareStatement("SELECT * FROM tieyif4_user_collection WHERE uid = " + UserDao.getInfoByName(name).getUid());
        ResultSet rs = ptmt.executeQuery();
        List<Book> res = new ArrayList<Book>();
        while (rs.next()){
            res.add(BookDao.getBookByBid(rs.getInt("bid")));
        }
        return res;
    }
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
            book.setScore(rs.getInt("score"));
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

    public static List<Book> getFreeBookByName(String bookName) throws Exception{
        Connection cn = DBUtil.getConnection();
        PreparedStatement ptmt = cn.prepareStatement("SELECT * FROM tieyif4_book_list WHERE `name`=?");
        ptmt.setString(1, bookName);
        ResultSet rs = ptmt.executeQuery();
        List<Book> result = new ArrayList<>();
        while(rs.next()){
            Book book = new Book();
            book.setBid(rs.getInt("bid"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setPagenum(rs.getInt("pagenum"));
            book.setScore(rs.getInt("score"));
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
        PreparedStatement ptmt = cn.prepareStatement("SELECT * FROM tieyif4_book_list WHERE `status` =0 OR `status`=6");
        ResultSet rs = ptmt.executeQuery();
        List<Book> result = new ArrayList<Book>();
        while(rs.next()){
            Book book = new Book();
            book.setBid(rs.getInt("bid"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setPagenum(rs.getInt("pagenum"));
            book.setScore(rs.getInt("score"));
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

    public static List<Book> getBookByOwnerid(int userId) throws Exception{
        Connection cn = DBUtil.getConnection();
        PreparedStatement ptmt = cn.prepareStatement("SELECT * FROM tieyif4_book_list WHERE `ownerid` = " + userId);
        ResultSet rs = ptmt.executeQuery();
        List<Book> result = new ArrayList<Book>();
        while(rs.next()){
            Book book = new Book();
            book.setBid(rs.getInt("bid"));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setPagenum(rs.getInt("pagenum"));
            book.setScore(rs.getInt("score"));
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
            book.setScore(rs.getInt("score"));
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

    public static void updateBookStatus(int bid, BookStatus status) throws Exception{
        Connection cnn = DBUtil.getConnection();
        PreparedStatement ptmt = cnn.prepareStatement("UPDATE tieyif4_book_list SET status=? WHERE `bid`=?");
        ptmt.setInt(1, BookStatusInTable.getIntForBookStatus(status));
        ptmt.setInt(2, bid);
        ptmt.execute();
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

    public static void collect(int uid,int bid){
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement ptmt = conn.prepareStatement("INSERT INTO tieyif4_user_collection VALUES (?,?)");
            ptmt.setInt(1,uid);
            ptmt.setInt(2,bid);
            ptmt.execute();
        }catch (Exception E){
            return;
        }
    }
    public static void canclecollect(int uid,int bid){
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement ptmt = conn.prepareStatement("DELETE FROM tieyif4_user_collection WHERE `uid`=? and `bid`=?");
            ptmt.setInt(1,uid);
            ptmt.setInt(2,bid);
            ptmt.execute();
        }catch (Exception E){
            return;
        }
    }
    public static boolean querycollection(int uid,int bid){
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement ptmt = conn.prepareStatement("SELECT * FROM tieyif4_user_collection WHERE `uid`=? and `bid`=?");
            ptmt.setInt(1,uid);
            ptmt.setInt(2,bid);
            ResultSet rs = ptmt.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception E){
            return false;
        }
    }

    public static void returnBook(int bid,int score) {
        //获取uid
        int uid;
        int ownerid=0;
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement ptmt = conn.prepareStatement("SELECT * FROM tieyif4_book_record WHERE `bid` = " + bid);
            ResultSet rs = ptmt.executeQuery();
            if(rs.next()){
                uid = rs.getInt("uid");
                ownerid = rs.getInt("ownerid");
                System.out.println("bid is:"+bid);
                System.out.println("ownerid is:"+ownerid);
            }
            ptmt = conn.prepareStatement("UPDATE tieyif4_book_record SET `status` = 9 WHERE `bid` = " + bid);
            ptmt.execute();

            ptmt = conn.prepareStatement("UPDATE tieyif4_user_info SET `level_score` = `level_score` + ? WHERE `uid` = ?");
            ptmt.setInt(1,score);
            ptmt.setInt(2,ownerid);
            ptmt.execute();

        }catch (Exception E){
            System.out.println(E.getMessage());
            return ;
        }
    }

    public static int getBookId(int ownerid, String name) throws Exception{
        Connection conn = DBUtil.getConnection();
        PreparedStatement ptmt = conn.prepareStatement("SELECT bid FROM tieyif4_book_list WHERE `owner`=? AND `String`=?");
        ptmt.setInt(1,ownerid);
        ptmt.setString(2,name);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()) {
            return rs.getInt("bid");
        }
        return 0;
    }

    public static void lendBook(int nowBid, String requesterName) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ptmt = conn.prepareStatement("UPDATE tieyif4_book_record SET `status` = 4 WHERE `bid` = ? and `uid` = ?");
            ptmt.setInt(1,nowBid);
            ptmt.setInt(2,UserDao.getInfoByName(requesterName).getUid());
            System.out.println(nowBid);
            System.out.println(UserDao.getInfoByName(requesterName).getUid());
            ptmt.execute();
        }catch (Exception E){
            System.out.println(E.getMessage());
        }
    }

    public static void denyLend(int nowBid, String requesterName) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ptmt = conn.prepareStatement("UPDATE tieyif4_book_record SET `status` = 5 WHERE `bid` = ? and `uid` = ?");
            ptmt.setInt(1,nowBid);
            ptmt.setInt(2,UserDao.getInfoByName(requesterName).getUid());
            ptmt.execute();
        }catch (Exception E){
            System.out.println(E.getMessage());
        }
    }

    public static void confirmReturn(int nowRid) {
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement ptmt = conn.prepareStatement("UPDATE tieyif4_book_record SET `status` = 6 WHERE `rid` = ?");
            ptmt.setInt(1,nowRid);
            ptmt.execute();
        }catch (Exception E){
            System.out.println(E.getMessage());
        }
    }

    public static void confirmReturn(int nowRid,int score) {
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement ptmt = conn.prepareStatement("UPDATE tieyif4_book_record SET `status` = 6 WHERE `rid` = ?");
            ptmt.setInt(1,nowRid);
            ptmt.execute();
            ptmt = conn.prepareStatement("UPDATE tieyif4_user_info SET `level_score` = `level_score` + ? WHERE `uid` = ?");
            ptmt.setInt(1,score);
            ptmt.setInt(2, BookRecordDao.getRecordByRid(nowRid).getUid());
            ptmt.execute();
        }catch (Exception E){
            System.out.println(E.getMessage());
        }
    }


}
