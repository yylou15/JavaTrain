package Controller;

import Utils.BookStatusText;
import dao.BookDao;
import dao.UserDao;
import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// 发现它和Item基本上是一样的，只是Button的行为不同而已
public class BookItemCollection extends AnchorPane implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    private PageIndex belongId;
    public void setBelongId(PageIndex belongId) {
        this.belongId = belongId;
    }

    @FXML
    private VBox vBox;
    @FXML
    private Label bookItemScoreLabel;
    @FXML
    private Label bookItemStatusLabel;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label bookItemAuthorLabel;
    @FXML
    private Label bookItemNameLabel;
    @FXML
    private ImageView bookItemImgView;
    @FXML
    private Label bookItemTimeLabel;

    private int bid;
    private String bookName, bookAuthor, bookScore, bookStatusStr, bookTime,ownerid;
    private BookStatus bookStatus;
    // Time 只有在BORROWING与LENDING两种状态显示 即BORROWED下显示 在非记录下 统一显示最短的归还时间 确认归还在belongId为BookBorrow下为不可见
    private String bookImgPath;
    private Image bookImg;

    private final String COMMON_TITLE = "我的收藏";

    public BookItemCollection() {
        System.out.println("BookItemCollection Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_collection.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookItemCollection(int bid,String bookName, String bookAuthor, String bookScore, BookStatus bookStatus, String bookTime, String bookImgPath, PageIndex belongId) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookScore = bookScore;
        this.bookStatus = bookStatus;
        this.bookTime = bookTime;
        this.bookImgPath = bookImgPath;
        this.belongId = belongId;
        this.bid = bid;
        System.out.println("BookItemCollection Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item_collection.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        }catch (IOException e) {
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
        belongId = PageIndex.BOOK_BORROW;
        bookName = GlobalConst.TEST_BOOK_NAME;
        bookAuthor = GlobalConst.TEST_BOOK_AUTHOR;
        bookScore = GlobalConst.TEST_BOOK_SCORE;
        bookStatus = BookStatus.FREE;
        bookTime = GlobalConst.TEST_BOOK_TIME;
        bookStatusStr = BookStatusText.getBookStatusTxt(bookStatus);
    }

    private void setData() {
//        bookItemNameLabel.setText(bookName);
//        bookItemAuthorLabel.setText(bookAuthor);
//        bookItemScoreLabel.setText(bookScore);
//        bookStatusStr = BookStatusText.getBookStatusTxt(bookStatus);
//        bookItemStatusLabel.setText(bookStatusStr);
//        bookItemTimeLabel.setText(bookTime);
        bookItemNameLabel.setText("《" + bookName + "》");
        bookItemAuthorLabel.setText("作者：" + bookAuthor);
        bookStatusStr = BookStatusText.getBookStatusTxt(bookStatus);
        bookItemStatusLabel.setText(bookStatusStr);
        bookItemTimeLabel.setText(bookTime);
        String s = "res/" + String.valueOf(bid) + ".jpg";
        System.out.println(s);
        bookImg = new Image(s);

        bookItemImgView.setImage(bookImg);
    }

    private void setLayout() {
        if(!(bookStatus==BookStatus.BORROWED || bookStatus==BookStatus.BORROWING || bookStatus==BookStatus.LENDING)) {
            bookItemTimeLabel.setVisible(false);
            bookItemTimeLabel.setManaged(false);
        }
    }

    @FXML
    private void onItemClicked() {
        System.out.println("BookItemCollection Clicked");
        // book info
        BookInfo.nowBid = bid;
        app.showBookInfo(belongId);
    }

    @FXML
    private void onCancelBtnClicked() {
        System.out.println("cancelBtn Clicked");
        if(checkValid()) { // 上传
            if(cancelCollect()) {
//                Alert information = new Alert(Alert.AlertType.INFORMATION, "取消收藏成功");
//                information.setTitle(COMMON_TITLE);
//                information.showAndWait();
//                onToBackBtnClicked();
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

    private boolean cancelCollect() {
        System.out.println("cancel collect...");
        try {
            BookDao.canclecollect(UserDao.getInfoByName(Login.username).getUid() ,BookInfo.nowBid);
        }catch (Exception E){
            return false;
        }
        return true;
    }
}
