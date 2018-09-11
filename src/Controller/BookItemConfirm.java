package Controller;

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

public class BookItemConfirm extends AnchorPane implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    private PageIndex fromId, belongId;
    public void setBelongId(PageIndex belongId) {
        this.belongId = belongId;
    }

    @FXML
    private ImageView confirmUserImgView;
    @FXML
    private Label confirmTimeLabel;
    @FXML
    private Label confirmUserNameLabel;
    @FXML
    private Label confirmBookLabel;

    private String confirmUserName, confirmBookName, confirmTime;
    private String confirmUserImgPath;
    private Image confirmUserImg;

    public BookItemConfirm() {
        System.out.println("BookItemConfirm Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_confirm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookItemConfirm(String confirmUserName, String confirmBookName, String confirmTime, String confirmUserImgPath, PageIndex belongId) {
        this.confirmUserName = confirmUserName;
        this.confirmBookName = confirmBookName;
        this.confirmTime = confirmTime;
        this.confirmUserImgPath = confirmUserImgPath;
        this.belongId = belongId;
        System.out.println("BookItemConfirm Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_confirm.fxml"));
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
        setData();
        setLayout();
    }

    private void setData() {
        confirmUserNameLabel.setText(confirmUserName);
        confirmBookLabel.setText(confirmBookName);
        confirmTimeLabel.setText(confirmTime);
        // img
    }

    private void setLayout() {

    }

    @FXML
    private void onItemClicked() {
        System.out.println("BookItemConfirm Clicked");
        app.showLenderComment(belongId);
    }
}
