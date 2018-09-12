package Controller;

import Utils.BookStatusText;
import global.BookStatus;
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

public class BookItemBorrow extends AnchorPane implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    private PageIndex belongId, rootId;

    public void setBelongId(PageIndex belongId) {
        this.belongId = belongId;
    }

    public void setRootId(PageIndex rootId) {
        this.rootId = rootId;
    }

    @FXML
    private Label bookBorrowUserScoreLabel;
    @FXML
    private Label bookBorrowIsOk;
    @FXML
    private Label bookBorrowUserTimeLabel;
    @FXML
    private ImageView bookBorrowUserImgView;
    @FXML
    private Label bookBorrowUserNameLabel;
    @FXML
    private Label bookBorrowUserComLabel;

    private String onwerName, onwerScore, onwerTime, onwerLeft;
    // 这里有两种状态... 1. 书可以借 2. 书被借走了，显示归还的时间
    private BookStatus bookStatus; // 可借 or 归还 + 时间;
    private String bookStatusStr;
    private String onwerImgPath;
    private Image onwerImg;


    public BookItemBorrow() {
        System.out.println("BookItemBorrow Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_borrow.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookItemBorrow(String onwerName, String onwerScore, String onwerTime, String onwerLeft, BookStatus bookStatus, String onwerImgPath, PageIndex belongId, PageIndex rootId) {
        this.onwerName = onwerName;
        this.onwerScore = onwerScore;
        this.onwerTime = onwerTime;
        this.onwerLeft = onwerLeft;
        this.bookStatus = bookStatus;
        this.onwerImgPath = onwerImgPath;
        this.belongId = belongId;
        this.rootId = rootId;
        System.out.println("BookItemBorrow Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_borrow.fxml"));
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
        onwerName = GlobalConst.TEST_USER_NAME;
        onwerScore = GlobalConst.TEST_USER_SCORE;
        onwerTime = GlobalConst.TEST_USER_UPLOAD_TIME;
        onwerLeft = GlobalConst.TEST_USER_LEFT;
        bookStatus = BookStatus.FREE;
        bookStatusStr = BookStatusText.getBookStatusTxt(bookStatus);
    }

    private void setData() {
        bookBorrowUserNameLabel.setText(onwerName);
        bookBorrowUserScoreLabel.setText(onwerScore + "分");
        bookBorrowUserTimeLabel.setText(onwerTime);
        bookBorrowUserComLabel.setText(onwerLeft);
        bookStatusStr = BookStatusText.getBookStatusTxt(bookStatus);
        bookBorrowIsOk.setText(bookStatusStr);
        onwerImg = new Image(onwerImgPath);
        bookBorrowUserImgView.setImage(onwerImg);
    }

    private void setLayout() {

    }

    @FXML
    private void onItemClicked() {
        System.out.println("BookItemBorrow Clicked");
        // borrow request
        app.showBookBorrowRequest(rootId, belongId);
    }
}
