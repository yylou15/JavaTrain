package Controller;

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

public class BookItemRequest extends AnchorPane implements Initializable {

    private  int bid;
    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    private PageIndex belongId;
    public void setBelongId(PageIndex belongId) {
        this.belongId = belongId;
    }

    @FXML
    private Label bookRequesterNameLabel, bookNameLabel, bookRequesterTimeLabel, bookRequesterLeftLabel;
    @FXML
    private ImageView bookRequesterImgView;

    private int rid;
    private String requesterName, bookName, requesterTime, requesterLeft;
    private String requesterImgPath;
    private Image requesterImg;


    public BookItemRequest() {
        System.out.println("BookItemRequest Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_request.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookItemRequest(int rid, int bid,String requesterName, String bookName, String requesterTime, String requesterLeft, String requesterImgPath, PageIndex belongId) {
        this.rid = rid;
        this.requesterName = requesterName;
        this.bookName = bookName;
        this.requesterTime = requesterTime;
        this.requesterLeft = requesterLeft;
        this.requesterImgPath = requesterImgPath;
        this.belongId = belongId;
        this.bid = bid;
        System.out.println("BookItemRequest Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_request.fxml"));
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
        requesterName = GlobalConst.TEST_USER_NAME;
        bookName = GlobalConst.TEST_BOOK_NAME;
        requesterTime = GlobalConst.TEST_USER_UPLOAD_TIME;
        requesterLeft = GlobalConst.TEST_REQUESTER_LEFT;
    }

    private void setData() {
        bookRequesterNameLabel.setText(requesterName);
        bookNameLabel.setText(bookName);
        bookRequesterTimeLabel.setText(requesterTime);
        bookRequesterLeftLabel.setText(requesterLeft);

        requesterImg = new Image(requesterImgPath);
        bookRequesterImgView.setImage(requesterImg);
    }

    private void setLayout() {

    }

    @FXML
    private void onItemClicked() {
        BookInfo.nowBid = bid;
        BookBorrowConfirm.nowRid = rid;
        System.out.println("BookItemRequest Clicked");
        app.showBookBorrowConfirm();
    }
}
