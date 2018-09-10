package Controller;

import global.GlobalConst;
import global.PageIndex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

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
            if(saveComment()) {
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
        return true;
    }

    private boolean saveComment() {
        System.out.println("save comment...");
        return true;
    }
}
