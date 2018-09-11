package Controller;

import dao.UserDao;
import global.GlobalConst;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
import model.User;
public class UserInfo implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        // common bottom
        app.registerCommonBottom(commonBottom);
        // tap
        setFocus();
    }
    private void setFocus() {
        commonBottom.bottomPersonTab.requestFocus();
    }

    @FXML
    private AnchorPane infoBasicTop;
    @FXML
    private ImageView infoBasicImg;
    @FXML
    private Label infoBasicName, infoBasicContact, infoBasicIntro;
    @FXML
    private Label infoScoreMiddle;
    @FXML
    private GridPane infoNumBottom;
    @FXML
    private CommonBottom commonBottom;

    private Label borrowNumLabel, lendNumLabel;
    private Label borrowNumTxt, lendNumTxt;

    private String userName, userContact, userIntro, userScore,avatarUrl;
    private String borrowNum, lendNum;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLayout();
        setData();
    }

    private void setData() {
        try{
            getData();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        infoBasicName.setText(userName);
        infoBasicContact.setText(userContact);
        infoBasicIntro.setText(userIntro);
        infoScoreMiddle.setText(userScore);
        borrowNumTxt.setText(borrowNum);
        lendNumTxt.setText(lendNum);

        String imagePath = avatarUrl;
        Image image = new Image(imagePath);
        infoBasicImg.setImage(image);
    }

    private void setLayout() {
        // top

        // middle

        // bottom
        borrowNumLabel = new Label(); lendNumLabel = new Label();
        borrowNumTxt = new Label(); lendNumTxt = new Label();
        borrowNumLabel.setAlignment(Pos.CENTER); borrowNumLabel.setPrefWidth(190); borrowNumLabel.setPrefHeight(40);
        lendNumLabel.setAlignment(Pos.CENTER); lendNumLabel.setPrefWidth(190); lendNumLabel.setPrefHeight(40);
        borrowNumTxt.setAlignment(Pos.CENTER); borrowNumTxt.setPrefWidth(190); borrowNumTxt.setPrefHeight(90);
        lendNumTxt.setAlignment(Pos.CENTER); lendNumTxt.setPrefWidth(190); lendNumTxt.setPrefHeight(90);
        borrowNumLabel.setText("累计借入"); lendNumLabel.setText("累计借出");
        borrowNumTxt.setText("12"); lendNumTxt.setText("8");

        infoNumBottom.add(borrowNumLabel, 0, 0);
        infoNumBottom.add(lendNumLabel, 1, 0);
        infoNumBottom.add(borrowNumTxt, 0, 1);
        infoNumBottom.add(lendNumTxt, 1, 1);

        commonBottom.setLayoutY(588);
    }

    @FXML
    private void onInfoBasicTopClicked() {
        System.out.println("infoBasic AnchorPane Clicked");
        app.showActionChoose();
    }

    private void getData() throws Exception{
        User user = new User();
        try {
            user = UserDao.getInfoByName(Login.username);
        }catch (Exception E){
            System.out.println(E.getMessage());
        }
        avatarUrl = user.getAvatarUrl();
        userName = user.getName();
        userContact = "手机号：" +  user.getPhone();
        userScore = String.valueOf(user.getScore())+"分";

//        userName = GlobalConst.TEST_USER_NAME;
//        userContact = "1051651561";
//        userIntro = GlobalConst.TEST_USER_INTRO;
        userIntro = "QQ：" + user.getQq();
//        userScore = GlobalConst.TEST_USER_SCORE;
        borrowNum = String.valueOf(user.getTotalBorrow());
        lendNum = String.valueOf(user.getTotalLend());
    }
}
