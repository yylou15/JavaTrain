package Controller;

import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookBorrow implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        // common bottom
        app.registerCommonBottom(commonBottom);
        // tap
        for(int i=0; i<bookItemList.size(); i++) {
            app.registerBookItem(bookItemList.get(i));
        }
        setFocus();
    }
    private void setFocus() {
        commonBottom.bottomBorrowTab.requestFocus();
    }

    @FXML
    private Label borrowTitle;
    @FXML
    private VBox vBox;
    @FXML
    private CommonBottom commonBottom;

    private String bookName, bookAuthor, bookScore, bookTime;
    private BookStatus bookStatus;
    private String bookImgPath;

    private BookItem bookItem;
    private List<BookItem> bookItemList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookBorrow initialize");
        setLayout();
    }

    private void setLayout() {
        borrowTitle.setTextAlignment(TextAlignment.CENTER);

        // vBox bookList
        getBookList();
        vBox.getChildren().addAll(bookItemList);

        // commonBottom
        commonBottom.setLayoutY(588);
    }

    private void getBookList() { // 获取书籍数据
        bookItemList = new ArrayList<>();
        getData();
        for(int i=0; i<10; i++) { // 直接在List里操作了
            bookItem = new BookItem(bookName, bookAuthor, bookScore, bookStatus, bookTime, bookImgPath, PageIndex.BOOK_BORROW);
            bookItemList.add(bookItem);
        }
    }

    private void getData() {
        bookName = GlobalConst.TEST_BOOK_NAME;
        bookAuthor = GlobalConst.TEST_BOOK_AUTHOR;
        bookScore = GlobalConst.TEST_BOOK_SCORE;
        bookStatus = BookStatus.FREE;
        bookTime = GlobalConst.TEST_BOOK_TIME;
        // 访问数据库 拿到Model的List
    }
}
