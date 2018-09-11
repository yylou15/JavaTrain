package dao;
import model.BookComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBUtil;
import Utils.*;
public class BookCommentDao {
    public static List<BookComment> getCommentsByBookId(int bid){
        Connection conn=DBUtil.getConnection();
        String sql="select *  from tieyif4_book_comment where `bid`="+bid;
        System.out.println(sql);
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ResultSet rs=ptmt.executeQuery();
            List<BookComment> result=new ArrayList<BookComment>();
            while(rs.next()) {
                BookComment bookComment=new BookComment();
                bookComment.setCid(rs.getInt("cid"));
                bookComment.setBid(rs.getInt("bid"));
                bookComment.setScore(rs.getInt("score"));
                bookComment.setComment(rs.getString("comment"));
                bookComment.setCreateTime(rs.getString("createtime"));
                bookComment.setUserName(rs.getString("username"));
                bookComment.setLike(rs.getInt("like"));
                bookComment.setDislike(rs.getInt("dislike"));
                result.add(bookComment);
            }
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;


    }
    public static returnObj uploadComment(BookComment comment) {
        Connection conn=DBUtil.getConnection();
        String sql="INSERT INTO `tieyif4_book_comment`(`bid`, `score`, `comment`, `createtime`, `username`, `like`, `dislike`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.setInt(1,comment.getBid());
            ptmt.setInt(2,comment.getScore());
            ptmt.setString(3,comment.getComment());
            ptmt.setString(4,comment.getCreateTime());
            ptmt.setString(5,comment.getUserName());
            ptmt.setInt(6,comment.getLike());
            ptmt.setInt(7,comment.getDislike());
            ptmt.execute();
            returnObj rs=new returnObj();
            rs.setStatus(true);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            returnObj rs=new returnObj();
            rs.setStatus(false);
            return rs;
        }


    }
    public static returnObj deleteComment(int commentid){

        Connection conn=DBUtil.getConnection();
        String sql="delete from tieyif4_book_comment where 'cid'="+commentid;
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.executeUpdate();
            returnObj rs=new returnObj();
            rs.setStatus(true);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            returnObj rs=new returnObj();
            rs.setStatus(false);
            return rs;
        }
    }
    public static returnObj updateComment(BookComment comment){
        Connection conn=DBUtil.getConnection();
        String sql="update tieyif4_book_comment set comment=? where 'bid' ="+ comment.getCid();
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.executeUpdate();
            returnObj rs=new returnObj();
            rs.setStatus(true);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            returnObj rs=new returnObj();
            rs.setStatus(false);
            return rs;
        }
    }
}
