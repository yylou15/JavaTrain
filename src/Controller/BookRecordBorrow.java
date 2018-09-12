package Controller;

import Utils.BookStatusInTable;
import dao.BookDao;
import dao.BookRecordDao;
import dao.UserDao;
import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.Book;
import model.BookRecord;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static dao.BookDao.getAllBook;

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
        try {
            setLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLayout() throws Exception{
        // top

        // content
        getBookList();
        vBox.getChildren().addAll(bookItemList);

        // bottom
        commonBottom.setLayoutY(588);
    }

    private void getBookList() throws Exception{
        bookItemList = new ArrayList<>();

        // 1. uid
        int userId = UserDao.getInfoByName(Login.username).getUid();
        List<BookRecord> rs = BookRecordDao.getRecordByUid(userId); // 所有的借书记录

        // 3. 再过滤Status 这里在前端操作没什么问题了
        Iterator<BookRecord> iterator = rs.iterator();
        while(iterator.hasNext()) {
            BookRecord bookRecord = iterator.next();
            BookStatus status = BookStatusInTable.getBookStatus(bookRecord.getStataus());
            if(status==BookStatus.REQUESTING || status==BookStatus.BORROWING || status==BookStatus.DENIED || status==BookStatus.OVER || status==BookStatus.CONFIRMING)
                continue;
            else
                iterator.remove();
        }

        for(BookRecord record : rs) {
            // 4. 再根据bid获取书籍信息
            Book book = BookDao.getBookByBid(record.getBid());
            bookStatus = BookStatusInTable.getBookStatus(record.getStataus()); // 通过Book也是一样的
            bookItem = new BookItem(book.getBid(),book.getName(), book.getAuthor(), String.valueOf(book.getScore()), bookStatus, record.getCreateTime().split(" ")[0], GlobalConst.TEST_BOOK_IMG_PATH, PageIndex.BOOK_RECORD_BORROW, book.getOwnerid());
            bookItemList.add(bookItem);
        }
    }
}
