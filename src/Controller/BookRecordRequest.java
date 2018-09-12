package Controller;

import dao.BookDao;
import dao.BookRecordDao;
import dao.UserDao;
import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Book;
import model.BookRecord;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookRecordRequest implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        app.registerRecordTop(recordTop);
        app.registerCommonBottom(commonBottom);
        for(BookItemRequest bookItemRequest : bookItemRequestList) {
            app.registerBookItemRequest(bookItemRequest);
        }
        for(BookItemConfirm bookItemConfirm : bookItemConfirmList) {
            app.registerBookItemConfirm(bookItemConfirm);
        }
        setFocus();
    }
    private void setFocus() {
        commonBottom.bottomRecordTab.requestFocus();
        recordTop.topRequestRecord.setDisable(true);
    }

    @FXML
    private RecordTop recordTop;
    @FXML
    private VBox vBox;
    @FXML
    private CommonBottom commonBottom;
    @FXML
    private Label borrowRequestTitle, returnConfirmTitle;

    private String requesterName, bookName, requesterTime, requesterLeft;
    private String requesterImgPath;
//    private Image requesterImg;

    private String confirmUserName, confirmBookName, confirmTime;
    private String confirmUserImgPath;

    private BookItemRequest bookItemRequest;
    private List<BookItemRequest> bookItemRequestList;
    private BookItemConfirm bookItemConfirm;
    private List<BookItemConfirm> bookItemConfirmList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookRecordRequest initialize");
        try {
            setLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLayout() throws Exception{
//        getData();
        getBookItemRequestList();
        getBookItemConfirmList();

        vBox.getChildren().add(borrowRequestTitle);
        vBox.getChildren().addAll(bookItemRequestList);
        vBox.getChildren().add(returnConfirmTitle);
        vBox.getChildren().addAll(bookItemConfirmList);

        commonBottom.setLayoutY(588);
    }

    private void getBookItemRequestList() throws Exception{
        bookItemRequestList = new ArrayList<>();
        int userId = UserDao.getInfoByName(Login.username).getUid();
        List<BookRecord> rs = BookRecordDao.getOwnerRecordByStatus(userId, BookStatus.REQUESTING);
        // 所有的相关记录只有REQUESTING是被采用的
        // 用户头像 名字 书名 时间 留言
        for(BookRecord record : rs) {
            User user = UserDao.getInfoByUid(record.getUid());
            Book book = BookDao.getBookByBid(record.getBid());
            bookItemRequest = new BookItemRequest(record.getRid(), record.getBid(),user.getName(), book.getName(), record.getCreateTime(), record.getLeft(), user.getAvatarUrl(), PageIndex.BOOK_RECORD_REQUEST);
            bookItemRequestList.add(bookItemRequest);
        }
//        for(int i=0; i<10; i++) {
//            bookItemRequest = new BookItemRequest(requesterName, bookName, requesterTime, requesterLeft,requesterImgPath, PageIndex.BOOK_RECORD_REQUEST);
////            bookItemRequest.setBelongId(PageIndex.BOOK_RECORD_REQUEST);
//            bookItemRequestList.add(bookItemRequest);
//        }
    }

    private void getBookItemConfirmList() throws Exception{
        bookItemConfirmList = new ArrayList<>();
        int userId = UserDao.getInfoByName(Login.username).getUid();
        List<BookRecord> rs = BookRecordDao.getOwnerRecordByStatus(userId, BookStatus.CONFIRMING);
        // 所有的相关记录只有CONFIRMING是被采用的
        // 用户头像 名字 书名 时间 留言
        for(BookRecord record : rs) {
            User user = UserDao.getInfoByUid(record.getUid());
            Book book = BookDao.getBookByBid(record.getBid());
            bookItemConfirm = new BookItemConfirm(record.getRid(), record.getBid(), user.getName(), book.getName(), record.getCreateTime().split(" ")[0], user.getAvatarUrl(), PageIndex.BOOK_RECORD_REQUEST);
            bookItemConfirmList.add(bookItemConfirm);
        }
//        bookItemConfirmList = new ArrayList<>();
//        getData();
//        for(int i=0; i<10; i++) {
//            bookItemConfirm = new BookItemConfirm(confirmUserName, confirmBookName, confirmTime, confirmUserImgPath, PageIndex.BOOK_RECORD_REQUEST);
//            bookItemConfirmList.add(bookItemConfirm);
//        }

    }

    private void getData() {
        // request
        requesterName = GlobalConst.TEST_USER_NAME;
        bookName = GlobalConst.TEST_BOOK_NAME;
        requesterTime = GlobalConst.TEST_USER_UPLOAD_TIME;
        requesterLeft = GlobalConst.TEST_REQUESTER_LEFT;
        // confirm
        confirmUserName = GlobalConst.TEST_USER_NAME;
        confirmBookName = GlobalConst.TEST_BOOK_NAME;
        confirmTime = GlobalConst.TEST_USER_UPLOAD_TIME;
    }
}
