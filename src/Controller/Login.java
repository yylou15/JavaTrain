package Controller;

import dao.UserDao;
import global.GlobalConst;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import Utils.*;
public class Login implements Initializable{
    public static String username;
    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
//    private Scene scene;

    // view
    @FXML
    private AnchorPane mainPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button loginBtn, registerBtn;

    private Label labelId, labelPwd;
    private TextField txtId;
    private PasswordField txtPwd;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Login initialize");
        setLayout();
    }

    private void setLayout() {
        // new
//        mainPane = new AnchorPane();
//        gridPane = new GridPane();
        labelId = new Label();
        labelPwd = new Label();
        txtId = new TextField();
        txtPwd = new PasswordField();
//        loginBtn = new Button();
//        registerBtn = new Button();

        // basic init
        mainPane.setPrefWidth(GlobalConst.SIZE_SCENE_WIDTH);
        mainPane.setPrefHeight(GlobalConst.SIZE_SCENE_HEIGHT);
        labelId.setText("账号");
        labelPwd.setText("密码");
        loginBtn.setText("登录");
        registerBtn.setText("立即注册");

        // layout
        gridPane.add(labelId, 0, 0);
        gridPane.add(labelPwd, 0, 1);
        gridPane.add(txtId, 1, 0);
        gridPane.add(txtPwd, 1, 1);

//        scene = new Scene(mainPane, GlobalConst.SIZE_SCENE_WIDTH, GlobalConst.SIZE_SCENE_HEIGHT);
//        app.setScene(scene);
    }

    // action
    @FXML
    private void onLoginBtnClicked(ActionEvent event) throws Exception{
        // three status 1. no account 2. password is wrong
        int res = checkData();
        returnObj rs = UserDao.checkPassword(txtId.getText(),txtPwd.getText());
        System.out.println(rs.getStatus());
        System.out.println(rs.getMsg());
        if(rs.getStatus()){
            username = txtId.getText();
            app.showBookBorrow();
//            app.showBookRecommend();
//            app.showBookBorrow();
//            app.showBookRecordBorrow();
//            app.showBookRecordUpload();
//            app.showBookInfo();
//            app.showBorrowerComment(1);
        }else{
            Alert information = new Alert(Alert.AlertType.ERROR, rs.getMsg());
            information.setTitle("登录失败");
            information.showAndWait();
        }
//        if(res == 1)
//            System.out.println("pwd is wrong");
    }

    @FXML
    private void onRegisterBtnClicked(ActionEvent event) {
        System.out.println("registerBtn Clicked");
//        txtPwd.requestFocus();
        app.showRegister();
    }

    private void clearText() {
        txtId.setText("");
        txtPwd.setText("");
    }

    private int checkData() {
        String account = txtId.getText();
        String pwd = txtPwd.getText();
        if(account.equals("123") && pwd.equals("123"))
            return 1;
        return 2;
    }
}
