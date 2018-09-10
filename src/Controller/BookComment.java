package Controller;

import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookComment implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        for(BookItemComment bookItemComment : bookCommentList) {
            app.registerBookItemComment(bookItemComment);
        }
        setFocus();
    }
    private PageIndex fromId;
    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }

    private void setFocus() {

    }

    @FXML
    private VBox vBox;
    @FXML
    private Button toBackBtn;

    private BookItemComment bookItemComment;
    private List<BookItemComment> bookCommentList;

    private String userName, userGivenScore, userCreateTime, userComment;
    private String userLikeNum;
    private String userImgPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookComment initialize");
        setLayout();
    }

    private void setLayout() {
        getBookCommentList();
        vBox.getChildren().addAll(bookCommentList);
    }

    @FXML
    private void onToBackBtnClicked() {
        // 回到书籍详细页面
        app.showBookInfo(fromId);
    }

    private void getBookCommentList() {
        bookCommentList = new ArrayList<>();
        getData();
        for(int i=0; i<10; i++) {
            bookItemComment = new BookItemComment(userName, userGivenScore, userCreateTime, userComment, userLikeNum, userImgPath, fromId);
            bookItemComment.setBelongId(fromId);
            bookCommentList.add(bookItemComment);
        }
    }

    private void getData() {
        userName = GlobalConst.TEST_USER_NAME;
        userGivenScore = GlobalConst.TEST_BOOK_SCORE;
        userCreateTime  = GlobalConst.TEST_USER_UPLOAD_TIME;
        userComment = GlobalConst.TEST_USER_COMMENT;
        userLikeNum = "57";
    }
}
