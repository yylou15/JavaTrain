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
import java.util.List;
import java.util.ResourceBundle;

public class BookRecordUpload implements Initializable {

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
        for (BookItem bookItem : bookItemList) {
            app.registerBookItem(bookItem);
        }
        setFocusUpload();
    }

    private void setFocusUpload() {
        commonBottom.bottomRecordTab.requestFocus();
        recordTop.topUploadRecord.setDisable(true);
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
        System.out.println("BookRecordUpload initialize");
        try {
            setLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLayout() throws Exception {
        // top

        // content
        getBookList();
        vBox.getChildren().addAll(bookItemList);

        // bottom
        commonBottom.setLayoutY(588);
    }

    private void getBookList() throws Exception {
        bookItemList = new ArrayList<>();
        int userId = UserDao.getInfoByName(Login.username).getUid();
//        List<BookRecord> rs = BookRecordDao.getAllUploadRecords(userId);
//
//        for(BookRecord record : rs) { // 这个不能这么取...因为BookId为0
//            Book book = BookDao.getBookByBid(record.getBid());
//            bookStatus = BookStatusInTable.getBookStatus(record.getStataus()); // 通过Book也是一样的
//            bookItem = new BookItem(book.getBid(),book.getName(), book.getAuthor(), String.valueOf(book.getScore()), bookStatus, record.getCreateTime(), GlobalConst.TEST_BOOK_IMG_PATH, PageIndex.BOOK_RECORD_BORROW, book.getOwnerid());
//
//            bookItemList.add(bookItem);
//        }

        List<Book> rs = BookDao.getBookByOwnerid(userId);
        for (Book book : rs) {
            // 这里如果状态是LENDING 或者 说是 Borrowing就会有时间
            bookStatus = BookStatusInTable.getBookStatus(book.getStatus());
            // 去找它的创建时间 那就得要去找记录
//            if(status == BookStatus.REQUESTED || status == BookStatus.REQUESTING) { // 根据ownid 和 bid 去找书的相关记录？
            // 在申请里显示时间更方便
//            } else if(status == BookStatus.LENDING || status == BookStatus.BORROWING) { // 借阅到期的时间
            // 这里也会有一条记录 // 这条记录是借阅记录 // 它的状态应该是BORROWING
//                String time = BookRecordDao.getRecordCreateTimeByCondition(0, userId, status);
//                bookTime  = time;
            bookItem = new BookItem(book.getBid(), book.getName(), book.getAuthor(), String.valueOf(book.getScore()), bookStatus, bookTime, GlobalConst.TEST_BOOK_IMG_PATH, PageIndex.BOOK_RECORD_UPLOAD, book.getOwnerid());
            bookItemList.add(bookItem);
        }
    }
//        for(int i=0; i<10; i++) {
//            bookItem = new BookItem(bookName, bookAuthor, bookScore, bookStatus, bookTime, bookImgPath, PageIndex.BOOK_RECORD_UPLOAD);
//            bookItem.setBelongId(PageIndex.BOOK_RECORD_UPLOAD);
//            bookItemList.add(bookItem);
//}

}
