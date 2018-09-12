package Controller;

import dao.BookCommentDao;
import dao.UserDao;
import global.GlobalConst;
import global.PageIndex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Utils.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import model.BookComment;
public class AddBookComment implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {

    }

    private PageIndex fromId;
    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }

    @FXML
    private Button toBackBtn;
    @FXML
    private Label addBookCommentTitle;
    @FXML
    private TextField bookScoreTxt;
    @FXML
    private TextArea bookWordsTxt;
    @FXML
    private Button saveCommentBtn;

    private String bookScore, bookWords;

    private final String COMMON_TITLE = "书籍评论";
    private final String INFORMATION_SUCCESS = "保存评论成功";
    private final String INFORMATION_FULL = "请完整输入信息";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AddBookComment initialize");
        setLayout();
    }

    private void setLayout() {

    }

    @FXML
    void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        app.showBookInfo(fromId);
    }

    @FXML
    void onSaveCommentBtnClicked(ActionEvent event) {
        System.out.println("saveCommentBtn Clicked");
        bookScore = bookScoreTxt.getText();
        bookWords = bookWordsTxt.getText();

        // 判空
        if(checkValid()) { // 上传
            if(saveComment().getStatus()) {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_SUCCESS);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
                onToBackBtnClicked();
            } else {
                Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.OPERATION_FAILED);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
            }
        } else {
            Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_FULL);
            information.setTitle(COMMON_TITLE);
            information.showAndWait();
        }

    }

    private boolean checkValid() {
        try {
            Integer.parseInt(bookScore);
        } catch (Exception E){
            return false;
        }
        if(bookScore.equals("")||bookWords.equals("")){
            return false;
        }
        return true;
    }

    private returnObj saveComment() {
        BookComment comment = new BookComment();
        comment.setBid(BookInfo.nowBid);
        comment.setScore(Integer.parseInt(bookScore));
        comment.setComment(bookWords);
        comment.setUserName(Login.username);
        comment.setLike(0);
        comment.setDislike(0);
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setCreateTime(df.format(day));
        return BookCommentDao.uploadComment(comment);
    }
}
