package Controller;

import dao.BookDao;
import dao.UserDao;
import global.GlobalConst;
import global.PageIndex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import model.Book;
import model.BookRecord;

// 通过uid等于0来控制上传的记录
public class BookUpload implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {}
    private PageIndex fromId;
    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }

    @FXML
    private Button uploadBtn;
    @FXML
    private TextField bookNameTxt;
    @FXML
    private TextField bookAuthorTxt;
    @FXML
    private TextField bookPressTxt;
    @FXML
    private TextArea bookWordsTxt;
    @FXML
    private ImageView bookImgView1;
    @FXML
    private ImageView bookImgView2;
    @FXML
    private ImageView bookImgView3;

    private String bookName, bookAuthor, bookPress, bookWords;
    private Image img1, img2, img3;
    private String imgPath1, imgPath2, imgPath3;

    private final String COMMON_TITLE = "上传书籍";
    private final String INFORMATION_FULL = "请先完善书籍信息！";
    private final String INFORMATION_SUCCESS = "上传成功";
    private final String INFORMATION_FAILED = "抱歉，上传失败！";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookUpload initialize");
        setLayout();
        setEvents();
    }

    private void setLayout() {
        img1 = new Image(GlobalConst.COMMON_IMG_BACKGROUD);
        img2 = new Image(GlobalConst.COMMON_IMG_BACKGROUD);
        img3 = new Image(GlobalConst.COMMON_IMG_BACKGROUD);

        bookImgView1.setImage(img1);
        bookImgView2.setImage(img2);
        bookImgView3.setImage(img3);
    }

    private void setEvents() {
        bookImgView1.imageProperty().addListener(new ChangeListener<Image>() {
            @Override
            public void changed(ObservableValue<? extends Image> observable, Image oldValue, Image newValue) {

            }
        });
    }

    @FXML
    private void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        if(fromId == PageIndex.ACTION_CHOOSE)
            app.showActionChoose();
        else
            app.showBookInfo(fromId);
    }

    @FXML
    private void onUploadBtnClicked(ActionEvent event) throws Exception{
        System.out.println("uploadBtn Clicked");
        bookName = bookNameTxt.getText();
        bookAuthor = bookAuthorTxt.getText();
        bookPress = bookPressTxt.getText();
        bookWords = bookWordsTxt.getText();

        // 判空
        if(checkValid()) { // 上传
            if(uploadBook()) {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_SUCCESS);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
                onToBackBtnClicked();
            } else {
                Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.OPERATION_FAILED);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
            }
        }

        else {
            Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.INFORMATION_NOT_FULL);
            information.setTitle(COMMON_TITLE);
            information.showAndWait();
        }
    }

    private boolean checkValid() {
        return true;
    }

    private boolean uploadBook(){
        try{
            // 保存书籍信息
            int userId = UserDao.getInfoByName(Login.username).getUid();
            System.out.println("upload book...");
            Book book = new Book();
            book.setName(bookName);
            book.setOwnerid(userId);
            book.setAuthor(bookAuthor);
            book.setIntroduction("这是一本好书");
            book.setPagenum(0);
            book.setStatus(0); // 代表Free
            BookDao.insertBook(book);
            // 创建记录
            BookRecord record = new BookRecord();
            int bid = BookDao.getBookId(userId, bookName);
            record.setUid(0);
            record.setBid(bid);
            record.setLeft(bookWords);
            Date day = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            record.setCreateTime(df.format(day));
            record.setOwnerid(userId);
            return true;
        }catch (Exception E){
            return false;
        }
    }
}
