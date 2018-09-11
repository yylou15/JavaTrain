package Controller;

import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookBorrowList implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        for(BookItemBorrow bookItemBorrow : bookItemBorrowList) {
            app.registerBookItemBorrow(bookItemBorrow);
            bookItemBorrow.setBelongId(fromId);
        }
    }
    private PageIndex fromId;
    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }

    private String onwerName, onwerScore, onwerTime, onwerLeft;
    private BookStatus bookStatus;
    private String onwerImgPath;

    @FXML
    private Button toBackBtn;
    @FXML
    private VBox vBox;

    private BookItemBorrow bookItemBorrow;
    private List<BookItemBorrow> bookItemBorrowList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookBorrowList initialize");
        setLayout();
    }

    private void setLayout() {
        getBookItemBorrowList();
        vBox.getChildren().addAll(bookItemBorrowList);
    }

    @FXML
    private void onToBackBtnClicked() {
        // 回到书籍详细页面
        app.showBookInfo(fromId);
    }

    private void getBookItemBorrowList() {
        bookItemBorrowList = new ArrayList<>();
        getData();
        for(int i=0; i<10; i++) {
            bookItemBorrow = new BookItemBorrow(onwerName, onwerScore, onwerTime, onwerLeft, bookStatus, onwerImgPath, fromId, PageIndex.BOOK_BORROW_LIST) ;
//            bookItemBorrow.setRootId(PageIndex.BOOK_BORROW_LIST);
            bookItemBorrowList.add(bookItemBorrow);
        }
    }

    private void getData() {
        onwerName = GlobalConst.TEST_USER_NAME;
        onwerScore = GlobalConst.TEST_USER_SCORE;
        onwerTime = GlobalConst.TEST_USER_UPLOAD_TIME;
        onwerLeft = GlobalConst.TEST_REQUESTER_LEFT;
        bookStatus = BookStatus.FREE;
    }
}
