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
        String sql="select comment from tieyif4_book_comment where 'bid'="+bid;
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ResultSet rs=ptmt.executeQuery();
            List<BookComment> result=new ArrayList<BookComment>();
            while(rs.next()) {
                BookComment bookComment=new BookComment();
                bookComment.setCid(rs.getInt(1));
                bookComment.setBid(rs.getInt(2));
                bookComment.setUid(rs.getInt(3));
                bookComment.setScore(rs.getInt(4));
                bookComment.setComment(rs.getString(5));
                bookComment.setCreateTime(rs.getString(6));
                bookComment.setUserName(rs.getString(7));
                bookComment.setLike(rs.getInt(8));
                bookComment.setDislike(rs.getInt(9));
                result.add(bookComment);
                return result;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        return null;



    }
    public static returnObj uploadComment(BookComment comment) {
        Connection conn=DBUtil.getConnection();
        String sql="insert into tieyif4_book_comment(comment)values(?) ";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.setString(1,comment.getComment());
            ptmt.execute();
            returnObj rs=new returnObj();
            rs.setStatus(true);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            returnObj rs=new returnObj();
            rs.setStatus(false);
            return rs;
        }finally {
            DBUtil.close(conn);
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
        }finally {
            DBUtil.close(conn);
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
        }finally {
            DBUtil.close(conn);
        }
    }
}
