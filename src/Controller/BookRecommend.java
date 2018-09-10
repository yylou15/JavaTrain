package Controller;

import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
//import sun.security.krb5.internal.PAEncTSEnc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookRecommend implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        // common bottom
        app.registerCommonBottom(commonBottom);
        // tap
        for(BookItemRecommend bookItemRecommend : bookList) {
            app.registerBookItemRecommend(bookItemRecommend);
        }
        setFocus();
    }
    private void setFocus() {
        commonBottom.bottomRecommendTab.requestFocus();
    }

    @FXML
    private Label recommendTitle;
    @FXML
    private GridPane bookGrid;
    @FXML
    private CommonBottom commonBottom;

    private String bookName, bookScore, bookStatusStr;
    private BookStatus bookStatus;
    private String bookImgPath;

    private BookItemRecommend bookItem;
    private List<BookItemRecommend> bookList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookRecommend initialize");
        setLayout();

    }

    private void setLayout() {
        // 图片轮播

        // 书籍Grid
        bookGrid.setVgap(5);
        bookGrid.setHgap(5);
        getBookList();
        for(int i=1; i<=bookList.size(); i++) { // 根据书籍数量而添加
            if(i%2 != 0) {
                bookGrid.add(bookList.get(i-1), 0,(i-1)/2);
            }else
                bookGrid.add(bookList.get(i-1), 1, i/2-1);
        }

        // 底部导航栏
        commonBottom.setLayoutY(588);
        // 这里还没有持有app的对象，必须要在initialize之后才会有app的对象
//        app.invokeTest();
        commonBottom.setApp(app);
    }

    // 封装List 获取书籍信息
    private void getBookList() {
        bookList = new ArrayList<>();
        getData();
        for(int i=0; i<10; i++) {
            bookItem = new BookItemRecommend(bookName, bookScore, bookStatus, bookImgPath, PageIndex.BOOK_RECOMMEND);
//            bookItem.setBelongId(PageIndex.BOOK_RECOMMEND);
            bookList.add(bookItem);
        }
    }

    private void getData() {
        bookName = GlobalConst.TEST_BOOK_NAME;
        bookScore = GlobalConst.TEST_BOOK_SCORE;
        bookStatus = BookStatus.FREE;
    }

}
