package Controller;

import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
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

    private String requesterName, bookName, requesterTime, requesterLeft;
    private String requesterImgPath;
//    private Image requesterImg;

    private BookItemRequest bookItemRequest;
    private List<BookItemRequest> bookItemRequestList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookRecordRequest initialize");
        setLayout();
    }

    private void setLayout() {
        getBookItemRequestList();
        vBox.getChildren().addAll(bookItemRequestList);

        commonBottom.setLayoutY(588);
    }

    private void getBookItemRequestList(){
        bookItemRequestList = new ArrayList<>();
        getData();
        for(int i=0; i<10; i++) {
            bookItemRequest = new BookItemRequest(requesterName, bookName, requesterTime, requesterLeft,requesterImgPath, PageIndex.BOOK_RECORD_REQUEST);
//            bookItemRequest.setBelongId(PageIndex.BOOK_RECORD_REQUEST);
            bookItemRequestList.add(bookItemRequest);
        }
    }

    private void getData() {
        requesterName = GlobalConst.TEST_USER_NAME;
        bookName = GlobalConst.TEST_BOOK_NAME;
        requesterTime = GlobalConst.TEST_USER_UPLOAD_TIME;
        requesterLeft = GlobalConst.TEST_REQUESTER_LEFT;
    }
}
