package Controller;

import Utils.BookStatusText;
import dao.BookDao;
import dao.UserDao;
import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class BookItem extends AnchorPane implements Initializable {
    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    private PageIndex fromId, belongId;
    public void setBelongId(PageIndex belongId) {
        this.belongId = belongId;
    }

    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }

    @FXML
    private VBox vBox;
    @FXML
    private Label bookItemScoreLabel;
    @FXML
    private Label bookItemStatusLabel;
    @FXML
    private Label bookItemAuthorLabel;
    @FXML
    private Label bookItemNameLabel, bookItemHolderNameLabel, bookItemReturnTimeLabel, bookItemUploadLeftLabel;
    @FXML
    private ImageView bookItemImgView;
    @FXML
    private Label bookItemTimeLabel;
    @FXML
    private Button confirmBtn; // 用于隐藏

    private int bid, ownerid;
    private String bookName, bookAuthor, bookScore, bookStatusStr, bookTime, owner;
    private BookStatus bookStatus;
    // Time 只有在BORROWING与LENDING两种状态显示 即BORROWED下显示 在非记录下 统一显示最短的归还时间 确认归还在belongId为BookBorrow下为不可见
    private String bookImgPath;
    private Image bookImg;

    public BookItem() {
        System.out.println("BookItem Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BookItem(int bid, String bookName, String bookAuthor, String bookScore, BookStatus bookStatus, String bookTime, String bookImgPath, PageIndex belongId, int ownerid) {
        this.bid = bid;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookScore = bookScore;
        this.bookStatus = bookStatus;
        this.bookTime = bookTime;
        this.bookImgPath = bookImgPath;
        this.belongId = belongId;
        this.ownerid = ownerid;
        System.out.println("BookItem Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/book_item.fxml"));
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
        bookName = "《" + bookName + "》";
//        switch (bookStatus){
//            case REQUESTING:bookName = bookName+"(待确认)";break;
//            case OVER:bookName = bookName+"(已归还)";break;
//            case DENIED:bookName = bookName+"(被拒绝)";break;
//            case CONFIRMING:bookName = bookName+"(待确认归还)";break;
//            default:break;
//        }
        try {
            owner = UserDao.getInfoByUid(ownerid).getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = "res/" + String.valueOf(bid) + ".jpg";
        System.out.println(s);
        bookImg = new Image(s);
        bookItemImgView.setImage(bookImg);
        bookItemNameLabel.setText(bookName);
        bookItemAuthorLabel.setText("作者：" + bookAuthor);
        bookItemScoreLabel.setText("评分：" + bookScore + " 分");
        bookItemHolderNameLabel.setText("持有者： " + owner);
        bookStatusStr = BookStatusText.getBookStatusTxt(bookStatus);
        bookItemStatusLabel.setText("状态：" + bookStatusStr);
        bookItemTimeLabel.setText("创建时间：" + bookTime);
        bookItemReturnTimeLabel.setText("借阅时间：30天");

    }

    private void setLayout() {
        // BookBorrow
        if(belongId == PageIndex.BOOK_BORROW) {
            bookItemTimeLabel.setVisible(false);
            bookItemTimeLabel.setManaged(false);
        }

        // BookRecordBorrow
        if(belongId == PageIndex.BOOK_RECORD_BORROW) {
            bookItemScoreLabel.setVisible(false);
            bookItemScoreLabel.setManaged(false);
            if(bookStatus == BookStatus.BORROWING) {
                bookItemReturnTimeLabel.setVisible(true);
                bookItemReturnTimeLabel.setManaged(true);
                confirmBtn.setVisible(true);
                confirmBtn.setManaged(true);
//            bookItemTimeLabel.setText(bookTime);
            }
        }

        // BookRecordUpload
        if(belongId == PageIndex.BOOK_RECORD_UPLOAD) {
            bookItemHolderNameLabel.setVisible(false);
            bookItemHolderNameLabel.setManaged(false);
            bookItemTimeLabel.setVisible(false);
            bookItemTimeLabel.setManaged(false);
            bookItemUploadLeftLabel.setVisible(true);
            bookItemUploadLeftLabel.setManaged(true);
            if(bookStatus == BookStatus.REQUESTING) {
                bookStatusStr = BookStatusText.getBookStatusTxt(BookStatus.REQUESTED);
                bookItemStatusLabel.setText("状态：" + bookStatusStr);
            }
            if(bookStatus == BookStatus.BORROWING) {
                bookItemReturnTimeLabel.setVisible(true);
                bookItemReturnTimeLabel.setManaged(true);
//                confirmBtn.setVisible(true);
//                confirmBtn.setManaged(true);
            }
        }

//        if(!(bookStatus==BookStatus.BORROWED || bookStatus==BookStatus.BORROWING || bookStatus==BookStatus.LENDING)) {
//            bookItemTimeLabel.setVisible(false);
//            bookItemTimeLabel.setManaged(false);
//        }
    }

    @FXML
    private void onItemClicked() {
        BookInfo.nowBid = bid;
        System.out.println("BookItem Clicked" + bid);
        // book info
        app.showBookInfo(belongId);
    }

    @FXML
    private void onConfirmBtnClicked() {
        BookInfo.nowBid = bid;
        System.out.println("confirmBtn Clicked");
        System.out.println(belongId);
        switch (belongId) {
            case BOOK_RECORD_BORROW:
                app.showBorrowerComment(belongId);
                break;
            case BOOK_RECORD_UPLOAD:
                app.showLenderComment(belongId);
                break;
        }
    }
}
