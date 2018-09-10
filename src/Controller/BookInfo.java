package Controller;

import global.BookStatus;
import global.GlobalConst;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// 可以通过一个showBookInfo的参数来回到那个到它这边的页面
public class BookInfo implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        app.registerBookItemBorrow(bookBorrowList.get(0));
        app.registerBookItemComment(bookCommentList.get(0));
        for(BookItemBorrow bookItemBorrow : bookBorrowList) {
            bookItemBorrow.setBelongId(fromId);
        }
    }
    private PageIndex fromId;
    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }

    @FXML
    private Label bookNameLabel, bookAuthorLabel, bookPressLabel, bookLeftLabel, bookBorrowAmountLabel, bookScoreLabel;
    @FXML
    private Label bookNameTxt, bookAuthorTxt, bookPressTxt, bookLeftTxt, bookBorrowAmountTxt, bookScoreTxt;
    @FXML
    private Button addCollectionBtn, uploadBookBtn, addCommentBtn;
    @FXML
    private Label readMoreComment, checkMoreBorrow;
    @FXML
    private VBox bookCommentVBox, bookBorrowListVBox;
    @FXML
    private Label bookIntroTxt;
    @FXML
    private GridPane bookInfoPane;

    // book info
    private String bookName, bookAuthor, bookPress, bookBorrowAmount, bookScore, bookIntro;
    private BookStatus bookStatus;
    private String bookLeftNum;
    private String bookImgPath;
    // book_comment
    private String userName, userGivenScore, userCreateTime, userComment;
    private String userLikeNum;
    private String userImgPath;
    // book_borrow
    private String onwerName, onwerScore, onwerTime, onwerLeft;
    private BookStatus onwerBookStatus;
    private String onwerImgPath;
    // 收藏状态
    private boolean collectionStattus;


    private BookItemComment bookItemComment;
    private List<BookItemComment> bookCommentList;
    private BookItemBorrow bookItemBorrow;
    private List<BookItemBorrow> bookBorrowList;

    private final String COMMON_TITLE = "书籍信息";
    private final String COLLECTED = "收藏此书";
    private final String NOT_COLLECTED = "取消收藏";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookInfo initialize");
        setData();
        setLayout();
    }

    private void setData() {
        getBookInfoData();
        bookNameTxt.setText(bookName);
        bookAuthorTxt.setText(bookAuthor);
        bookPressTxt.setText(bookPress);
        bookLeftTxt.setText(bookLeftNum);
        bookBorrowAmountTxt.setText(bookBorrowAmount);
        bookScoreTxt.setText(bookScore);
        bookIntroTxt.setText(bookIntro);
        // img
    }


    private void setLayout() {
        // book info and GridPane
        bookInfoPane.setVgap(15);
        bookInfoPane.setHgap(20);
        bookInfoPane.add(bookNameLabel, 0, 0);
        bookInfoPane.add(bookAuthorLabel, 0, 1);
        bookInfoPane.add(bookPressLabel, 0, 2);
        bookInfoPane.add(bookLeftLabel, 0, 3);
        bookInfoPane.add(bookBorrowAmountLabel, 0, 4);
        bookInfoPane.add(bookScoreLabel, 0, 5);

        bookInfoPane.add(bookNameTxt, 1, 0);
        bookInfoPane.add(bookAuthorTxt, 1, 1);
        bookInfoPane.add(bookPressTxt, 1, 2);
        bookInfoPane.add(bookLeftTxt, 1, 3);
        bookInfoPane.add(bookBorrowAmountTxt, 1, 4);
        bookInfoPane.add(bookScoreTxt, 1, 5);

        // book comment
        getBookCommentList();
        bookCommentVBox.getChildren().addAll(bookCommentList);
        getBookBorrowList();
        bookBorrowListVBox.getChildren().addAll(bookBorrowList);

    }

    @FXML
    private void onAddCollectionBtnClicked() {
        System.out.println("addCollectionBtn Clicked");
        // 单纯的数据库访问 对哦 这里应该还有一个Button的能否问题 甚至取消收藏
        String collectionStatus = addCollectionBtn.getText();
        if(collectionStatus.equals(COLLECTED)) {
            if(operateCollection(true)) {
                addCollectionBtn.setText(NOT_COLLECTED);
            } else {
                Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.OPERATION_FAILED);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
            }
        } else {
            if(operateCollection(false)) {
                addCollectionBtn.setText(COLLECTED);
            } else {
                Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.OPERATION_FAILED);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
            }
        }
    }

    @FXML
    private void onUploadBookBtnClicked() {
        System.out.println("uploadBookBtn Clicked");
        app.showBookUpload(fromId);
    }

    @FXML
    private void onAddCommentBtnClicked() {
        System.out.println("addCommentBtn Clicked");
        app.showAddBookComment(fromId);
    }

    @FXML
    private void onReadMoreCommentClicked() {
        System.out.println("readMoreComment Clicked");
        app.showBookComment(fromId);
    }

    @FXML
    private void onCheckMoreBorrowClicked() {
        System.out.println("checkMoreBorrow Clicked");
        app.showBookBorrowList(fromId);
//        app.showBookBorrowRequest();
    }


    @FXML
    private void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        System.out.println(fromId);
        switch (fromId) {
            case BOOK_BORROW:
                app.showBookBorrow();
                break;
            case BOOK_RECOMMEND:
                app.showBookRecommend();
                break;
            case BOOK_RECORD_BORROW:
                app.showBookRecordBorrow();
                break;
            case BOOK_RECORD_UPLOAD:
                app.showBookRecordUpload();
                break;
            case BOOK_RECORD_REQUEST: // 其实是不会出现的
                app.showBookRecordRequest();
                break;
            case USER_COLLECTION:
                app.showUserCollection();
                break;
        }
    }

    private boolean operateCollection(boolean choice) {
        if(choice) {
            System.out.println("choose collect...");
        } else {
            System.out.println("cancel collect...");
        }
        return true;
    }

    private void getBookCommentList() {
        bookCommentList = new ArrayList<>();
        getBookCommentData();
        for(int i=0; i<1; i++) {
            bookItemComment = new BookItemComment(userName, userGivenScore, userCreateTime, userComment, userLikeNum, userImgPath, fromId);
            bookCommentList.add(bookItemComment);
        }
    }

    private void getBookBorrowList() {
        bookBorrowList = new ArrayList<>();
        getBookBorrowData();
        for(int i=0; i<1; i++) {
            bookItemBorrow = new BookItemBorrow(onwerName, onwerScore, onwerTime, onwerLeft, onwerBookStatus, onwerImgPath, fromId, PageIndex.BOOK_INFO);
//            bookItemBorrow.setRootId(PageIndex.BOOK_INFO);
            bookBorrowList.add(bookItemBorrow);
        }
    }

    private void getBookInfoData() {
        bookName = GlobalConst.TEST_BOOK_NAME;
        bookAuthor = GlobalConst.TEST_BOOK_AUTHOR;
        bookPress = GlobalConst.TEST_BOOK_PRESS;
        bookBorrowAmount = "233";
        bookScore = GlobalConst.TEST_BOOK_SCORE;
    }

    private void getBookCommentData() {
        // book_comment
        userName = GlobalConst.TEST_USER_NAME;
        userGivenScore = GlobalConst.TEST_BOOK_SCORE;
        userCreateTime  = GlobalConst.TEST_USER_UPLOAD_TIME;
        userComment = GlobalConst.TEST_USER_COMMENT;
        userLikeNum = "57";
    }

    private void getBookBorrowData() {
        onwerName = GlobalConst.TEST_USER_NAME;
        onwerScore = GlobalConst.TEST_USER_SCORE;
        onwerTime = GlobalConst.TEST_USER_UPLOAD_TIME;
        onwerLeft = GlobalConst.TEST_REQUESTER_LEFT;
        onwerBookStatus = BookStatus.FREE;
    }
}
