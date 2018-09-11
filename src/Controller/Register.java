package Controller;
/**
 * Sample Skeleton for 'register.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import Utils.*;
import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import model.User;
public class Register implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txt_contact"
    private TextField txt_contact; // Value injected by FXMLLoader

    @FXML // fx:id="txt_qq"
    private TextField txt_qq; // Value injected by FXMLLoader

    @FXML // fx:id="pwdTxt_pwd"
    private PasswordField pwdTxt_pwd; // Value injected by FXMLLoader

    @FXML // fx:id="txt_id"
    private TextField txt_id; // Value injected by FXMLLoader

    @FXML // fx:id="pwdTxt_confirm"
    private PasswordField pwdTxt_confirm; // Value injected by FXMLLoader

    @FXML // fx:id="btn_login"
    private Button btn_registerAndLogin; // Value injected by FXMLLoader


    @FXML
    void onRegisterAndLoginBtnClicked(ActionEvent event) throws Exception{
//        app.showBookBorrow();
        if(pwdTxt_pwd.getText().equals(pwdTxt_confirm.getText())){
            User user = new User();
            user.setName(txt_id.getText());
            user.setQq(txt_qq.getText());
            user.setPhone(txt_contact.getText());

            returnObj res = UserDao.submitInfo(user);
            if(res.getStatus()){
//                user.setAvatarUrl(Util.getAvatarUrl(user.getQq()));
                UserDao.submitPwd(user,pwdTxt_pwd.getText());
                Alert information = new Alert(Alert.AlertType.INFORMATION, "注册成功");
                information.setTitle("注册成功");
                information.showAndWait();
                app.showLogin();
            }else{
                Alert information = new Alert(Alert.AlertType.ERROR, res.getMsg());
                information.setTitle("Error");
                information.showAndWait();
            }
        }else {
            Alert information = new Alert(Alert.AlertType.ERROR, "前后密码输入不一致！");
            information.setTitle(this.pwdTxt_pwd.toString());
            information.showAndWait();
        }
        // 系列相关操作
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Register initialize");
    }
}
