package Controller;

import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookRecordBorrow implements Initializable {
    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        // record top
        app.registerRecordTop(recordTop);
        // common bottom
        app.registerCommonBottom(commonBottom);
        // tap
        for(BookItem bookItem : bookItemList) {
            app.registerBookItem(bookItem);
        }
        setFocus();
    }
    private void setFocus() {
        commonBottom.bottomRecordTab.requestFocus();
        recordTop.topBorrowRecord.setDisable(true);
    }

    @FXML
    private RecordTop recordTop;
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
        System.out.println("BookRecordBorrow initialize");
        setLayout();
    }

    private void setLayout() {
        // top

        // content
        getBookList();
        vBox.getChildren().addAll(bookItemList);

        // bottom
        commonBottom.setLayoutY(588);
    }

    private void getBookList() {
        bookItemList = new ArrayList<>();
        getData();
        for(int i=0; i<10; i++) {
//            bookItem = new BookItem(bookName, bookAuthor, bookScore, bookStatus, bookTime, bookImgPath, PageIndex.BOOK_RECORD_BORROW);
////            bookItem.setBelongId(PageIndex.BOOK_RECORD_BORROW);
//            bookItemList.add(bookItem);
        }
    }

    private void getData() {
        bookName = GlobalConst.TEST_BOOK_NAME;
        bookAuthor = GlobalConst.TEST_BOOK_AUTHOR;
        bookScore = GlobalConst.TEST_BOOK_SCORE;
        bookStatus = BookStatus.FREE;
        bookTime = GlobalConst.TEST_BOOK_TIME;
    }
}
