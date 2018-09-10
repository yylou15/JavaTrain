package Controller;

import global.GlobalConst;
import global.PageIndex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BookUpload implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {}
    private PageIndex fromId;
    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }

    @FXML
    private Button uploadBtn;
    @FXML
    private TextField bookNameTxt;
    @FXML
    private TextField bookAuthorTxt;
    @FXML
    private TextField bookPressTxt;
    @FXML
    private TextArea bookWordsTxt;
    @FXML
    private ImageView bookImgView1;
    @FXML
    private ImageView bookImgView2;
    @FXML
    private ImageView bookImgView3;

    private String bookName, bookAuthor, bookPress, bookWords;
    private Image img1, img2, img3;
    private String imgPath1, imgPath2, imgPath3;

    private final String COMMON_TITLE = "上传书籍";
    private final String INFORMATION_FULL = "请先完善书籍信息！";
    private final String INFORMATION_SUCCESS = "上传成功";
    private final String INFORMATION_FAILED = "抱歉，上传失败！";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLayout();
    }

    private void setLayout() {

    }

    @FXML
    private void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        if(fromId == PageIndex.ACTION_CHOOSE)
            app.showActionChoose();
        else
            app.showBookInfo(fromId);
    }

    @FXML
    private void onUploadBtnClicked(ActionEvent event) {
        System.out.println("uploadBtn Clicked");
        bookName = bookNameTxt.getText();
        bookAuthor = bookAuthorTxt.getText();
        bookPress = bookPressTxt.getText();
        bookWords = bookWordsTxt.getText();

        // 判空
        if(checkValid()) { // 上传
            if(uploadBook()) {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_SUCCESS);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
                onToBackBtnClicked();
            } else {
                Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.OPERATION_FAILED);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
            }
        }

        else {
            Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.INFORMATION_NOT_FULL);
            information.setTitle(COMMON_TITLE);
            information.showAndWait();
        }
    }

    private boolean checkValid() {
        return true;
    }

    private boolean uploadBook() {
        System.out.println("upload book...");
        return true;
    }
}
