package Controller;

import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserCollection implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        for(BookItemCollection bookItemCollection : bookItemCollectionList) {
            app.registerBookItemCollection(bookItemCollection);
        }
    }

    @FXML
    private VBox vBox;
    @FXML
    private Button toBackBtn;

    private String bookName, bookAuthor, bookScore, bookTime;
    private BookStatus bookStatus;
    private String bookImgPath;
//    private Image bookImg;

    private BookItemCollection bookItemCollection;
    private List<BookItemCollection> bookItemCollectionList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLayout();
    }

    private void setLayout() {
        // vBox bookList
        getBookList();
        vBox.getChildren().addAll(bookItemCollectionList);
    }

    @FXML
    private void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        app.showActionChoose();
    }

    private void getBookList() { // 获取书籍数据
        bookItemCollectionList = new ArrayList<>();
        getData();
        for(int i=0; i<10; i++) {
            bookItemCollection = new BookItemCollection(bookName, bookAuthor, bookScore, bookStatus, bookTime, bookImgPath, PageIndex.USER_COLLECTION);
//            bookItemCollection.setBelongId(PageIndex.USER_COLLECTION);
            bookItemCollectionList.add(bookItemCollection);
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
