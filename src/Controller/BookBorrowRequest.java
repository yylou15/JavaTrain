package Controller;

import Utils.BookStatusInTable;
import Utils.returnObj;
import dao.BookDao;
import dao.BookRecordDao;
import dao.UserDao;
import global.BookStatus;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.BookRecord;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookBorrowRequest implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {}
    private PageIndex fromId, rootId;
    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }
    public void setRootId(PageIndex rootId) {
        this.rootId = rootId;
    }

    @FXML
    private Button toBackBtn, borrowConfirmBtn;
    @FXML
    private Label bookOwnerNameLabel, bookOwnerLeftLabel;
    @FXML
    private ImageView bookImgView1, bookImgView2, bookImgView3;
    @FXML
    private TextArea bookBorrowLeftTxt;

    private final String COMMON_TITLE = "借书申请";
    private final String CONFIRMATION_REQUEST = "确定发出30天的借书申请？";
    private final String INFORMATION_SUCCESS = "借书申请已发送，请耐心等待有书人的联系";
    private final String INFORMATION_FAILED = "操作失败！";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookBorrowRequest initialize");
        setLayout();
    }

    private void setLayout() {

    }

    @FXML
    private void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        switch (rootId) {
            case BOOK_INFO:
                app.showBookInfo(fromId);
                break;
            case BOOK_BORROW_LIST:
                app.showBookBorrowList(fromId);
                break;
        }
    }

    @FXML
    private void onBorrowConfirmBtnClicked() {
        System.out.println("borrowConfirmBtn Clicked");
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, CONFIRMATION_REQUEST);
        confirmation.setTitle(COMMON_TITLE);
        confirmation.setTitle(COMMON_TITLE);
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            // 确定
            if (sendRequest()) {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_SUCCESS);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
                onToBackBtnClicked();
            } else {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_FAILED);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
            }
        }
    }

    private boolean sendRequest() {
        try{
            System.out.println("send request...");
            BookRecord record = new BookRecord();
            record.setBid(BookInfo.nowBid);
            Date day = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            record.setCreateTime(df.format(day));
            record.setOwnerid(BookDao.getBookByBid(BookInfo.nowBid).getOwnerid());
            record.setStataus(BookStatusInTable.getIntForBookStatus(BookStatus.REQUESTING));
            record.setUid(UserDao.getInfoByName(Login.username).getUid());
            System.out.println("借书者申请留言： " + bookBorrowLeftTxt.getText());
            record.setLeft(bookBorrowLeftTxt.getText());
            returnObj rs = BookRecordDao.insertRecoord(record);
            System.out.println(rs.getStatus());
            System.out.println(rs.getMsg());

            // 更新此书的状态
            BookDao.updateBookStatus(BookInfo.nowBid, BookStatus.REQUESTING);
            return true;
        }catch (Exception E){
            return false;
        }
    }
}
