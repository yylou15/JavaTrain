package Controller;

import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

// hello world
public class ActionChoose implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() { }

    @FXML
    private Label actionChooseTitle;
    @FXML
    private Button toBackBtn;
    @FXML
    private GridPane actionListPane;

    @FXML
    private Button modifyInfoBtn,
            modifyPwdBtn,
            lookCollectionBtn,
            uploadBookBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ActionChoose initialize");
        setLayout();
    }

    private void setLayout() {
        // GridPane
        modifyInfoBtn.setText("修改个人信息"); modifyInfoBtn.setAlignment(Pos.CENTER_LEFT);
        modifyPwdBtn.setText("修改密码");modifyPwdBtn.setAlignment(Pos.CENTER_LEFT);
        lookCollectionBtn.setText("查看愿望单"); lookCollectionBtn.setAlignment(Pos.CENTER_LEFT);
        uploadBookBtn.setText("上传书籍"); uploadBookBtn.setAlignment(Pos.CENTER_LEFT);

        modifyInfoBtn.setPrefWidth(GlobalConst.SIZE_SCENE_WIDTH);
        modifyPwdBtn.setPrefWidth(GlobalConst.SIZE_SCENE_WIDTH);
        lookCollectionBtn.setPrefWidth(GlobalConst.SIZE_SCENE_WIDTH);
        uploadBookBtn.setPrefWidth(GlobalConst.SIZE_SCENE_WIDTH);

        actionListPane.setVgap(12);
        actionListPane.add(modifyInfoBtn, 0, 0);
        actionListPane.add(modifyPwdBtn, 0, 1);
        actionListPane.add(lookCollectionBtn, 0, 2);
        actionListPane.add(uploadBookBtn, 0, 3);

    }

    @FXML
    private void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        app.showUserInfo();
    }

    @FXML
    private void onModifyInfoBtnClicked() {
        System.out.println("modifyInfoBtn Clicked");
        app.showUserInfoModify();
    }

    @FXML
    private void onModifyPwdBtnClicked() {
        System.out.println("modifyPwdBtn Clicked");
        app.showUserPwdModify();
    }

    @FXML
    private void onLookCollectionBtnClicked() {
        System.out.println("lookCollectionBtn Clicked");
        app.showUserCollection();
    }

    @FXML
    private void onUploadBookBtnClicked() {
        System.out.println("uploadBookBtn Clicked");
        app.showBookUpload(PageIndex.ACTION_CHOOSE);
    }
}
