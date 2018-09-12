package Controller;

import Utils.BookStatusText;
import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookItemRecommend extends AnchorPane implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    private PageIndex belongId;
    public void setBelongId(PageIndex belongId) {
        this.belongId = belongId;
    }

    @FXML
    private Label bookItemNameLabel;
    @FXML
    private Label bookItemScoreLabel;
    @FXML
    private Label bookItemStatusLabel;
    @FXML
    private ImageView bookItemImgView;

    private int bid;
    private String bookName, bookScore, bookStatusStr;
    private BookStatus bookStatus;
    private String bookImgPath;
    private Image bookImg;

    public BookItemRecommend() {
        System.out.println("BookItemRecommend Construct invoke");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_rec.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public BookItemRecommend(int bid, String bookName, String bookScore, BookStatus bookStatus, String bookImgPath, PageIndex belongId) {
        this.bid = bid;
        this.bookName = bookName;
        this.bookScore = bookScore;
        this.bookStatus = bookStatus;
        this.bookImgPath = bookImgPath;
        this.belongId = belongId;
        System.out.println("BookItemRecommend Construct invoke");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_rec.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
        setLayout();
    }

    private void setData() {
        bookItemNameLabel.setText("《" + bookName + "》");
        bookItemScoreLabel.setText("评分：" + bookScore);
        bookStatusStr = BookStatusText.getBookStatusTxt(bookStatus);
        bookItemStatusLabel.setText(bookStatusStr);
        String s = "res/" + String.valueOf(bid) + ".jpg";
        System.out.println(s);
        bookImg = new Image(s);
//        bookImg = new Image(GlobalConst.TEST_BOOK_IMG_PATH);
        bookItemImgView.setImage(bookImg);
    }

    private void setLayout() {

    }

    @FXML
    private void onItemClicked() {
        System.out.println("BookItemRecommend Clicked");
        BookInfo.nowBid = bid;
        System.out.println(belongId);
        app.showBookInfo(belongId);
    }
}
