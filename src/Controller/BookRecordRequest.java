package Controller;

import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
        setLayout();
    }

    private void setLayout() {
        getData();
        getBookItemRequestList();
        getBookItemConfirmList();

        vBox.getChildren().add(borrowRequestTitle);
        vBox.getChildren().addAll(bookItemRequestList);
        vBox.getChildren().add(returnConfirmTitle);
        vBox.getChildren().addAll(bookItemConfirmList);

        commonBottom.setLayoutY(588);
    }

    private void getBookItemRequestList(){
        bookItemRequestList = new ArrayList<>();
//        getData();
        for(int i=0; i<10; i++) {
            bookItemRequest = new BookItemRequest(requesterName, bookName, requesterTime, requesterLeft,requesterImgPath, PageIndex.BOOK_RECORD_REQUEST);
//            bookItemRequest.setBelongId(PageIndex.BOOK_RECORD_REQUEST);
            bookItemRequestList.add(bookItemRequest);
        }
    }

    private void getBookItemConfirmList() {
        bookItemConfirmList = new ArrayList<>();
//        getData();
        for(int i=0; i<10; i++) {
            bookItemConfirm = new BookItemConfirm(confirmUserName, confirmBookName, confirmTime, confirmUserImgPath, PageIndex.BOOK_RECORD_REQUEST);
            bookItemConfirmList.add(bookItemConfirm);
        }
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
