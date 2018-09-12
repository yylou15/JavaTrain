package Controller;

import dao.UserDao;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Comment不需要跳转 但是它要点赞
public class BookItemComment extends AnchorPane implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    private PageIndex belongId;
    public void setBelongId(PageIndex belongId) {
        this.belongId = belongId;
    }

    @FXML
    private Label bookCommentUserNameLabel;
//    @FXML
//    private Label bookCommentUserLikeNumLabel;
    @FXML
    private Label bookCommentUserTimeLabel;
    @FXML
    private ImageView bookCommentUserImgView;
    @FXML
    private Label bookCommentUserComLabel;
    @FXML
    private Label bookCommentUserScoreLabel;

    private String userName, userGivenScore, userCreateTime, userComment;
    private String userLikeNum;
    private String userImgPath;
    private Image userImg;

    public BookItemComment() {
        System.out.println("BookItemComment Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_comment.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookItemComment(String userName, String userGivenScore, String userCreateTime, String userComment, String userLikeNum, String userImgPath, PageIndex belongId) {
//        this(userName, userGivenScore, userCreateTime, userComment, userLikeNum, userImgPath);
        this.userName = userName;
        this.userGivenScore = userGivenScore;
        this.userCreateTime = userCreateTime;
        this.userComment = userComment;
        this.userLikeNum = userLikeNum;
        this.userImgPath = userImgPath;
        this.belongId = belongId;
        System.out.println("BookItemComment Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_comment.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        initTestData();
        setData();
        setLayout();
    }

    private void initTestData() {
        userName = GlobalConst.TEST_USER_NAME;
        userGivenScore = GlobalConst.TEST_BOOK_SCORE;
        userCreateTime  = GlobalConst.TEST_USER_UPLOAD_TIME;
        userComment = GlobalConst.TEST_USER_COMMENT;
        userLikeNum = "57";
    }

    private void setData() {
        bookCommentUserNameLabel.setText(userName);
        bookCommentUserScoreLabel.setText(userGivenScore  + "分");
        bookCommentUserTimeLabel.setText(userCreateTime);
        bookCommentUserComLabel.setText(userComment);

        userImg = new Image(userImgPath);
        bookCommentUserImgView.setImage(userImg);

//        bookCommentUserLikeNumLabel.setText(userLikeNum);
        // img
    }

    private void setLayout() {

    }

    @FXML
    private void onBookCommentUserLikeNumLabelClciked() {
        System.out.println("bookCommentUserLikeNumLabel(BookItemComment) Clicked");
        // 点赞数+1
    }
}
