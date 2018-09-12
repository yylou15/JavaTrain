package Controller;

import dao.BookDao;
import dao.BookRecordDao;
import dao.UserDao;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Book;
import model.BookRecord;
import model.User;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class BookBorrowConfirm implements Initializable {

    public static int nowRid;
    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {}

    @FXML
    private ImageView infoTopImgView;
    @FXML
    private Label infoTopNameLabel,infoTopContactLabel,infoTopIntroLabel;
    @FXML
    private Label infoMiddleScoreLabel;
    @FXML
    private Label requestedBookLabel, requesterLeftLabel;
    @FXML
    private Button toBackBtn, agreeBtn, denyBtn;

    private String requesterName, requesterContact, requestIntro;
    private String requesterScore, requestedBook, requesterLeft;
    private String requesterImgPath;
    private Image requesterImg;

    private final String COMMON_TITLE = "借书申请";
    private final String CONFIRMATION_AGREE = "借书期限为30天，请主动联系借书申请人交付书籍";
    private final String CONFIRMATION_DENY = "确定拒绝申请？";
    private final String INFORMATION_AGREE = "申请已成功接受";
    private final String INFORMATION_DENY = "申请已成功拒绝";
    private final String INFORMATION_FAILED = "操作失败！";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookBorrowConfirm initialize");
        try {
            setData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData() throws Exception{
//        getData();
        BookRecord record = BookRecordDao.getRecordByRid(nowRid);
        User user = UserDao.getInfoByUid(record.getUid());
        Book book = BookDao.getBookByBid(record.getBid());
        requesterName = user.getName();
        requesterContact = user.getQq();
        requestIntro = user.getIntroduction();
        requesterScore = String.valueOf(user.getScore());
        requestedBook = book.getName();
        requesterLeft = record.getLeft();
        requesterImgPath = user.getAvatarUrl();

        infoTopNameLabel.setText("姓名：" + requesterName);
        infoTopContactLabel.setText("QQ：" + requesterContact);
        infoTopIntroLabel.setText(requestIntro);
        infoMiddleScoreLabel.setText("信誉分：" + requesterScore);
        requestedBookLabel.setText(requestedBook);
        requesterLeftLabel.setText(requesterLeft);

        Image img = new Image(requesterImgPath);
        infoTopImgView.setImage(img);
        // img
    }

    private void setLayout() throws Exception{

    }

    @FXML
    private void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        app.showBookRecordRequest();
    }

    @FXML
    private void onAgreeBtnClicked() {
        System.out.println("agreeBtn Clicked");
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, CONFIRMATION_AGREE);
        confirmation.setTitle(COMMON_TITLE);
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            // 借书 如果数据库访问成功则提示成功 否则失败 成功的话还要返回页面
            if(lendBook()) {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_AGREE);
                information.showAndWait();
                information.setTitle(COMMON_TITLE);
                onToBackBtnClicked();
            } else {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_FAILED);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
            }
        }
    }

    @FXML
    private void onDenyBtnClicked() {
        System.out.println("denyBtn Clicked");
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, CONFIRMATION_DENY);
        confirmation.setTitle(COMMON_TITLE);
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            // 拒绝
            if(denyLend()) {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_DENY);
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

    private boolean lendBook() {
        System.out.println("lend book...");
        BookDao.lendBook(BookInfo.nowBid,requesterName);
        return true;
    }

    private boolean denyLend() {
        System.out.println("deny lend...");
        BookDao.denyLend(BookInfo.nowBid,requesterName);
        return true;
    }

    private void  getData() {

    }
}
